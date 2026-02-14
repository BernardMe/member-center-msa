package com.cheshun.price.controller;

import com.cheshun.common.result.Result;
import com.cheshun.price.domain.vo.HardwarePriceVO;
import com.cheshun.price.mapper.HardwarePriceMapper;
import com.cheshun.price.mapper.PriceHistoryMapper;
import com.cheshun.price.service.AdminHardwarePriceService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 硬件价格控制器
 */
@Slf4j
@RestController
@RequestMapping("/admin/hardware-price")
public class AdminHardwarePriceController {

    @Autowired
    private AdminHardwarePriceService adminHardwarePriceService;
    @Autowired
    private HardwarePriceMapper hardwarePriceMapper;
    @Autowired
    private PriceHistoryMapper priceHistoryMapper;

    @ApiOperation("后管-查询商品价格记录详情")
    @GetMapping("/get")
    public Result getPriceDetail(@RequestParam(value = "id") Long id) {
        try {
            log.info("查询商品价格记录: id={}", id);
            Result result = adminHardwarePriceService.getHardwarePriceDetail(id);
            return result;
        } catch (Exception e) {
            log.error("查询商品价格记录详情失败", e);
            return Result.error("查询商品价格记录详情失败: " + e.getMessage());
        }
    }

    @ApiOperation("后管-手动更新商品价格记录")
    @PostMapping("/update")
    public Result updateHardwarePriceByHand(@RequestBody HardwarePriceVO hardwarePriceVO) {
        try {
            Result result = adminHardwarePriceService.update(hardwarePriceVO);
            return result;
        } catch (Exception e) {
            log.error("后管-手动更新商品价格记录失败", e);
            return Result.error("后管-手动更新商品价格记录失败: " + e.getMessage());
        }
    }

    @ApiOperation("后管-手动删除商品价格记录")
    @DeleteMapping("/delete")
    public Result deleteHardwarePrice(@RequestBody HardwarePriceVO hardwarePriceVO) {
        return adminHardwarePriceService.delete(hardwarePriceVO);
    }
}