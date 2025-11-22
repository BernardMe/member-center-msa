package com.cheshun.market.service.common;

import com.aliyun.oss.HttpMethod;
import com.aliyun.oss.OSS;
import com.aliyun.oss.model.*;
import com.cheshun.market.vo.commonquestion.MultipartUploadRequest;
import com.cheshun.common.properties.AliOssProperties;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;
import java.sql.Date;
import java.util.*;

@Service
@AllArgsConstructor
@Slf4j
public class AliOssService {

    @Autowired
    private OSS aliOssClient;
    @Autowired
    private AliOssProperties aliOssConfig;

    /**
     * 初始化分片上传
     */
    public String initiateMultipartUpload(String objectName) {
        try {
            InitiateMultipartUploadRequest request = new InitiateMultipartUploadRequest(
                    aliOssConfig.getBucketName(), objectName);

            InitiateMultipartUploadResult result = aliOssClient.initiateMultipartUpload(request);
            log.info("分片上传初始化成功, UploadId: {}", result.getUploadId());
            return result.getUploadId();
        } catch (Exception e) {
            log.error("分片上传初始化失败", e);
            throw new RuntimeException("分片上传初始化失败", e);
        }
    }

    /**
     * 上传分片
     */
    public PartETag uploadPart(MultipartUploadRequest request, InputStream inputStream) {
        try {

            UploadPartRequest uploadPartRequest = new UploadPartRequest();
            uploadPartRequest.setBucketName(aliOssConfig.getBucketName());
            uploadPartRequest.setKey(request.getObjectName());
            uploadPartRequest.setUploadId(request.getUploadId());
            uploadPartRequest.setInputStream(inputStream);
            uploadPartRequest.setPartSize(request.getPartSize());
            uploadPartRequest.setPartNumber(request.getPartNumber());

            UploadPartResult uploadPartResult = aliOssClient.uploadPart(uploadPartRequest);
            log.info("分片上传成功, PartNumber: {}, ETag: {}",
                    request.getPartNumber(), uploadPartResult.getPartETag().getETag());

            return uploadPartResult.getPartETag();
        } catch (Exception e) {
            log.error("分片上传失败, PartNumber: {}", request.getPartNumber(), e);
            throw new RuntimeException("分片上传失败", e);
        }
    }

    /**
     * 完成分片上传
     */
    public void completeMultipartUpload(String objectName, String uploadId, List<PartETag> partETags) {
        try {

            CompleteMultipartUploadRequest request = new CompleteMultipartUploadRequest(
                    aliOssConfig.getBucketName(), objectName, uploadId, partETags);

            aliOssClient.completeMultipartUpload(request);
            log.info("分片上传完成, Object: {}, UploadId: {}", objectName, uploadId);
        } catch (Exception e) {
            log.error("完成分片上传失败", e);
            throw new RuntimeException("完成分片上传失败", e);
        }
    }

    /**
     * 取消分片上传
     */
    public void abortMultipartUpload(String objectName, String uploadId) {
        try {

            AbortMultipartUploadRequest request = new AbortMultipartUploadRequest(
                    aliOssConfig.getBucketName(), objectName, uploadId);

            aliOssClient.abortMultipartUpload(request);
            log.info("分片上传已取消, UploadId: {}", uploadId);
        } catch (Exception e) {
            log.error("取消分片上传失败", e);
            throw new RuntimeException("取消分片上传失败", e);
        }
    }

    /**
     * 列出已上传的分片
     */
    public List<PartSummary> listParts(String objectName, String uploadId) {
        try {

            ListPartsRequest request = new ListPartsRequest(
                    aliOssConfig.getBucketName(), objectName, uploadId);

            PartListing partListing = aliOssClient.listParts(request);
            return partListing.getParts();
        } catch (Exception e) {
            log.error("获取已上传分片列表失败", e);
            throw new RuntimeException("获取已上传分片列表失败", e);
        }
    }

    /**
     * 完整的分片上传流程（适合小文件直接使用）
     */
    public String multipartUpload(File file, String objectName) {
        try {
            // 初始化上传
            String uploadId = initiateMultipartUpload(objectName);
            List<PartETag> partETags = new ArrayList<>();

            // 计算分片数量
            long fileSize = file.length();
            long partSize = aliOssConfig.getPartSize();
            int partCount = (int) (fileSize / partSize);
            if (fileSize % partSize != 0) {
                partCount++;
            }

            // 上传每个分片
            for (int i = 0; i < partCount; i++) {
                long startPos = i * partSize;
                long curPartSize = (i + 1 == partCount) ? (fileSize - startPos) : partSize;

                try (InputStream inputStream = new FileInputStream(file)) {
                    inputStream.skip(startPos);

                    UploadPartRequest uploadPartRequest = new UploadPartRequest();
                    uploadPartRequest.setBucketName(aliOssConfig.getBucketName());
                    uploadPartRequest.setKey(objectName);
                    uploadPartRequest.setUploadId(uploadId);
                    uploadPartRequest.setInputStream(inputStream);
                    uploadPartRequest.setPartSize(curPartSize);
                    uploadPartRequest.setPartNumber(i + 1);

                    UploadPartResult uploadPartResult = aliOssClient.uploadPart(uploadPartRequest);
                    partETags.add(uploadPartResult.getPartETag());
                }
            }

            // 完成上传
            completeMultipartUpload(objectName, uploadId, partETags);
            return uploadId;

        } catch (Exception e) {
            log.error("分片上传失败", e);
            throw new RuntimeException("分片上传失败", e);
        }
    }

    /**
     * 生成带自定义处理参数的URL
     */
    public String generateProcessedUrl(String objectName, String process, Integer expireSeconds) {
        try {
            if (expireSeconds == null || expireSeconds <= 0) {
                expireSeconds = 3600;
            }

            Date expiration = new Date(System.currentTimeMillis() + expireSeconds * 1000);
            GeneratePresignedUrlRequest request = new GeneratePresignedUrlRequest(
                    aliOssConfig.getBucketName(), objectName, HttpMethod.GET);
            request.setExpiration(expiration);

            if (process != null && !process.trim().isEmpty()) {
                request.setProcess(process);
            }

            URL signedUrl = aliOssClient.generatePresignedUrl(request);

            log.info("生成处理URL: {}, 处理参数: {}, 过期时间: {}秒",
                    objectName, process, expireSeconds);

            return signedUrl.toString();
        } catch (Exception e) {
            log.error("生成处理URL失败: {}", objectName, e);
            throw new RuntimeException("生成处理URL失败: " + e.getMessage(), e);
        }
    }

    /**
     * 生成视频播放URL（不进行转码处理）
     */
    public String generateVideoPlayUrl(String objectName, Integer expireSeconds) {
        try {
            if (expireSeconds == null || expireSeconds <= 0) {
                expireSeconds = 3600;
            }

            Date expiration = new Date(System.currentTimeMillis() + expireSeconds * 1000);
            GeneratePresignedUrlRequest request = new GeneratePresignedUrlRequest(
                    aliOssConfig.getBucketName(), objectName, HttpMethod.GET);
            request.setExpiration(expiration);

            // 设置响应头，确保浏览器可以正确播放
            ResponseHeaderOverrides headers = new ResponseHeaderOverrides();
            //headers.setContentType(getVideoContentType(objectName));
            headers.setContentDisposition("inline"); // 内联显示，不下载
            headers.setCacheControl("max-age=3600");
            request.setResponseHeaders(headers);

            URL signedUrl = aliOssClient.generatePresignedUrl(request);

            log.info("生成视频播放URL: {}, 过期时间: {}秒", objectName, expireSeconds);
            return signedUrl.toString();

        } catch (Exception e) {
            log.error("生成视频播放URL失败: {}", objectName, e);
            throw new RuntimeException("生成播放URL失败: " + e.getMessage(), e);
        }
    }

    /**
     * 设置对象的Content-Type（重要！）
     */
    public void setObjectContentType(String objectName) {
        try {
            String contentType = getVideoContentType(objectName);

            // 复制对象到自身，更新元数据
            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentType(contentType);

            CopyObjectRequest request = new CopyObjectRequest(
                    aliOssConfig.getBucketName(), objectName,
                    aliOssConfig.getBucketName(), objectName);
            request.setNewObjectMetadata(metadata);

            aliOssClient.copyObject(request);
            log.info("设置对象Content-Type: {} -> {}", objectName, contentType);

        } catch (Exception e) {
            log.error("设置对象Content-Type失败: {}", objectName, e);
            // 这里不抛出异常，因为不是关键操作
        }
    }

    /**
     * 获取对象信息
     */
    public Map<String, Object> getObjectInfo(String objectName) {
        try {
            ObjectMetadata metadata = aliOssClient.getObjectMetadata(aliOssConfig.getBucketName(), objectName);

            Map<String, Object> info = new HashMap<>();
            info.put("contentType", metadata.getContentType());
            info.put("size", metadata.getContentLength());
            info.put("lastModified", metadata.getLastModified());
            info.put("etag", metadata.getETag());
            info.put("storageClass", metadata.getObjectStorageClass());

            return info;

        } catch (Exception e) {
            log.error("获取对象信息失败: {}", objectName, e);
            throw new RuntimeException("获取对象信息失败: " + e.getMessage(), e);
        }
    }

    /**
     * 根据文件扩展名获取Content-Type
     */
    private String getVideoContentType(String filename) {
        String extension = getFileExtension(filename).toLowerCase();
        switch (extension) {
            case "mp4": return "video/mp4";
            case "mov": return "video/quicktime";
            case "avi": return "video/x-msvideo";
            case "wmv": return "video/x-ms-wmv";
            case "flv": return "video/x-flv";
            case "mkv": return "video/x-matroska";
            case "webm": return "video/webm";
            case "m4v": return "video/x-m4v";
            case "3gp": return "video/3gpp";
            default: return "video/mp4"; // 默认返回mp4类型
        }
    }

    private ResponseHeaderOverrides getVideoResponseHeaders() {
        ResponseHeaderOverrides headers = new ResponseHeaderOverrides();
        headers.setContentType("video/mp4");
        headers.setContentDisposition("inline"); // 内联显示，不下载
        headers.setCacheControl("max-age=3600"); // 缓存1小时
        return headers;
    }

    private String getFileExtension(String filename) {
        int lastDotIndex = filename.lastIndexOf(".");
        return lastDotIndex > 0 ? filename.substring(lastDotIndex + 1) : "";
    }

    private boolean isVideoFormat(String extension) {
        String[] videoFormats = {"mp4", "avi", "mov", "wmv", "flv", "mkv", "webm", "m4v", "3gp"};
        return Arrays.asList(videoFormats).contains(extension.toLowerCase());
    }
}


