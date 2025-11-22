package com.cheshun.market.service;

import cn.hutool.extra.qrcode.QrCodeUtil;
import cn.hutool.extra.qrcode.QrConfig;
import com.cheshun.common.exception.RRException;
import com.cheshun.common.tools.utils.FileUtil;
import com.cheshun.market.config.FileConfig;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

/**
 * @author 阿隆
 * Created on 2021/7/26 12:01 下午.
 */
@Slf4j
@Service
@AllArgsConstructor
public class FileService {
    private final FileConfig fileConfig;

    /**
     * 文件上传
     *
     * @param savePath 保存路径
     * @param file     文件
     * @return 文件保存路径
     */
    public String upload(String savePath, MultipartFile file) {
        FileUtil.checkSize(fileConfig.getMaxSize(), file.getSize());
        String suffix = FileUtil.getExtensionName(file.getOriginalFilename());
        String type = FileUtil.getFileType(suffix);
        String filePath = fileConfig.getPath().getPath() + File.separator + savePath + File.separator + type + File.separator;
        File localFile = FileUtil.upload(file, filePath);
        if (localFile == null || !localFile.exists()) {
            throw new RRException("文件上传失败");
        }
        return localFile.getPath();
    }

    /**
     * 文件下载
     *
     * @param request  HttpServletRequest
     * @param response HttpServletResponse
     * @param path     文件保存路径
     */
    public void download(HttpServletRequest request, HttpServletResponse response, String path) {
        FileUtil.downloadFile(request, response, new File(path), false);
    }

    /**
     * 创建二维码
     *
     * @param response HttpServletResponse
     * @param url      链接
     */
    public void createQR(HttpServletResponse response, String url) throws IOException {
        File tempDir = new File(fileConfig.getPath().getPath() + File.separator + "temp" + File.separator);
        tempDir = FileUtil.mkdir(tempDir);
        String fileName = System.currentTimeMillis() + "_qr.png";
        File tempQRFile = new File(tempDir, fileName);
        QrCodeUtil.generate(
                url,
                QrConfig.create()
//                        .setImg(ImgUtil.read(fileConfig.getQRLogoFile().getInputStream()))
                        .setWidth(fileConfig.getQrWidth())
                        .setHeight(fileConfig.getQrHeight())
                        .setRatio(fileConfig.getQrLogoRatio())
                        .setMargin(fileConfig.getQrMargin()),
                tempQRFile);
        response.setContentType("image/png");
        response.setHeader("Content-Disposition", "inline; filename=" + fileName);
        FileUtil.writeToStream(tempQRFile, response.getOutputStream());
        response.flushBuffer();
        tempQRFile.delete();
    }
}

