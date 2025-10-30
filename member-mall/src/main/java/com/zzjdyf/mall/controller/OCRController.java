//package com.zzjdyf.mall.controller;
//
//import com.zzjdyf.mall.constant.AuthServerConstant;
//import com.zzjdyf.mall.feign.ocr.OCRBroker;
//import com.zzjdyf.mall.feign.ocr.entity.*;
//import com.zzjdyf.mall.feign.ocr.request.OCRGetRequest;
//import com.zzjdyf.mall.feign.ocr.entity.OCR;
//import lombok.RequiredArgsConstructor;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.util.Base64;
//
///**
// * 易道ocr控制层
// * @author xueqing wang
// * @date 2021年6月16日 10:40:59
// */
//@RestController
//@RequiredArgsConstructor
//public class OCRController {
//
//
//    /**
//     * ocr代理类
//     */
//    private final OCRBroker ocrBroker;
//
//
//    /**
//     * 功能描述: 易道OCR识别
//     *
//     * @param file
//     * @return OCRGetResponse
//     * @author xueqing wang
//     * @date 2020/12/16 12:14
//     */
//    @PostMapping("/ocr")
//    public OCR ocr(MultipartFile file, String photoType) throws Exception {
//        OCRGetRequest build = OCRGetRequest.builder()
//                .imageBase64(Base64.getEncoder().encodeToString(file.getBytes()))
//                .build();
//        switch (photoType) {
//            case AuthServerConstant.BANK_PHOTO:
//                return ocrBroker.ocrBankCard(build);
//            case AuthServerConstant.ID_CARD_HOME:
//            case AuthServerConstant.ID_CARD_VICE:
//                return ocrBroker.ocrIdCard(build,photoType);
//            case AuthServerConstant.MAIN_DRIVING:
//            case AuthServerConstant.SECOND_DRIVING:
//                return ocrBroker.ocrVehicleLicense(build,photoType);
//            default:
//                return null;
//        }
//    }
//
//
//}
