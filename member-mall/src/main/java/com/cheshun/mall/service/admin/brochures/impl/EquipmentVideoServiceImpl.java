package com.cheshun.mall.service.admin.brochures.impl;

import com.alibaba.fastjson.JSONObject;
import com.cheshun.common.component.page.PageCondition;
import com.cheshun.common.enumeration.CommontBooleanStatusType;
import com.cheshun.common.enumeration.OperationType;
import com.cheshun.common.tools.utils.AliOssUtil;
import com.cheshun.common.tools.utils.ResultUtil;
import com.cheshun.constant.enumeration.MedicalEquipmentCategories;
import com.cheshun.dto.brochures.MedicalEquipmentVideo;
import com.cheshun.dto.brochures.MedicalEquipmentVideoExample;
import com.cheshun.mall.domain.mapper.project.customer.brochures.MedicalEquipmentVideoMapper;
import com.cheshun.mall.service.admin.brochures.EquipmentVideoService;
import com.cheshun.po.admin.brochures.EquipmentVideoPO;
import com.cheshun.vo.admin.brochures.EquipVideoMiniQrCodeVO;
import com.cheshun.vo.admin.brochures.EquipmentVideoVO;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.cheshun.common.exception.BaseException;
import com.cheshun.common.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static com.cheshun.common.result.Result.ERR_CODE;

/**
 * 器械视频service实现类
 * @author wangzhuo
 * @date 20251110
 */
@Slf4j
@Service
public class EquipmentVideoServiceImpl implements EquipmentVideoService {

    @Autowired
    private MedicalEquipmentVideoMapper equipmentVideoMapper;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private AliOssUtil aliOssUtil;

    @Override
    public Result addEquipmentVideoInfo(EquipmentVideoVO equipmentVideoVO) {
        try {
            if (null == equipmentVideoVO || ObjectUtils.isEmpty(equipmentVideoVO.getOssObjectName())) {
                throw new BaseException("视频资源OSS对象名称不能为空，请检查！");
            }

            MedicalEquipmentVideo equipmentVideo = new MedicalEquipmentVideo();
            copyVo2Dto(equipmentVideoVO, equipmentVideo, OperationType.INSERT);

            equipmentVideoMapper.insert(equipmentVideo);
            return ResultUtil.result(new HashMap<>());
        } catch (Exception e) {
            log.error("保存器械视频时发生异常，=, ", e);
            return ResultUtil.result(ERR_CODE, e.getMessage());
        }
    }

    @Override
    public Result queryEquipmentVideoByCategory(PageCondition pageCondition, String categoryName, String equipmentName, String goodsCode) {
        try {
            //校验
            if (null != categoryName && !"".equals(categoryName.trim())) {
                String code = MedicalEquipmentCategories.getCodeByName(categoryName);
                if (null == code) {
                    return new Result(300, false, "目录名称不符合规范!");
                }
            }

            MedicalEquipmentVideoExample example = new MedicalEquipmentVideoExample();
            MedicalEquipmentVideoExample.Criteria criteria = example.createCriteria();
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

            if (pageCondition.getIsPage()) {
                PageHelper.startPage(pageCondition.getPageNum(), pageCondition.getPageSize());
            }

            List<MedicalEquipmentVideo> equipmentVideoList = equipmentVideoMapper.selectByExample(example);
            PageInfo<MedicalEquipmentVideo> pageInfo = new PageInfo<>(equipmentVideoList);

            List<EquipmentVideoPO> poList = new ArrayList<>();
            for (MedicalEquipmentVideo equipmentVideo : equipmentVideoList) {
                EquipmentVideoPO equipmentVideoPO = new EquipmentVideoPO();
                copyDto2Po(equipmentVideo, equipmentVideoPO);
                poList.add(equipmentVideoPO);
            }

            PageInfo<EquipmentVideoPO> pageInfoPO = new PageInfo<>();
            BeanUtils.copyProperties(pageInfo, pageInfoPO);
            pageInfoPO.setList(poList);

            return ResultUtil.listResult(pageInfoPO);
        } catch (Exception e) {
            log.error("后管-查询视频信息列表时发生异常，=, ", e);
            return ResultUtil.result(ERR_CODE, e.getMessage());
        }
    }

    @Override
    public Result getEquipmentVideoDetail(Integer videoId) {
        try {
            //校验
            if (null == videoId) {
                return ResultUtil.result(ERR_CODE, "视频id不能为空!");
            }

            MedicalEquipmentVideoExample example = new MedicalEquipmentVideoExample();
            MedicalEquipmentVideoExample.Criteria criteria = example.createCriteria();
            criteria.andStatusEqualTo(CommontBooleanStatusType.YES.getCode()).andIdEqualTo(videoId);
            example.setOrderByClause("category_code asc");

            List<MedicalEquipmentVideo> equipmentVideoList = equipmentVideoMapper.selectByExample(example);

            if (ObjectUtils.isEmpty(equipmentVideoList)) {
                return ResultUtil.result(ERR_CODE, "视频不存在!");
            }

            MedicalEquipmentVideo equipmentVideo = equipmentVideoList.get(0);
            EquipmentVideoPO equipmentVideoPO = new EquipmentVideoPO();
            copyDto2Po(equipmentVideo, equipmentVideoPO);


            return ResultUtil.result(equipmentVideoPO);
        } catch (Exception e) {
            log.error("后管-查看视频信息详情时发生异常，=, ", e);
            return ResultUtil.result(ERR_CODE, e.getMessage());
        }
    }

    @Override
    public Result updateEquipmentVideo(EquipmentVideoVO equipmentVideoVO) {
        try {
            //校验
            if (null == equipmentVideoVO || null == equipmentVideoVO.getId()) {
                return ResultUtil.result(ERR_CODE, "视频id不能为空!");
            }
            if (ObjectUtils.isEmpty(equipmentVideoVO.getOssObjectName())) {
                throw new BaseException("视频资源OSS对象名称不能为空，请检查！");
            }

            MedicalEquipmentVideoExample example = new MedicalEquipmentVideoExample();
            MedicalEquipmentVideoExample.Criteria criteria = example.createCriteria();
            criteria.andStatusEqualTo(CommontBooleanStatusType.YES.getCode()).andIdEqualTo(equipmentVideoVO.getId());
            example.setOrderByClause("category_code asc");

            List<MedicalEquipmentVideo> equipmentVideoList = equipmentVideoMapper.selectByExample(example);

            if (ObjectUtils.isEmpty(equipmentVideoList)) {
                return ResultUtil.result(ERR_CODE, "视频不存在!");
            }

            MedicalEquipmentVideo equipmentVideo = equipmentVideoList.get(0);
            copyVo2Dto(equipmentVideoVO, equipmentVideo, OperationType.UPDATE);

            equipmentVideoMapper.updateByPrimaryKey(equipmentVideo);
            return ResultUtil.result(new HashMap<>());
        } catch (Exception e) {
            log.error("后管-更新视频信息时发生异常，=, ", e);
            return ResultUtil.result(ERR_CODE, e.getMessage());
        }
    }

    @Override
    public Result deleteEquipmentVideo(EquipmentVideoVO equipmentVideoVO) {
        try {
            //校验
            if (null == equipmentVideoVO || null == equipmentVideoVO.getId()) {
                return ResultUtil.result(ERR_CODE, "视频id不能为空!");
            }
            MedicalEquipmentVideoExample example = new MedicalEquipmentVideoExample();
            MedicalEquipmentVideoExample.Criteria criteria = example.createCriteria();
            criteria.andStatusEqualTo(CommontBooleanStatusType.YES.getCode()).andIdEqualTo(equipmentVideoVO.getId());
            example.setOrderByClause("category_code asc");

            List<MedicalEquipmentVideo> equipmentVideoList = equipmentVideoMapper.selectByExample(example);
            if (ObjectUtils.isEmpty(equipmentVideoList)) {
                return ResultUtil.result(ERR_CODE, "视频不存在!");
            }
            MedicalEquipmentVideo equipmentVideo = equipmentVideoList.get(0);

            equipmentVideoMapper.deleteByPrimaryKey(equipmentVideo.getId());
            return ResultUtil.result(new HashMap<>());
        } catch (Exception e) {
            log.error("后管-删除视频信息时发生异常，=, ", e);
            return ResultUtil.result(ERR_CODE, e.getMessage());
        }
    }

    @Override
    public ResponseEntity<byte[]> createMiniProgramCode(EquipVideoMiniQrCodeVO equipVideoMiniQrCodeVO) {
        //校验问卷id不能为空
        if (null == equipVideoMiniQrCodeVO.getGoodsCode()) {
            throw new BaseException("货号不能为空！");
        }
        //校验页面路径不能为空
        if (ObjectUtils.isEmpty(equipVideoMiniQrCodeVO.getPageUrl())) {
            throw new BaseException("指定的页面路径不能为空！");
        }
        ResponseEntity<String> entity = restTemplate.getForEntity("https://sjzx.zzjdyf.com/servers/mall/user/switch/token", String.class);
        // 将JSON字符串转换为JSONObject
        JSONObject jsonObject = JSONObject.parseObject(entity.getBody());
        // 从JSONObject中提取data字段的值
        String accessToken = jsonObject.getString("data");
        JSONObject reqJson = new JSONObject();
        reqJson.put("scene", String.valueOf(equipVideoMiniQrCodeVO.getGoodsCode()));
        reqJson.put("page", equipVideoMiniQrCodeVO.getPageUrl());
        byte[] bytes = restTemplate.postForObject("https://api.weixin.qq.com/wxa/getwxacodeunlimit?access_token=" + accessToken, reqJson, byte[].class);
        // 设置响应头
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_PNG); // 微信接口通常返回 PNG 格式
        headers.add("Content-Disposition", "attachment; filename=货号_" + equipVideoMiniQrCodeVO.getGoodsCode() + ".png");
        headers.setCacheControl("no-cache");
        String fileNewName = "admin/" + "货号_" + equipVideoMiniQrCodeVO.getGoodsCode() + ".png";
        String filePath = aliOssUtil.upload(bytes, fileNewName);
        //equipmentVideoMapper.updateSurveyPaperMiniCode(equipVideoMiniQrCodeVO.getGoodsCode(), filePath);
        // 返回响应实体
        return new ResponseEntity<>(bytes, headers, HttpStatus.OK);
    }

    @Override
    public Result userQueryEquipmentVideo(PageCondition pageCondition, String equipmentName, String goodsCode) {
        try {
            //校验
            if (ObjectUtils.isEmpty(goodsCode)) {
                return ResultUtil.result(ERR_CODE, "视频id不能为空!");
            }

            MedicalEquipmentVideoExample example = new MedicalEquipmentVideoExample();
            MedicalEquipmentVideoExample.Criteria criteria = example.createCriteria();
            /*if (null != categoryName && !"".equals(categoryName.trim())) {
                criteria.andCategoryNameEqualTo(categoryName);
            }*/
            if (null != equipmentName && !"".equals(equipmentName.trim())) {
                criteria.andEquipmentNameLike("%" + equipmentName + "%");
            }
            if (!"".equals(goodsCode.trim())) {
                criteria.andGoodsCodeEqualTo(goodsCode);
            }
            criteria.andStatusEqualTo(CommontBooleanStatusType.YES.getCode());
            example.setOrderByClause("category_code asc");

            if (pageCondition.getIsPage()) {
                PageHelper.startPage(pageCondition.getPageNum(), pageCondition.getPageSize());
            }

            List<MedicalEquipmentVideo> equipmentVideoList = equipmentVideoMapper.selectByExample(example);
            PageInfo<MedicalEquipmentVideo> pageInfo = new PageInfo<>(equipmentVideoList);

            List<EquipmentVideoPO> poList = new ArrayList<>();
            for (MedicalEquipmentVideo equipmentVideo : equipmentVideoList) {
                EquipmentVideoPO equipmentVideoPO = new EquipmentVideoPO();
                copyDto2Po(equipmentVideo, equipmentVideoPO);
                poList.add(equipmentVideoPO);
            }

            PageInfo<EquipmentVideoPO> pageInfoPO = new PageInfo<>();
            BeanUtils.copyProperties(pageInfo, pageInfoPO);
            pageInfoPO.setList(poList);

            return ResultUtil.listResult(pageInfoPO);
        } catch (Exception e) {
            log.error("会员端-查询视频信息列表时发生异常，=, ", e);
            return ResultUtil.result(ERR_CODE, e.getMessage());
        }
    }

    /**
     * VO复制到DTO
     * @param vo
     * @param dto
     */
    private void copyVo2Dto(EquipmentVideoVO vo, MedicalEquipmentVideo dto, OperationType operationType) {
        dto.setEquipmentId(vo.getEquipmentId());
        dto.setGoodsCode(vo.getGoodsCode());
        dto.setEquipmentName(vo.getEquipmentName());
        dto.setOssObjectName(vo.getOssObjectName());
        dto.setThumbnailImage(vo.getThumbnailImage());
        dto.setVideoUrl(vo.getVideoUrl());
        dto.setRemark(vo.getRemark());
        dto.setCategoryCode(vo.getCategoryCode());
        dto.setCategoryName(vo.getCategoryName());
        dto.setStatus(CommontBooleanStatusType.YES.getCode());
        if (OperationType.INSERT.equals(operationType)) {
            dto.setClickNum(0);
            dto.setCreateTime(LocalDateTime.now());
        } else if (OperationType.UPDATE.equals(operationType)) {
            dto.setClickNum(vo.getClickNum());
            dto.setUpdateTime(LocalDateTime.now());
        }
    }

    /**
     * DTO复制的PO
     * @param videoDTO
     * @param po
     */
    private void copyDto2Po(MedicalEquipmentVideo videoDTO, EquipmentVideoPO po) {
        po.setId(videoDTO.getId());
        po.setEquipmentId(videoDTO.getEquipmentId());
        po.setGoodsCode(videoDTO.getGoodsCode());
        po.setEquipmentName(videoDTO.getEquipmentName());
        po.setOssObjectName(videoDTO.getOssObjectName());
        po.setCategoryCode(videoDTO.getCategoryCode());
        po.setCategoryName(videoDTO.getCategoryName());
        po.setVideoUrl(videoDTO.getVideoUrl());
        po.setThumbnailImage(videoDTO.getThumbnailImage());
        po.setRemark(videoDTO.getRemark());
        po.setStatus(videoDTO.getStatus());
        po.setClickNum(videoDTO.getClickNum());
        po.setStatus(videoDTO.getStatus());
        String createTimeStr = null == videoDTO.getCreateTime() ? "" : DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(videoDTO.getCreateTime());
        po.setCreateTimeStr(createTimeStr);
        String updateTimeStr = null == videoDTO.getUpdateTime() ? "" : DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(videoDTO.getUpdateTime());
        po.setUpdateTimeStr(updateTimeStr);
    }

}

