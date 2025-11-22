package com.cheshun.market.service.admin.brochures;

import com.zzjdyf.component.page.PageCondition;
import com.zzjdyf.result.Result;
import com.zzjdyf.vo.admin.brochures.EquipVideoMiniQrCodeVO;
import com.zzjdyf.vo.admin.brochures.EquipmentVideoVO;
import org.springframework.http.ResponseEntity;

/**
 * 器械视频service接口
 * @author wangzhuo
 * @date 20251110
 */
public interface EquipmentVideoService {

    /**
     * 后管-添加视频信息
     *
     * @param equipmentVideoVO
     * @return
     */
    Result addEquipmentVideoInfo(EquipmentVideoVO equipmentVideoVO);
    /**
     * 后管-查询视频信息列表
     * @param pageCondition
     * @param categoryName
     * @param equipmentName
     * @param goodsCode
     * @return
     */
    Result queryEquipmentVideoByCategory(PageCondition pageCondition, String categoryName, String equipmentName, String goodsCode);
    /**
     * 后管-查看视频信息详情
     *
     * @param videoId
     * @return
     */
    Result getEquipmentVideoDetail(Integer videoId);
    /**
     * 后管-更新视频信息
     *
     * @param equipmentVideoVO
     * @return
     */
    Result updateEquipmentVideo(EquipmentVideoVO equipmentVideoVO);
    /**
     * 后管-删除视频信息
     *
     * @param equipmentVideoVO
     * @return
     */
    Result deleteEquipmentVideo(EquipmentVideoVO equipmentVideoVO);
    /**
     * 后管-生成视频页码小程序码
     * @param equipVideoMiniQrCodeVO
     * @return
     */
    ResponseEntity<byte[]> createMiniProgramCode(EquipVideoMiniQrCodeVO equipVideoMiniQrCodeVO);
    /**
     * 会员端-查询视频信息列表
     * @param pageCondition
     * @param equipmentName
     * @param goodsCode
     * @return
     */
    Result userQueryEquipmentVideo(PageCondition pageCondition, String equipmentName, String goodsCode);
}
