//package com.zzjdyf.mall.feign.ocr;
//
//import com.zzjdyf.mall.constant.AuthServerConstant;
//import com.zzjdyf.mall.feign.ocr.entity.*;
//import com.zzjdyf.mall.feign.ocr.request.OCRGetRequest;
//import com.zzjdyf.mall.feign.ocr.response.OCRGetResponse;
//import lombok.RequiredArgsConstructor;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Component;
//
//import java.net.URI;
//
///**
// * 功能描述 短信代理类
// *
// * @author xueqing wang
// * @date 2020/12/16 12:14
// */
//@Component
//@RequiredArgsConstructor
//public class OCRBroker {
//    @Value("${zzjdyf.ocr.url}")
//    String url;
//    /**
//     * ocr服务feign
//     */
//    private final OCRFeignClient ocrFeignClient;
//
//
//    /**
//     * 功能描述: 易道OCR身份证识别开始
//     *
//     * @param ocrGetRequest
//     * @return OCRGetResponse
//     * @author xueqing wang
//     * @date 2020/12/16 12:14
//     */
//    public IdCard ocrIdCard(OCRGetRequest ocrGetRequest, String photoType) throws Exception {
//        OCRGetResponse ocrGetResponse = ocrFeignClient.ocrIdCard(new URI(url), ocrGetRequest);
//        switch (photoType) {
//            case AuthServerConstant.ID_CARD_HOME:
//                return IdCardHome.builder()
//                        .birthdate(ocrGetResponse.getResult().get("birthdate").get("words").toString())
//                        .name(ocrGetResponse.getResult().get("name").get("words").toString())
//                        .gender(ocrGetResponse.getResult().get("gender").get("words").toString())
//                        .address(ocrGetResponse.getResult().get("address").get("words").toString())
//                        .idno(ocrGetResponse.getResult().get("idno").get("words").toString())
//                        .nationality(ocrGetResponse.getResult().get("nationality").get("words").toString())
//                        .build();
//            case AuthServerConstant.ID_CARD_VICE:
//                return IdCardVice.builder()
//                        .valid(ocrGetResponse.getResult().get("valid").get("words").toString())
//                        .issued(ocrGetResponse.getResult().get("issued").get("words").toString())
//                        .build();
//            default:
//                return null;
//        }
//    }
//
//    /**
//     * 功能描述: 易道OCR银行卡识别开始
//     *
//     * @param ocrGetRequest
//     * @return OCRGetResponse
//     * @author xueqing wang
//     * @date 2020/12/16 12:14
//     */
//    public BankCard ocrBankCard(OCRGetRequest ocrGetRequest) throws Exception {
//        OCRGetResponse ocrGetResponse = ocrFeignClient.ocrIdCard(new URI(url), ocrGetRequest);
//        return BankCard.builder()
//                .bankName(ocrGetResponse.getResult().get("bank_name").get("words").toString())
//                .cardType(ocrGetResponse.getResult().get("card_type").get("words").toString())
//                .cardName(ocrGetResponse.getResult().get("card_name").get("words").toString())
//                .cardNo(ocrGetResponse.getResult().get("card_no").get("words").toString())
//                .validStart(ocrGetResponse.getResult().get("valid_start").get("words").toString())
//                .validThru(ocrGetResponse.getResult().get("valid_thru").get("words").toString())
//                .build();
//    }
//
//
//    /**
//     * 功能描述: 易道OCR行驶证识别开始
//     *
//     * @param ocrGetRequest
//     * @return OCRGetResponse
//     * @author xueqing wang
//     * @date 2020/12/16 12:14
//     */
//    public VehicleLicense ocrVehicleLicense(OCRGetRequest ocrGetRequest, String photoType) throws Exception {
//        OCRGetResponse ocrGetResponse = ocrFeignClient.ocrIdCard(new URI(url), ocrGetRequest);
//        switch (photoType) {
//            case AuthServerConstant.MAIN_DRIVING:
//                return VehicleLicenseMainDriving.builder()
//                        .address(ocrGetResponse.getResult().get("address").get("words").toString())
//                        .engineNo(ocrGetResponse.getResult().get("engine_no").get("words").toString())
//                        .model(ocrGetResponse.getResult().get("model").get("words").toString())
//                        .issueDate(ocrGetResponse.getResult().get("issue_date").get("words").toString())
//                        .owner(ocrGetResponse.getResult().get("owner").get("words").toString())
//                        .plateno(ocrGetResponse.getResult().get("plateno").get("words").toString())
//                        .vehicleType(ocrGetResponse.getResult().get("vehicle_type").get("words").toString())
//                        .registerDate(ocrGetResponse.getResult().get("register_date").get("words").toString())
//                        .vin(ocrGetResponse.getResult().get("vin").get("words").toString())
//                        .useCharacter(ocrGetResponse.getResult().get("use_character").get("words").toString())
//                        .build();
//            case AuthServerConstant.SECOND_DRIVING:
//                return VehicleLicenseSecondDriving.builder()
//                        .approvedLoad(ocrGetResponse.getResult().get("approved_load").get("words").toString())
//                        .notes(ocrGetResponse.getResult().get("notes").get("words").toString())
//                        .plateno(ocrGetResponse.getResult().get("plateno").get("words").toString())
//                        .size(ocrGetResponse.getResult().get("size").get("words").toString())
//                        .totalMass(ocrGetResponse.getResult().get("total_mass").get("words").toString())
//                        .testRecord(ocrGetResponse.getResult().get("test_record").get("words").toString())
//                        .tractionMass(ocrGetResponse.getResult().get("traction_mass").get("words").toString())
//                        .mass(ocrGetResponse.getResult().get("mass").get("words").toString())
//                        .documentNo(ocrGetResponse.getResult().get("document_no").get("words").toString())
//                        .capacity(ocrGetResponse.getResult().get("capacity").get("words").toString())
//                        .build();
//            default:
//                return null;
//        }
//
//    }
//
//}
