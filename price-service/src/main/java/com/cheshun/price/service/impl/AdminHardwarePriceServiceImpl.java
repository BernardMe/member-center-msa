package com.cheshun.price.service.impl;

import com.cheshun.common.api.web.http.common.ListResult;
import com.cheshun.common.component.page.PageCondition;
import com.cheshun.common.enumeration.OperationType;
import com.cheshun.common.exception.BaseException;
import com.cheshun.common.result.Result;
import com.cheshun.common.tools.utils.ResultUtil;
import com.cheshun.price.domain.entity.HardwarePrice;
import com.cheshun.price.domain.entity.HardwarePriceExample;
import com.cheshun.price.mapper.HardwarePriceMapper;
import com.cheshun.price.domain.po.HardwarePricePO;
import com.cheshun.price.service.AdminHardwarePriceService;
import com.cheshun.price.domain.vo.HardwarePriceVO;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static com.cheshun.common.constant.CommonServiceConstant.SU;
import static com.cheshun.common.result.Result.ERR_CODE;
import static com.cheshun.common.tools.utils.DateTimeUtil.USUAL_DATE_TIME_FORMAT_VALUE;

/**
 * 后管-硬件价格记录Service实现类
 * @author wangzhuo
 * @date 20260205
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class AdminHardwarePriceServiceImpl implements AdminHardwarePriceService {

    /*@Autowired
    private ElasticsearchTemplate elasticsearchTemplate;*/
    @Autowired
    private HardwarePriceMapper hardwarePriceMapper;


    @Override
    public Result add(HardwarePriceVO hardwarePriceVO) {
        try {

            HardwarePrice hardwarePrice = new HardwarePrice();
            copyVo2Dto(hardwarePriceVO, hardwarePrice, OperationType.INSERT);
            hardwarePriceMapper.insert(hardwarePrice);
            return SU;
        } catch (Exception e) {
            log.error("后管-添加硬件价格记录时发生异常， hardwarePriceVO={} ", hardwarePriceVO.toString(), e);
            throw new RuntimeException("后管-添加硬件价格记录时发生异常", e);
        }
    }

    @Override
    public Result batchAdd(List<HardwarePriceVO> hotSearchVOList) {
        return null;
    }

    @Override
    public Result getHardwarePriceDetail(Long id) {
        try {
            // 检查 paperId 字段是否为空
            if (null == id) {
                throw new BaseException("问卷id不能为空");
            }
            //查询回答问卷记录
            HardwarePrice record = getHardwarePrice(id);

            HardwarePricePO po = new HardwarePricePO();
            copyDto2Po(record, po);

            return ResultUtil.result(po);
        } catch (Exception e) {
            log.error("后管-查询硬件价格记录详情时发生异常， id={}，", id, e);
            e.printStackTrace();
            return new Result(300, false, e.getMessage());
        }
    }

    @Override
    public HardwarePrice getHardwarePrice(Long id) {
        //校验
        if (ObjectUtils.isEmpty(id)) {
            throw new BaseException("记录id不能为空！");
        }

        HardwarePriceExample example = new HardwarePriceExample();
        HardwarePriceExample.Criteria criteria = example.createCriteria();
        criteria.andIdEqualTo(id);

        // 查询条件转换为DTO
        List<HardwarePrice> hardwarePriceList = hardwarePriceMapper.selectByExample(example);
        if (ObjectUtils.isEmpty(hardwarePriceList)) {
            return null;
        }
        return hardwarePriceList.get(0);
    }

    @Override
    public Result update(HardwarePriceVO hardwarePriceVO) {
        try {
            if (null == hardwarePriceVO.getId() || "".equals(hardwarePriceVO.getId())) {
                log.error("硬件价格记录id不能为空 ");
                return ResultUtil.result(ERR_CODE,"硬件价格记录id不能为空！");
            }
            HardwarePrice hardwarePrice = hardwarePriceMapper.selectByPrimaryKey(hardwarePriceVO.getId());
            if (null == hardwarePrice) {
                log.error("后管-根据id更新单个硬件价格记录， id={} 硬件价格记录不存在", hardwarePriceVO);
                return ResultUtil.result(ERR_CODE,"硬件价格记录不存在！");
            }
            copyVo2Dto(hardwarePriceVO, hardwarePrice, OperationType.UPDATE);
            // save 方法兼具新增和更新
            hardwarePriceMapper.updateByPrimaryKey(hardwarePrice);
            return SU;
        } catch (Exception e) {
            log.error("后管-根据id更新单个硬件价格记录时发生异常， id={} ", hardwarePriceVO, e);
            throw new RuntimeException("后管-根据id更新单个硬件价格记录时发生异常", e);
        }
    }

    @Override
    public Result adminQueryByCondition(PageCondition pageCondition, String platform, String productType, String brand,
                                        BigDecimal minPrice, BigDecimal maxPrice, String sortField, String sortOrder) {
        try {
            // 分页查询
            if (pageCondition == null || !pageCondition.getIsPage()) {
                pageCondition = new PageCondition();
            }

            HardwarePriceExample example = new HardwarePriceExample();
            HardwarePriceExample.Criteria criteria = example.createCriteria();
            if (!ObjectUtils.isEmpty(platform)) {
                criteria.andPlatformEqualTo(platform);
            }
            if (!ObjectUtils.isEmpty(productType)) {
                criteria.andProductTypeEqualTo(productType);
            }
            if (!ObjectUtils.isEmpty(brand)) {
                criteria.andBrandEqualTo(brand);
            }
            // 价格范围筛选
            if (minPrice != null) {
                criteria.andPriceGreaterThanOrEqualTo( minPrice);
            }
            if (maxPrice != null) {
                criteria.andPriceLessThanOrEqualTo(maxPrice);
            }

            // 排序处理
            if (!ObjectUtils.isEmpty(sortField)) {
                // 字段名转换（驼峰转下划线）
                String dbField = camelToUnderscore(sortField);

                // 验证排序字段（防止SQL注入）
                if (isValidSortField(dbField)) {
                    boolean isAsc = "asc".equalsIgnoreCase(sortOrder);
                    example.setOrderByClause(isAsc ? String.format("%s ASC", dbField) : String.format("%s DESC", dbField));
                } else {
                    log.warn("非法的排序字段: {}", sortField);
                    // 使用默认排序
                    example.setOrderByClause("create_time DESC");
                }
            } else {
                example.setOrderByClause("create_time DESC");
            }

            //强制分页
            PageHelper.startPage(pageCondition.getPageNum(), pageCondition.getPageSize());
            List<HardwarePrice> hardwarePriceList = hardwarePriceMapper.selectByExample(example);

            ListResult<HardwarePrice> activityResult = null;
            if(pageCondition.getIsPage()) {
                PageInfo<HardwarePrice> pageInfo = new PageInfo<HardwarePrice>(hardwarePriceList);
                activityResult = ResultUtil.listResult(pageInfo);
            }else {
                activityResult = ResultUtil.listResult(SU, hardwarePriceList);
            }

            List<HardwarePricePO> adminHotSearchPOList = new ArrayList<>();
            hardwarePriceList.stream().forEach(dto -> {
                HardwarePricePO po = new HardwarePricePO();
                copyDto2Po(dto, po);
                adminHotSearchPOList.add(po);
            });

            ListResult<HardwarePricePO> outResult = new ListResult<HardwarePricePO>();
            ResultUtil.copyListResultPageInfo(activityResult, outResult);
            outResult.setListData(adminHotSearchPOList);
            //结果包装
            return outResult;

        } catch (Exception e) {
            log.error("后管-按条件查询硬件价格记录（分页）列表时发生异常， isPage={} pageNum={} pageSize={}",
                    pageCondition.getIsPage(), pageCondition.getPageNum(), pageCondition.getPageSize(), e);
            throw new RuntimeException("后管-按条件查询硬件价格记录（分页）列表时发生异常", e);
        }
    }

    @Override
    public Result delete(HardwarePriceVO hardwarePriceVO) {
        try {
            if (null == hardwarePriceVO.getId() || "".equals(hardwarePriceVO.getId())) {
                log.error("硬件价格记录id不能为空 ");
                return ResultUtil.result(ERR_CODE,"硬件价格记录id不能为空！");
            }
            HardwarePrice esHotSearch = hardwarePriceMapper.selectByPrimaryKey(hardwarePriceVO.getId());
            if (null == esHotSearch) {
                log.error("后管-根据id删除单个硬件价格记录， id={} 硬件价格记录不存在", hardwarePriceVO);
                return ResultUtil.result(ERR_CODE,"硬件价格记录不存在！");
            }

            hardwarePriceMapper.deleteByPrimaryKey(hardwarePriceVO.getId());
            return SU;
        } catch (Exception e) {
            log.error("后管-根据id删除单个硬件价格记录时发生异常， id={} ", hardwarePriceVO, e);
            throw new RuntimeException("后管-根据id删除单个硬件价格记录时发生异常", e);
        }
    }

    @Override
    public void batchDelete(List<HardwarePrice> ESGoodsSearchDTOs) {



    }

    /**
     * DTO复制到PO
     * @param item
     * @param po
     */
    private void copyDto2Po(HardwarePrice item, HardwarePricePO po) {
        po.setId(item.getId());
        po.setProductName(item.getProductName());
        po.setProductModel(item.getProductModel());
        po.setProductType(item.getProductType());
        po.setBrand(item.getBrand());
        po.setPlatform(item.getPlatform());
        po.setProductUrl(item.getProductUrl());
        po.setPrice(item.getPrice());
        po.setOriginalPrice(item.getOriginalPrice());
        po.setSalesVolume(item.getSalesVolume());
        po.setShopName(item.getShopName());
        po.setImageUrl(item.getImageUrl());
        po.setDescription(item.getDescription());
        String crawlTimeStr = null == item.getCrawlTime() ? "" : DateTimeFormatter.ofPattern(USUAL_DATE_TIME_FORMAT_VALUE).format(item.getCrawlTime());
        po.setCrawlTimeStr(crawlTimeStr);
        String createTimeStr = null == item.getCreateTime() ? "" : DateTimeFormatter.ofPattern(USUAL_DATE_TIME_FORMAT_VALUE).format(item.getCreateTime());
        po.setCreateTimeStr(createTimeStr);
        String updateTimeStr = null == item.getUpdateTime() ? "" : DateTimeFormatter.ofPattern(USUAL_DATE_TIME_FORMAT_VALUE).format(item.getUpdateTime());
        po.setUpdateTimeStr(updateTimeStr);
    }

    /**
     * DTO复制到PO
     * @param dto
     * @param vo
     */
    private void copyVo2Dto(HardwarePriceVO vo, HardwarePrice dto, OperationType operationType) {
        //po.setConfigId(configDTO.getConfigId());
        dto.setId(vo.getId());
        dto.setProductName(vo.getProductName());
        dto.setProductModel(vo.getProductModel());
        dto.setProductType(vo.getProductType());
        dto.setBrand(vo.getBrand());
        dto.setPlatform(vo.getPlatform());
        dto.setProductUrl(vo.getProductUrl());
        dto.setPrice(vo.getPrice());
        dto.setOriginalPrice(vo.getOriginalPrice());
        dto.setSalesVolume(vo.getSalesVolume());
        dto.setShopName(vo.getShopName());
        dto.setImageUrl(vo.getImageUrl());
        dto.setDescription(vo.getDescription());
        if (OperationType.INSERT.equals(operationType)) {
            dto.setCrawlTime(LocalDateTime.now());
            dto.setCreateTime(LocalDateTime.now());
        } else if (OperationType.UPDATE.equals(operationType)) {
            dto.setUpdateTime(LocalDateTime.now());
        }
    }

    /**
     * 验证排序字段（防止SQL注入）
     */
    private boolean isValidSortField(String field) {
        // 白名单验证
        String[] validFields = {
                "price",
                "sales_volume",
                "crawl_time",
                "create_time",
                "update_time",
                "original_price"
        };
        for (String validField : validFields) {
            if (validField.equals(field)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 驼峰转下划线
     */
    private String camelToUnderscore(String str) {
        if (str == null || str.isEmpty()) {
            return str;
        }

        StringBuilder result = new StringBuilder();
        result.append(Character.toLowerCase(str.charAt(0)));

        for (int i = 1; i < str.length(); i++) {
            char ch = str.charAt(i);
            if (Character.isUpperCase(ch)) {
                result.append('_');
                result.append(Character.toLowerCase(ch));
            } else {
                result.append(ch);
            }
        }

        return result.toString();
    }

}
