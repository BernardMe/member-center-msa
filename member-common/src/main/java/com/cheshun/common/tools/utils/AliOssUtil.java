package com.cheshun.common.tools.utils;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.model.PutObjectResult;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.io.ByteArrayInputStream;

@Data
@AllArgsConstructor
@Slf4j
public class AliOssUtil {

    private String endpoint;
    private String accessKeyId;
    private String accessKeySecret;
    private String bucketName;

    /**
     * AliOSS-Hyzx-文件上传
     *
     * @param bytes
     * @param objectName
     * @return
     */
    public String upload(byte[] bytes, String objectName) {

        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

        try {
            // 创建PutObject请求。
            PutObjectResult result = ossClient.putObject(bucketName, objectName, new ByteArrayInputStream(bytes));
            log.info("上传结果{}",result);
            log.info("oss上传成功, Message: Request ID:{}", result.getRequestId());
        } catch (OSSException oe) {
            log.error("oss上传失败,Error Message:" + oe.getErrorMessage()
                    +"Error Code:" + oe.getErrorCode()
                    +"Request ID:" + oe.getRequestId()
                    +"Host ID:" + oe.getHostId());
        } catch (ClientException ce) {
            log.error("Caught an ClientException,Error Message: {}", ce.getMessage());
        } finally {
            if (ossClient != null) {
                ossClient.shutdown();
            }
        }

        //文件访问路径规则 https://BucketName.Endpoint/ObjectName
        StringBuilder stringBuilder = new StringBuilder("https://");
        stringBuilder
                .append(bucketName)
                .append(".")
                .append(endpoint)
                .append("/")
                .append(objectName);

        log.info("文件上传成功到:{}", stringBuilder.toString());

        return stringBuilder.toString();
    }
}
