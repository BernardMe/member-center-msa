package com.cheshun.market.vo.commonquestion;

import lombok.Data;

@Data
public class MultipartUploadRequest {
    private String objectName;    // 对象名称
    private String uploadId;      // 上传ID（用于续传）
    private Integer partNumber;   // 分片序号
    private Long partSize;        // 分片size
    private String md5;          // 分片MD5
}