package com.cheshun.market.rest.admin.brochures;

import com.cheshun.common.result.Result;
import com.cheshun.market.service.admin.brochures.EquipmentVideoService;
import com.cheshun.market.service.common.AliOssService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static com.cheshun.common.result.Result.ERR_CODE;


@Api(tags = "会员端-器械视频控制器")
@RestController
@RequestMapping("/admin/equipment/video")
public class AdminEquipmentVideoContrller {

    @Autowired
    private EquipmentVideoService equipmentVideoService;
    @Autowired
    private AliOssService aliOssService;

    /**
     * 直接上传文件（自动分片）
     */
    @PostMapping("/uploadAuto")
    public Result uploadFileAuto(@RequestParam("file") MultipartFile file) {
        try {
            // 创建临时文件
            File tempFile = File.createTempFile("oss_upload_", "_" + file.getOriginalFilename());
            file.transferTo(tempFile);

            String objectName = "uploads/" + UUID.randomUUID() + "/" + file.getOriginalFilename();
            String uploadId = aliOssService.multipartUpload(tempFile, objectName);

            // 删除临时文件
            tempFile.delete();

            Map<String, String> result = new HashMap<>();
            result.put("uploadId", uploadId);
            result.put("objectName", objectName);
            result.put("message", "（自动分片）文件上传成功");

            return ResultUtil.result(result);
        } catch (Exception e) {
            return ResultUtil.result(ERR_CODE, "（自动分片）文件上传失败: " + e.getMessage());
        }
    }

    @ApiOperation("添加器械视频信息")
    @PostMapping("/add")
    public Result addEquipmentVideo(
            @RequestBody EquipmentVideoVO equipmentVideoVO) {
        //UserInfo userInfo = (USERINFO) request.getAttribute(Constants.CURRENT_USER);
        return equipmentVideoService.addEquipmentVideoInfo(equipmentVideoVO);
    }

    @ApiOperation("查询医疗器械分组列表信息")
    @GetMapping("/list")
    public Result queryEquipmentByCategory(
            @PageInfoParam PageCondition pageCondition,
            @RequestParam(value = "categoryName", required = false) String categoryName,
            @RequestParam(value = "equipmentName", required = false) String equipmentName,
            @RequestParam(value = "goodsCode", required = false) String goodsCode) {
        //UserInfo userInfo = (UserInfo) request.getAttribute(Constants.CURRENT_USER);
        return equipmentVideoService.queryEquipmentVideoByCategory(pageCondition, categoryName, equipmentName, goodsCode);
    }

    @ApiOperation("添加器械视频信息")
    @GetMapping("/detail")
    public Result getEquipmentVideoDetail(
            @RequestParam Integer videoId) {
        //UserInfo userInfo = (USERINFO) request.getAttribute(Constants.CURRENT_USER);
        return equipmentVideoService.getEquipmentVideoDetail(videoId);
    }

    @ApiOperation("添加器械视频信息")
    @PostMapping("/update")
    public Result updateEquipmentVideo(
            @RequestBody EquipmentVideoVO equipmentVideoVO) {
        //UserInfo userInfo = (USERINFO) request.getAttribute(Constants.CURRENT_USER);
        return equipmentVideoService.updateEquipmentVideo(equipmentVideoVO);
    }

    @PostMapping("/delete")
    public Result deleteEquipmentVideo(
            @RequestBody EquipmentVideoVO equipmentVideoVO) {
        //UserInfo userInfo = (USERINFO) request.getAttribute(Constants.CURRENT_USER);
        return equipmentVideoService.deleteEquipmentVideo(equipmentVideoVO);
    }

}
