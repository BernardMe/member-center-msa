package com.zzjdyf.market.rest.app;

import com.zzjdyf.common.annotation.NoToken;
import com.zzjdyf.market.service.FileService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author 阿隆
 * Created on 2021/7/26 12:03 下午.
 */
@Slf4j
@RestController
@RequestMapping("clsapi/market/etc/file")
@AllArgsConstructor
public class FileController {
    private final FileService fileService;

    @NoToken
    @GetMapping("download")
    public void download(HttpServletRequest request, HttpServletResponse response, String path) {
        fileService.download(request, response, path);
    }

    @NoToken
    @GetMapping("downloadQR")
    public void createQR(HttpServletResponse response, String url) throws IOException {
        fileService.createQR(response, url);
    }
}
