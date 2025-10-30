package com.zzjdyf.market.rest.admin;

import com.zzjdyf.common.annotation.AdminApi;
import com.zzjdyf.common.annotation.NoToken;
import com.zzjdyf.common.annotation.SuperToken;
import com.zzjdyf.market.service.YunZhangHuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * @author 阿隆
 * Created on 2021/8/5 3:23 下午.
 */
@RestController
@RequestMapping("clsapi/market/etc/admin/yunzhanghu")
@Api(tags = "后管系统-云账户管理API")
@AllArgsConstructor
public class YunZhangHuAdminController {
    private final YunZhangHuService yunZhangHuService;

    /**
     * 身份证实名验证
     *
     * @param token    访问token
     * @param idCard   身份证号 String 是 身份证号
     * @param realName 姓名 String 是 姓名
     */
    @SuperToken(token = {"8ee6b16b-6b1d-ac50-993a-e7b13acb2afa"})
    @AdminApi
    @GetMapping("realNameVerify")
    @ApiOperation("身份证实名验证")
    public void realNameVerify(@RequestHeader("token") String token,
                               String idCard,
                               String realName) {
        yunZhangHuService.realNameVerify(idCard, realName);
    }

    /**
     * 查询银行卡信息
     *
     * @param token  访问token
     * @param cardNo 银行卡号
     * @return Map
     */
    @SuperToken(token = {"8ee6b16b-6b1d-ac50-993a-e7b13acb2afa"})
    @AdminApi
    @GetMapping("bankCardInfoQuery")
    @ApiOperation("查询银行卡信息")
    public Map<String, Object> bankCardInfoQuery(@RequestHeader("token") String token,
                                                 String cardNo) {
        return yunZhangHuService.bankCardInfoQuery(cardNo);
    }

    /**
     * 银行卡三要素验证
     *
     * @param token    访问token
     * @param idCard   身份证号 String 是 身份证号
     * @param realName 姓名 String 是 姓名
     * @param cardNo   银行卡号 String 是 银行卡号
     */
    @SuperToken(token = {"8ee6b16b-6b1d-ac50-993a-e7b13acb2afa"})
    @AdminApi
    @GetMapping("threeFactorVerify")
    @ApiOperation("银行卡三要素验证")
    public void threeFactorVerify(@RequestHeader("token") String token,
                                  String idCard,
                                  String realName,
                                  String cardNo) {
        yunZhangHuService.threeFactorVerify(idCard, realName, cardNo);
    }

    /**
     * 银行卡四要素验证
     *
     * @param token    访问token
     * @param idCard   身份证号 String 是 身份证号
     * @param realName 姓名 String 是 姓名
     * @param cardNo   银行卡号 String 是 银行卡号
     * @param mobile   银行预留手机号 String 是 银行预留手机号
     */
    @SuperToken(token = {"8ee6b16b-6b1d-ac50-993a-e7b13acb2afa"})
    @AdminApi
    @GetMapping("fourFactorVerify")
    @ApiOperation("银行卡四要素验证")
    public void fourFactorVerify(@RequestHeader("token") String token,
                                 String idCard,
                                 String realName,
                                 String cardNo,
                                 String mobile) {
        yunZhangHuService.fourFactorVerify(idCard, realName, cardNo, mobile);
    }

    /**
     * 银行卡四要素鉴权请求（下发短信验证码）
     *
     * @param token    访问token
     * @param idCard   身份证号 String 是 身份证号
     * @param realName 姓名 String 是 姓名
     * @param cardNo   银行卡号 String 是 银行卡号
     * @param mobile   银行预留手机号 String 是 银行预留手机号
     * @return ref 交易凭证 String 交易凭证
     */
    @SuperToken(token = {"8ee6b16b-6b1d-ac50-993a-e7b13acb2afa"})
    @AdminApi
    @GetMapping("fourFactorVerifySendMsg")
    @ApiOperation("银行卡四要素鉴权请求（下发短信验证码）")
    public String fourFactorVerifySendMsg(@RequestHeader("token") String token,
                                          String idCard,
                                          String realName,
                                          String cardNo,
                                          String mobile) {
        return yunZhangHuService.fourFactorVerifySendMsg(idCard, realName, cardNo, mobile);
    }

    /**
     * 银行卡四要素确认鉴权（上传短信验证码）
     *
     * @param token    访问token
     * @param idCard   身份证号 String 是 身份证号
     * @param realName 姓名 String 是 姓名
     * @param cardNo   银行卡号 String 是 银行卡号
     * @param mobile   银行预留手机号 String 是 银行预留手机号
     * @param captcha  短信验证码 String 是 短信验证码
     * @param ref      交易凭证 String 是 鉴权请求接口响应的交易凭证
     */
    @SuperToken(token = {"8ee6b16b-6b1d-ac50-993a-e7b13acb2afa"})
    @AdminApi
    @GetMapping("fourFactorVerifyConfirm")
    @ApiOperation("银行卡四要素确认鉴权（上传短信验证码）")
    public void fourFactorVerifyConfirm(@RequestHeader("token") String token,
                                        String idCard,
                                        String realName,
                                        String cardNo,
                                        String mobile,
                                        String captcha,
                                        String ref) {
        yunZhangHuService.fourFactorVerifyConfirm(idCard, realName, cardNo, mobile, captcha, ref);
    }

    /**
     * 银行卡实时下单
     *
     * @param token     访问token
     * @param orderId   商户订单号 String 是 自定义，唯一，64 个英文字符以内
     * @param realName  姓名 String 是 银行卡开户姓名
     * @param cardNo    银行卡号 String 是 银行开户卡号
     * @param idCard    身份证号 String 是 身份证号
     * @param phoneNo   手机号 String 否 用户手机号
     * @param pay       打款金额 String 是 用户实际到账金额，单位：元，支持小数点后两位
     * @param payRemark 打款备注 String 否 最大 20 个字符 不支持特殊字符 ' " & | @% ( ) - : # + / < > ¥ \ ,
     * @return Map
     */
    @SuperToken(token = {"8ee6b16b-6b1d-ac50-993a-e7b13acb2afa"})
    @AdminApi
    @GetMapping("bankCardOrder")
    @ApiOperation("银行卡实时下单")
    public Map<String, String> bankCardOrder(@RequestHeader("token") String token,
                                             String orderId,
                                             String realName,
                                             String cardNo,
                                             String idCard,
                                             String phoneNo,
                                             BigDecimal pay,
                                             @RequestParam(required = false) String payRemark) {
        return yunZhangHuService.bankCardOrder(orderId, realName, cardNo, idCard, phoneNo, pay, payRemark);
    }

    /**
     * 查询单笔订单信息
     *
     * @param token   访问token
     * @param orderId 商户订单号 String 是 自定义，唯一，64 个英文字符以内
     * @return Map
     */
    @SuperToken(token = {"8ee6b16b-6b1d-ac50-993a-e7b13acb2afa"})
    @AdminApi
    @GetMapping("orderQuery")
    @ApiOperation("查询单笔订单信息")
    public Map<String, String> orderQuery(@RequestHeader("token") String token, String orderId) {
        return yunZhangHuService.orderQuery(orderId);
    }

    /**
     * 取消待打款订单
     *
     * @param token   访问token
     * @param orderId 商户订单号
     */
    @SuperToken(token = {"8ee6b16b-6b1d-ac50-993a-e7b13acb2afa"})
    @AdminApi
    @GetMapping("cancelOrder")
    @ApiOperation("取消待打款订单")
    public void cancelOrder(@RequestHeader("token") String token,
                            String orderId) {
        yunZhangHuService.cancelOrder(orderId);
    }

    /**
     * 下单回调消息通知
     *
     * @param data      通知参数
     * @param mess      通知参数
     * @param timestamp 通知参数
     * @param sign      通知参数
     * @param sign_type 通知参数
     * @param response  {@link HttpServletResponse}
     * @return map
     */
    @NoToken
    @PostMapping(value = "notify", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    @ApiOperation("下单回调消息通知")
    public Map<String, String> orderNotify(String data,
                                           String mess,
                                           String timestamp,
                                           String sign,
                                           String sign_type,
                                           HttpServletResponse response) {
        return yunZhangHuService.orderNotify(data,
                mess,
                timestamp,
                sign,
                sign_type,
                response);
    }

    /**
     * 查询商户余额
     *
     * @param token 访问token
     * @return Map
     */
    @SuperToken(token = {"8ee6b16b-6b1d-ac50-993a-e7b13acb2afa"})
    @AdminApi
    @GetMapping("dealerBalanceQuery")
    @ApiOperation("查询商户余额")
    public Map<String, String> dealerBalanceQuery(@RequestHeader("token") String token) {
        return yunZhangHuService.dealerBalanceQuery();
    }

    /**
     * 查询商户充值记录
     *
     * @param token   访问token
     * @param beginAt 查询开始日期 String 是 格式：yyyy-MM-dd
     * @param endAt   查询结束日期 String 是 格式：yyyy-MM-dd
     * @return list
     */
    @SuperToken(token = {"8ee6b16b-6b1d-ac50-993a-e7b13acb2afa"})
    @AdminApi
    @GetMapping("rechargeRecordQuery")
    @ApiOperation("查询商户充值记录")
    public List<Map<String, String>> rechargeRecordQuery(@RequestHeader("token") String token,
                                                         String beginAt,
                                                         String endAt) {
        return yunZhangHuService.rechargeRecordQuery(beginAt, endAt);
    }

    /**
     * 查询日订单数据
     *
     * @param token     访问token
     * @param orderDate 订单查询日期 String 是 格式：yyyy-MM-dd
     * @param offset    偏移量 Number 是 偏移量，最小从 0 开始
     * @return list
     */
    @SuperToken(token = {"8ee6b16b-6b1d-ac50-993a-e7b13acb2afa"})
    @AdminApi
    @GetMapping("dailyBillFileQuery")
    @ApiOperation("查询日订单数据")
    public List<Map<String, Object>> dailyBillFileQuery(@RequestHeader("token") String token,
                                                        String orderDate,
                                                        Integer offset) {
        return yunZhangHuService.dailyBillFileQuery(orderDate, offset);
    }

    /**
     * 查询商户已开具发票金额和待开具发票金额
     *
     * @param token 访问token
     * @param year  查询年份 Number 否 按年份查询已开和待开发票金额，不传代表当前年份
     * @return Map
     */
    @SuperToken(token = {"8ee6b16b-6b1d-ac50-993a-e7b13acb2afa"})
    @AdminApi
    @GetMapping("receiptQuery")
    @ApiOperation("查询商户已开具发票金额和待开具发票金额")
    public Map<String, String> receiptQuery(@RequestHeader("token") String token,
                                            Integer year) {
        return yunZhangHuService.receiptQuery(year);
    }

    /**
     * 查询可开票额度
     *
     * @param token 访问token
     * @return Map
     */
    @SuperToken(token = {"8ee6b16b-6b1d-ac50-993a-e7b13acb2afa"})
    @AdminApi
    @GetMapping("invoiceAmountQuery")
    @ApiOperation("查询可开票额度")
    public Map<String, Object> invoiceAmountQuery(@RequestHeader("token") String token) {
        return yunZhangHuService.invoiceAmountQuery();
    }

    /**
     * 开票申请
     *
     * @param token          访问token
     * @param invoiceApplyId 发票申请编号 String 是 发票申请编号
     * @param amount         申请开票金额 String 是 单位：元，支持小数点后两位
     * @return map
     */
    @SuperToken(token = {"8ee6b16b-6b1d-ac50-993a-e7b13acb2afa"})
    @AdminApi
    @GetMapping("invoiceApply")
    @ApiOperation("开票申请")
    public Map<String, Object> invoiceApply(@RequestHeader("token") String token,
                                            String invoiceApplyId,
                                            BigDecimal amount) {
        return yunZhangHuService.invoiceApply(invoiceApplyId, amount);
    }

    /**
     * 查询开票申请状态
     *
     * @param token          访问token
     * @param invoiceApplyId 发票申请编号
     * @return map
     */
    @SuperToken(token = {"8ee6b16b-6b1d-ac50-993a-e7b13acb2afa"})
    @AdminApi
    @GetMapping("invoiceApplyStatusQuery")
    @ApiOperation("查询开票申请状态")
    public Map<String, Object> invoiceApplyStatusQuery(@RequestHeader("token") String token,
                                                       String invoiceApplyId) {
        return yunZhangHuService.invoiceApplyStatusQuery(invoiceApplyId);
    }

    /**
     * 下载发票PDF
     *
     * @param token          访问token
     * @param invoiceApplyId 发票申请编号
     * @return map
     */
    @SuperToken(token = {"8ee6b16b-6b1d-ac50-993a-e7b13acb2afa"})
    @AdminApi
    @GetMapping("invoicePDF")
    @ApiOperation("下载发票PDF")
    public Map<String, String> invoicePDF(@RequestHeader("token") String token,
                                          String invoiceApplyId) {
        return yunZhangHuService.invoicePDF(invoiceApplyId);
    }
}
