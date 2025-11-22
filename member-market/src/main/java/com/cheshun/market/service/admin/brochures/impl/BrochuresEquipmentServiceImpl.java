package com.cheshun.market.service.admin.brochures.impl;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.read.listener.PageReadListener;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zzjdyf.api.web.http.common.EntityResult;
import com.zzjdyf.api.web.http.common.ListResult;
import com.zzjdyf.component.page.PageCondition;
import com.zzjdyf.constant.enumeration.MedicalEquipmentCategories;
import com.zzjdyf.dto.brochures.MedicalEquipmentCategoriesDTO;
import com.zzjdyf.dto.brochures.MedicalEquipmentCategoriesDTOExample;
import com.zzjdyf.dto.brochures.MedicalEquipmentDTO;
import com.zzjdyf.dto.brochures.MedicalEquipmentDTOExample;
import com.zzjdyf.enumeration.CommontBooleanStatusType;
import com.zzjdyf.exception.BaseException;
import com.zzjdyf.mappers.project.customer.brochures.MedicalEquipmentCategoriesMapper;
import com.zzjdyf.mappers.project.customer.brochures.MedicalEquipmentDAO;
import com.zzjdyf.mappers.project.customer.brochures.MedicalEquipmentMapper;
import com.zzjdyf.po.admin.brochures.BaseGoodsInfoPO;
import com.zzjdyf.po.admin.brochures.BrochuresEquipmentPO;
import com.zzjdyf.po.admin.brochures.MedicalEquipmentCategoriesPO;
import com.zzjdyf.result.Result;
import com.zzjdyf.service.admin.brochures.BrochuresEquipmentService;
import com.zzjdyf.service.admin.brochures.oms.OmsGoodsService;
import com.zzjdyf.tools.util.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

import static com.zzjdyf.constant.CommonServiceConstant.PE;
import static com.zzjdyf.constant.CommonServiceConstant.SU;

@Slf4j
@Service
public class BrochuresEquipmentServiceImpl implements BrochuresEquipmentService {

    @Autowired
    private MedicalEquipmentMapper medicalEquipmentMapper;
    @Autowired
    private MedicalEquipmentCategoriesMapper medicalEquipmentCategoriesMapper;
    @Autowired
    private MedicalEquipmentDAO medicalEquipmentDAO;
    @Autowired
    private OmsGoodsService omsGoodsService;

    @Override
    public Result uploadBrochuresEquipmentInfo(MultipartFile file) {
        //失败后写出的数据
        List<MedicalEquipmentDTO> list = new ArrayList<>();
        List<MedicalEquipmentDTO> poUpdateList = new ArrayList<>();
        String fileName  = null;
        try {
            //获取文件流
            List<BrochuresEquipmentPO> poList = new ArrayList<>();
            Set<String> illegalGoodsCodeSet = new HashSet<>();
            //读取文件内容
            // 这里默认每次会读取100条数据 然后返回过来 直接调用使用数据就行
            // 具体需要返回多少行可以在`PageReadListener`的构造函数设置
            EasyExcel.read(file.getInputStream(), BrochuresEquipmentPO.class, new PageReadListener<BrochuresEquipmentPO>(dataList -> {
                for (BrochuresEquipmentPO po : dataList) {
                    //校验
                    if (null == po.getGoodsCode() || "".equals(po.getGoodsCode().trim())) {
                        throw new BaseException("Excel中存在货号为空的数据，请检查！");
                    }
                    if (null == po.getCategoryName() || "".equals(po.getCategoryName().trim())) {
                        illegalGoodsCodeSet.add(po.getGoodsCode());
                    }
                    if (null != po.getCategoryName() && !"".equals(po.getCategoryName().trim())) {
                        String code = MedicalEquipmentCategories.getCodeByName(po.getCategoryName());
                        if (null == code) {
                            illegalGoodsCodeSet.add(po.getGoodsCode());
                        }
                    }
                    poList.add(po);
                    log.info("读取到一条数据{}", JSONObject.toJSONString(po));
                }
            })).sheet().doRead();

            //目录名称不符合规范
            if (!CollectionUtils.isEmpty(illegalGoodsCodeSet)) {
                return new Result(300, false, String.format("Excel中货号=%s的目录名称不符合规范!",
                        String.join(",", illegalGoodsCodeSet)));
            }

            List<String> inputGoodsCodes = poList.stream()
                    .map(BrochuresEquipmentPO::getGoodsCode).distinct().collect(Collectors.toList());

            //已存在的商品
            Map<String, MedicalEquipmentDTO> existedBaseGoodsMap = queryGoodsBaseDetailsByPlatGoodsCodeForMap(inputGoodsCodes).getEntityData();

            if(poList != null  && poList.size() > 0) {
                for(BrochuresEquipmentPO po : poList) {
                    if (existedBaseGoodsMap.get(po.getGoodsCode()) == null) {
                        MedicalEquipmentDTO record = new MedicalEquipmentDTO();
                        copyPo2Dto(po, record, (byte) 1);
                        list.add(record);
                    } else if(existedBaseGoodsMap.get(po.getGoodsCode()) != null) {
                        MedicalEquipmentDTO record = existedBaseGoodsMap.get(po.getGoodsCode());
                        copyPo2Dto(po, record, (byte) 2);
                        poUpdateList.add(record);
                    }
                }
            }
            if (list.size() > 0) {
                //批量插入
                medicalEquipmentMapper.batchInsert(list);
            }
            if (poUpdateList.size() > 0) {
                //批量更新
                updateBaseGoodsInfosByFields(poUpdateList);
            }
        } catch (Exception e) {
            log.error("上传保存医疗器械记录时发生异常，=, ", e);
            return new Result(300, false, e.getMessage());
        }
        return SU;
    }

    @Override
    public Result syncOmsGoodsInfoBatch(List<BrochuresEquipmentPO> goodsBaseInfoList) {
        if (goodsBaseInfoList == null || goodsBaseInfoList.size() == 0) {
            return new Result(300, false, "操作数据为空!");
        }
        if (goodsBaseInfoList != null && goodsBaseInfoList.size() > 100) {
            return new Result(300, false, "同时操作过多数据!");
        }

        MedicalEquipmentDTOExample example = new MedicalEquipmentDTOExample();
        //example.createCriteria().andStatusEqualTo(STATUS_NONE)

        List<String> inputGoodsCodes = goodsBaseInfoList.stream()
                .map(BrochuresEquipmentPO::getGoodsCode).distinct().collect(Collectors.toList());

        //已存在的商品
        Map<String, MedicalEquipmentDTO> existedBaseGoodsMap = queryGoodsBaseDetailsByPlatGoodsCodeForMap(inputGoodsCodes).getEntityData();
        Set<String> lackingGoodsCodeSet = new HashSet<>();
        Set<String> lackG3ImgGoodsCodeSet = new HashSet<>();
        if(inputGoodsCodes != null  && inputGoodsCodes.size() > 0) {
            for(String inputGoodsCode : inputGoodsCodes) {
                if (existedBaseGoodsMap.get(inputGoodsCode) == null) {
                    lackingGoodsCodeSet.add(inputGoodsCode);
                }
            }
            if (!CollectionUtils.isEmpty(lackingGoodsCodeSet)) {
                return new Result(300, false, String.format("商品编码(OMS货号)=%s的器械未导入！",
                        String.join(",",lackingGoodsCodeSet)));
            }
        }
        //准备base表中的   //准备图片更新替换数据并初始化容量
        List<MedicalEquipmentDTO> updateGoods  = new ArrayList<>();

        try {
            //一次批量获取oms最新商品信息
            Map<String, BaseGoodsInfoPO> mapOmsResult = omsGoodsService.queryOmsGoodsInfoForMap(inputGoodsCodes).getEntityData();

            for (BrochuresEquipmentPO addGoodsBaseInfoVPO : goodsBaseInfoList) {
                MedicalEquipmentDTO dto = existedBaseGoodsMap.get(addGoodsBaseInfoVPO.getGoodsCode());
                BaseGoodsInfoPO data = mapOmsResult.get(addGoodsBaseInfoVPO.getGoodsCode());
                if (null == data) {
                    log.info(String.format("获取OMS商品=%s信息为空 ", addGoodsBaseInfoVPO.getGoodsCode()));
                    continue;
                }
                if (null == data.getGoodsMainImage() || null == data.getGoodsBannerImages() || null == data.getGoodsDetailImages()) {
                    lackG3ImgGoodsCodeSet.add(addGoodsBaseInfoVPO.getGoodsCode());
                    //continue;
                }
                //oms拉取数据覆盖更新BaseGoodsInfoDTO对应属性
                /*dto.setGoodsName(data.getGoodsName());
                dto.setDrugTypeCode(data.getDrugTypeCode());
                dto.setDrugTypeName(data.getDrugTypeName());
                dto.setApprovalNo(data.getApprovalNo());
                dto.setGoodsUnit(data.getGoodsUnit());
                dto.setProductionCompany(data.getProductionCompany());
                dto.setProductionPlace(data.getProductionPlace());
                dto.setBarCode(data.getBarCode());*/
                dto.setSpecification(data.getSpecifications());
                dto.setGoodsPrice(data.getSuggestedRetailPrice());
                //主图
                if (null != data.getGoodsMainImage()) {
                    dto.setMainImage(data.getGoodsMainImage());
                }
                //轮播图
                if (null != data.getGoodsBannerImages()) {
                    dto.setBannerImages(String.join(",", data.getGoodsBannerImages()));
                }
                //详情图
                if (null != data.getGoodsDetailImages()) {
                    dto.setDetailImages(String.join(",", data.getGoodsDetailImages()));
                }

                updateGoods.add(dto);
                //填充图片
                /*replaceGoodsImgs.addAll(convertImageInfos(dto.getGoodsId(),
                        data.getGoodsMainImage(), data.getGoodsBannerImages(), data.getGoodsDetailImages()));*/
            }

            if (updateGoods.size() > 0) {
                //批量更新
                updateBaseGoodsInfosByFields(updateGoods);
                //批量更新商品关联图片
                /*baseGoodsImageService.replaceGoodsBaseImages(replaceGoodsImgs);*/
            }
            if (!CollectionUtils.isEmpty(lackG3ImgGoodsCodeSet)) {
                return new Result(200, true, String.format("部分已同步，请注意：商品编码(OMS货号)=%s的商品的G3缺失图片！",
                        String.join(",",lackG3ImgGoodsCodeSet)));
            }
            return SU;
        }catch (Exception e) {
            log.error("批量同步OMS商品信息 发生异常！ ={}", e.getMessage());
            e.printStackTrace();
            return new Result(300, false, e.getMessage());
        }
    }

    @Override
    public ListResult<MedicalEquipmentDTO> queryGoodsBaseDetailsByPlatGoodsCode(Collection<String> platGoodsCodes) {
        if (platGoodsCodes == null || platGoodsCodes.size() == 0) {
            return ResultUtil.listResult(PE);
        }
        //转换ID
        List<String> resourceGoodsCodes = null;
        if (platGoodsCodes instanceof List) {
            resourceGoodsCodes = (List<String>) platGoodsCodes;
        } else {
            resourceGoodsCodes = new ArrayList<String>(platGoodsCodes);
        }
        //查询商品
        MedicalEquipmentDTOExample example = new MedicalEquipmentDTOExample();
        example.createCriteria().andGoodsCodeIn(resourceGoodsCodes)
                .andStatusEqualTo(CommontBooleanStatusType.YES.getCode());
        List<MedicalEquipmentDTO> infos = medicalEquipmentMapper.selectByExample(example);
        return ResultUtil.listResult(infos);
    }

    @Override
    public EntityResult<Map<String, MedicalEquipmentDTO>> queryGoodsBaseDetailsByPlatGoodsCodeForMap(Collection<String> platGoodsCodes) {
        List<MedicalEquipmentDTO> infos = queryGoodsBaseDetailsByPlatGoodsCode(platGoodsCodes).getListData();
        Map<String, MedicalEquipmentDTO> result = new HashMap<String, MedicalEquipmentDTO>();
        if (infos != null && infos.size() > 0) {
            for (MedicalEquipmentDTO info : infos) {
                result.put(info.getGoodsCode(), info);
            }
        }
        return ResultUtil.entResult(result);
    }

    @Override
    public Result adminQueryEquipmentByCategory(PageCondition pageCondition, String categoryName, String equipmentName, String goodsCode) {
        //校验
        if (null != categoryName && !"".equals(categoryName.trim())) {
            String code = MedicalEquipmentCategories.getCodeByName(categoryName);
            if (null == code) {
                return new Result(300, false, "目录名称不符合规范!");
            }
        }

        MedicalEquipmentDTOExample example = new MedicalEquipmentDTOExample();
        MedicalEquipmentDTOExample.Criteria criteria = example.createCriteria();
        if (null != categoryName && !"".equals(categoryName.trim())) {
            criteria.andCategoryNameEqualTo(categoryName);
        }
        if (null != equipmentName && !"".equals(equipmentName.trim())) {
            criteria.andEquipmentNameLike("%" + equipmentName + "%");
        }
        if (null != goodsCode && !"".equals(goodsCode.trim())) {
            criteria.andGoodsCodeEqualTo(goodsCode);
        }
        criteria.andStatusEqualTo(CommontBooleanStatusType.YES.getCode());
        example.setOrderByClause("category_code asc");

        if (pageCondition.getIsPage()){
            PageHelper.startPage(pageCondition.getPageNum(),pageCondition.getPageSize());
        }

        List<MedicalEquipmentDTO> medicalEquipmentDTOS = medicalEquipmentMapper.selectByExample(example);
        PageInfo<MedicalEquipmentDTO> pageInfo = new PageInfo<>(medicalEquipmentDTOS);

        List<BrochuresEquipmentPO> poList = new ArrayList<>();
        for (MedicalEquipmentDTO equipmentDTO : medicalEquipmentDTOS) {
            BrochuresEquipmentPO brochuresEquipmentPO = new BrochuresEquipmentPO();
            copyDto2Po(equipmentDTO, brochuresEquipmentPO);
            poList.add(brochuresEquipmentPO);
        }

        PageInfo<BrochuresEquipmentPO> pageInfoPO = new PageInfo<>();
        BeanUtils.copyProperties(pageInfo, pageInfoPO);
        pageInfoPO.setList(poList);

        return ResultUtil.listResult(pageInfoPO);
    }

    @Override
    public Result queryEquipmentByCategory(PageCondition pageCondition, String categoryName, String equipmentName, String goodsCode) {
        //校验
        if (null != categoryName && !"".equals(categoryName.trim())) {
            String code = MedicalEquipmentCategories.getCodeByName(categoryName);
            if (null == code) {
                return new Result(300, false, "目录名称不符合规范!");
            }
        }

        MedicalEquipmentDTOExample example = new MedicalEquipmentDTOExample();
        MedicalEquipmentDTOExample.Criteria criteria = example.createCriteria();
        if (null != categoryName && !"".equals(categoryName.trim())) {
            criteria.andCategoryNameEqualTo(categoryName);
        }
        if (null != equipmentName && !"".equals(equipmentName.trim())) {
            criteria.andEquipmentNameLike("%" + equipmentName + "%");
        }
        if (null != goodsCode && !"".equals(goodsCode.trim())) {
            criteria.andGoodsCodeEqualTo(goodsCode);
        }
        criteria.andStatusEqualTo(CommontBooleanStatusType.YES.getCode());
        example.setOrderByClause("category_code asc");

        if (pageCondition.getIsPage()){
            PageHelper.startPage(pageCondition.getPageNum(),pageCondition.getPageSize());
        }

        List<MedicalEquipmentDTO> medicalEquipmentDTOS = medicalEquipmentMapper.selectByExample(example);
        PageInfo<MedicalEquipmentDTO> pageInfo = new PageInfo<>(medicalEquipmentDTOS);

        List<BrochuresEquipmentPO> poList = new ArrayList<>();
        for (MedicalEquipmentDTO equipmentDTO : medicalEquipmentDTOS) {
            BrochuresEquipmentPO brochuresEquipmentPO = new BrochuresEquipmentPO();
            copyDto2Po(equipmentDTO, brochuresEquipmentPO);
            poList.add(brochuresEquipmentPO);
        }

        PageInfo<BrochuresEquipmentPO> pageInfoPO = new PageInfo<>();
        BeanUtils.copyProperties(pageInfo, pageInfoPO);
        pageInfoPO.setList(poList);

        return ResultUtil.listResult(pageInfoPO);
    }

    @Override
    public Result queryEquipmentDetail(String goodsCode) {
        //校验
        if (null == goodsCode || "".equals(goodsCode.trim())) {
            return new Result(300, false, "货号不能为空!");
        }

        MedicalEquipmentDTOExample example = new MedicalEquipmentDTOExample();
        MedicalEquipmentDTOExample.Criteria criteria = example.createCriteria();
        criteria.andGoodsCodeEqualTo(goodsCode)
                .andStatusEqualTo(CommontBooleanStatusType.YES.getCode());
        List<MedicalEquipmentDTO> equipmentDTOS = medicalEquipmentMapper.selectByExample(example);
        if (null == equipmentDTOS || equipmentDTOS.size() == 0) {
            return new Result(300, false, "医疗器械信息不存在！");
        }
        MedicalEquipmentDTO equipment = equipmentDTOS.get(0);
        BrochuresEquipmentPO brochuresEquipmentPO = new BrochuresEquipmentPO();
        copyDto2Po(equipment, brochuresEquipmentPO);
        //更新访问频次
        incrementFrequence(equipment);
        return ResultUtil.entResult(brochuresEquipmentPO);
    }

    @Override
    public Result updateEquipmentInfo(BrochuresEquipmentPO brochuresEquipmentPO) {
        try {

            //校验
            if (null == brochuresEquipmentPO || "".equals(brochuresEquipmentPO.getGoodsCode().trim())) {
                return new Result(300, false, "货号不能为空!");
            }
            MedicalEquipmentDTOExample example = new MedicalEquipmentDTOExample();
            MedicalEquipmentDTOExample.Criteria criteria = example.createCriteria();
            criteria.andGoodsCodeEqualTo(brochuresEquipmentPO.getGoodsCode())
                    .andStatusEqualTo(CommontBooleanStatusType.YES.getCode());
            List<MedicalEquipmentDTO> equipmentDTOS = medicalEquipmentMapper.selectByExample(example);
            if (null == equipmentDTOS || equipmentDTOS.size() == 0) {
                return new Result(300, false, "医疗器械信息不存在！");
            }

            if (null != brochuresEquipmentPO.getCategoryName() && !"".equals(brochuresEquipmentPO.getCategoryName().trim())) {
                String code = MedicalEquipmentCategories.getCodeByName(brochuresEquipmentPO.getCategoryName());
                if (null == code) {
                    return new Result(300, false, "目录名称不符合规范!");
                }
            }

            MedicalEquipmentDTO equipment = equipmentDTOS.get(0);
            copyPo2Dto(brochuresEquipmentPO, equipment, (byte) 2);
            medicalEquipmentMapper.updateByPrimaryKey(equipment);
            return SU;
        } catch (Exception e) {
            log.error("根据货号更新医疗器械信息时发生异常，goodsCode={}, ", brochuresEquipmentPO.getGoodsCode(), e);
            return new Result(300, false, e.getMessage());
        }
    }

    @Override
    public Result updateBaseGoodsInfosByFields(List<MedicalEquipmentDTO> medicalEquipmentDTOS) {
        if (medicalEquipmentDTOS == null || medicalEquipmentDTOS.size() == 0) {
            return ResultUtil.listResult(PE);
        }
        medicalEquipmentDAO.updateBatchByFields(medicalEquipmentDTOS);
        return SU;
    }

    @Override
    public Result getEquipmentCategoriesByParent(String parentName) {
        //校验
        if (null == parentName && "".equals(parentName.trim())) {
            return new Result(300, false, "父级目录名称输入不能为空!");
        }
        if ("00".equals(parentName.trim())) {
            List<MedicalEquipmentCategoriesPO> list = new ArrayList<>();
            MedicalEquipmentCategoriesDTOExample example = new MedicalEquipmentCategoriesDTOExample();
            example.createCriteria().andParentCodeEqualTo(parentName.trim());
            List<MedicalEquipmentCategoriesDTO> parentCategories = medicalEquipmentCategoriesMapper.selectByExample(example);
            if (null == parentCategories || 0 == parentCategories.size()) {
                //String code = MedicalEquipmentCategories.getCodeByName(parentName);
                return new Result(300, false, "目录名称初始化查询参数异常!");
            }
            for (MedicalEquipmentCategoriesDTO dto: parentCategories) {
                MedicalEquipmentCategoriesPO po = new MedicalEquipmentCategoriesPO();
                List<MedicalEquipmentCategoriesDTO> categories = medicalEquipmentDAO.getEquipmentCategoriesByParent(dto.getCategoryName().trim());
                po.setId(dto.getId());
                po.setCategoryCode(dto.getCategoryCode());
                po.setCategoryName(dto.getCategoryName());
                po.setParentCode(dto.getParentCode());
                po.setStatus(dto.getStatus());
                po.setSubList(categories);
                list.add(po);
            }
            return ResultUtil.listResult(list);
        } else {
            //String code = MedicalEquipmentCategories.getCodeByName(parentName);
            return new Result(300, false, "目录名称不符合规范!");
        }
    }

    @Override
    public Result adminQueryEquipmentCategoryList(PageCondition pageCondition, String categoryName, String parentCode) {

        //查询每个子目录访问频次
        //校验
        /*if (null != categoryName && !"".equals(categoryName.trim())) {
            String code = MedicalEquipmentCategories.getCodeByName(categoryName);
            if (null == code) {
                return new Result(300, false, "目录名称不符合规范!");
            }
        }*/

        MedicalEquipmentCategoriesDTOExample example = new MedicalEquipmentCategoriesDTOExample();
        MedicalEquipmentCategoriesDTOExample.Criteria criteria = example.createCriteria();
        if (null != categoryName && !"".equals(categoryName.trim())) {
            criteria.andCategoryNameLike("%" + categoryName + "%");
        }
        criteria.andStatusEqualTo(CommontBooleanStatusType.YES.getCode());
        example.setOrderByClause("category_code asc");

        if (pageCondition.getIsPage()){
            PageHelper.startPage(pageCondition.getPageNum(),pageCondition.getPageSize());
        }

        List<MedicalEquipmentCategoriesDTO> medicalCategoriesDTOS = medicalEquipmentCategoriesMapper.selectByExample(example);
        PageInfo<MedicalEquipmentCategoriesDTO> pageInfo = new PageInfo<>(medicalCategoriesDTOS);

        List<MedicalEquipmentCategoriesPO> poList = new ArrayList<>();
        for (MedicalEquipmentCategoriesDTO categoryDTO : medicalCategoriesDTOS) {
            MedicalEquipmentCategoriesPO po = new MedicalEquipmentCategoriesPO();
            copyCategoryDto2Po(categoryDTO, po);
            poList.add(po);
        }

        PageInfo<MedicalEquipmentCategoriesPO> pageInfoPO = new PageInfo<>();
        BeanUtils.copyProperties(pageInfo, pageInfoPO);
        pageInfoPO.setList(poList);

        return ResultUtil.listResult(pageInfoPO);
    }

    /**
     * 频次计数
     * @param equipmentDTO
     * @param
     */
    private void incrementFrequence(MedicalEquipmentDTO equipmentDTO) {
        Integer frequency = null == equipmentDTO.getFrequency() ? 0 : equipmentDTO.getFrequency();
        equipmentDTO.setFrequency(Integer.sum(frequency, 1));
        //更新器械频次
        medicalEquipmentMapper.updateByPrimaryKey(equipmentDTO);

        //级联更新器械目录频次
        MedicalEquipmentCategoriesDTOExample example = new MedicalEquipmentCategoriesDTOExample();
        MedicalEquipmentCategoriesDTOExample.Criteria criteria = example.createCriteria();
        if (null != equipmentDTO.getCategoryCode() && !"".equals(equipmentDTO.getCategoryCode().trim())) {
            criteria.andCategoryCodeEqualTo(equipmentDTO.getCategoryCode());
        }
        criteria.andStatusEqualTo(CommontBooleanStatusType.YES.getCode());
        example.setOrderByClause("category_code asc");

        List<MedicalEquipmentCategoriesDTO> medicalCategoriesDTOS = medicalEquipmentCategoriesMapper.selectByExample(example);
        if (null != medicalCategoriesDTOS && medicalCategoriesDTOS.size() > 0) {
            MedicalEquipmentCategoriesDTO categoriesDTO = medicalCategoriesDTOS.get(0);
            Integer categoryFrequency = null == categoriesDTO.getFrequency() ? 0 : categoriesDTO.getFrequency();
            categoriesDTO.setFrequency(Integer.sum(categoryFrequency, 1));
            medicalEquipmentCategoriesMapper.updateByPrimaryKey(categoriesDTO);
        }
    }
    private static final String DEFAULT_IMG_URL = "https://zzj-hyzx.oss-cn-beijing.aliyuncs.com/member/e7deb5d4-caf4-4da3-827d-16c73184d7f8.png";

    /**
     * DTO复制的PO
     * @param equipmentDTO
     * @param brochuresEquipmentPO
     */
    private void copyDto2Po(MedicalEquipmentDTO equipmentDTO, BrochuresEquipmentPO brochuresEquipmentPO) {
        brochuresEquipmentPO.setId(equipmentDTO.getId());
        brochuresEquipmentPO.setGoodsCode(equipmentDTO.getGoodsCode());
        brochuresEquipmentPO.setEquipmentName(equipmentDTO.getEquipmentName());
        brochuresEquipmentPO.setSpecification(equipmentDTO.getSpecification());
        brochuresEquipmentPO.setManufacturer(equipmentDTO.getManufacturer());
        brochuresEquipmentPO.setAdvantages(equipmentDTO.getAdvantages());
        brochuresEquipmentPO.setCategoryCode(MedicalEquipmentCategories.getCodeByName(equipmentDTO.getCategoryName()));
        brochuresEquipmentPO.setCategoryName(equipmentDTO.getCategoryName());
        String mainImg = equipmentDTO.getMainImage();
        if (null == mainImg || "".equals(mainImg.trim())) {
            mainImg = DEFAULT_IMG_URL;
        }
        brochuresEquipmentPO.setMainImage(mainImg);
        String bannerImages = equipmentDTO.getBannerImages();
        if (null == bannerImages || "".equals(bannerImages.trim())) {
            bannerImages = DEFAULT_IMG_URL;
        }
        brochuresEquipmentPO.setBannerImages(bannerImages);
        String detailImages = equipmentDTO.getDetailImages();
        if (null == detailImages || "".equals(detailImages.trim())) {
            detailImages = DEFAULT_IMG_URL;
        }
        brochuresEquipmentPO.setDetailImages(detailImages);
        brochuresEquipmentPO.setRemark(equipmentDTO.getRemark());
        DecimalFormat df = new DecimalFormat("######0.00");
        Double aDouble = Double.parseDouble(String.valueOf(equipmentDTO.getGoodsPrice())) / 1000;
        brochuresEquipmentPO.setGoodsPrice(df.format(aDouble));
        brochuresEquipmentPO.setFrequency(null == equipmentDTO.getFrequency() ? 0 : equipmentDTO.getFrequency());
        brochuresEquipmentPO.setStatus(equipmentDTO.getStatus());
        String createTimeStr = null == equipmentDTO.getCreateTime() ? "" : DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(equipmentDTO.getCreateTime());
        brochuresEquipmentPO.setCreateTimeStr(createTimeStr);
        String updateTimeStr = null == equipmentDTO.getUpdateTime() ? "" : DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(equipmentDTO.getUpdateTime());
        brochuresEquipmentPO.setUpdateTimeStr(updateTimeStr);
    }

    /**
     * PO复制到DTO
     * @param brochuresEquipmentPO
     * @param equipmentDTO
     */
    private void copyPo2Dto(BrochuresEquipmentPO brochuresEquipmentPO, MedicalEquipmentDTO equipmentDTO, Byte optCode) {
        //update要复制id
        if ((byte)2 == optCode){
            equipmentDTO.setId(brochuresEquipmentPO.getId());
        }
        equipmentDTO.setGoodsCode(brochuresEquipmentPO.getGoodsCode());
        equipmentDTO.setEquipmentName(brochuresEquipmentPO.getEquipmentName());
        equipmentDTO.setSpecification(brochuresEquipmentPO.getSpecification());
        equipmentDTO.setManufacturer(brochuresEquipmentPO.getManufacturer());
        equipmentDTO.setAdvantages(brochuresEquipmentPO.getAdvantages());
        equipmentDTO.setCategoryCode(MedicalEquipmentCategories.getCodeByName(brochuresEquipmentPO.getCategoryName()));
        equipmentDTO.setCategoryName(brochuresEquipmentPO.getCategoryName());
        equipmentDTO.setMainImage(brochuresEquipmentPO.getMainImage());
        equipmentDTO.setBannerImages(brochuresEquipmentPO.getBannerImages());
        equipmentDTO.setDetailImages(brochuresEquipmentPO.getDetailImages());
        equipmentDTO.setRemark(brochuresEquipmentPO.getRemark());
        Double aDouble = Double.parseDouble(null == brochuresEquipmentPO.getGoodsPrice() ? "0.00" : brochuresEquipmentPO.getGoodsPrice()) * 1000;
        equipmentDTO.setGoodsPrice(aDouble.longValue());
        Byte status = (null == brochuresEquipmentPO.getStatus() ? (byte)1 : brochuresEquipmentPO.getStatus());
        equipmentDTO.setStatus((byte)1 == optCode ? (byte) 1 : status);
        if ((byte)1 == optCode) {
            equipmentDTO.setCreateTime(LocalDateTime.now());
        } else if ((byte)2 == optCode){
            equipmentDTO.setUpdateTime(LocalDateTime.now());
        }
    }

    /**
     * CategoryDTO复制的PO
     * @param equipmentDTO
     * @param categoriesPO
     */
    private void copyCategoryDto2Po(MedicalEquipmentCategoriesDTO equipmentDTO, MedicalEquipmentCategoriesPO categoriesPO) {
        categoriesPO.setId(equipmentDTO.getId());
        categoriesPO.setParentCode(equipmentDTO.getParentCode());
        categoriesPO.setCategoryCode(MedicalEquipmentCategories.getCodeByName(equipmentDTO.getCategoryName()));
        categoriesPO.setCategoryName(equipmentDTO.getCategoryName());
        categoriesPO.setStatus(equipmentDTO.getStatus());
        categoriesPO.setSubList(null);
        categoriesPO.setFrequency(null == equipmentDTO.getFrequency() ? 0 : equipmentDTO.getFrequency());
    }

}

