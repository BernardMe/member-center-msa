package com.zzjdyf.market.service;

import cn.hutool.core.map.MapBuilder;
import cn.hutool.core.util.RandomUtil;
import com.zzjdyf.common.exception.RRException;
import com.zzjdyf.market.config.ProxyConfig;
import com.zzjdyf.market.config.YunZhangHuConfig;
import com.zzjdyf.market.vo.dto.YunZhangHuResultDto;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;
import org.apache.http.HttpHost;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.util.EntityUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @author 阿隆
 * Created on 2021/8/2 4:01 下午.
 */
@Slf4j
@Service
@AllArgsConstructor
public class YunZhangHuService {
    private static final String DATA = "data";
    private static final String MESS = "mess";
    private static final String TIMESTAMP = "timestamp";
    private static final String SIGN = "sign";
    private static final String SIGN_TYPE = "sign_type";
    private static final String KEY = "key";
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    private final ProxyConfig proxyConfig;
    private final YunZhangHuConfig config;
    private final ApplicationContext applicationContext;

    /**
     * 银行卡实时下单
     *
     * @param orderId   商户订单号 String 是 自定义，唯一，64 个英文字符以内
     * @param realName  姓名 String 是 银行卡开户姓名
     * @param cardNo    银行卡号 String 是 银行开户卡号
     * @param idCard    身份证号 String 是 身份证号
     * @param phoneNo   手机号 String 否 用户手机号
     * @param pay       打款金额 String 是 用户实际到账金额，单位：元，支持小数点后两位
     * @param payRemark 打款备注 String 否 最大 20 个字符 不支持特殊字符 ' " & | @% ( ) - : # + / < > ¥ \ ,
     * @return Map
     * order_id 商户订单号 String 商户订单号，原值返回
     * ref 综合服务平台流水号 String 综合服务平台流水号，唯一
     * pay 打款金额 String 单位：元，支持小数点后两位
     */
    public Map<String, String> bankCardOrder(String orderId,
                                             String realName,
                                             String cardNo,
                                             String idCard,
                                             String phoneNo,
                                             BigDecimal pay,
                                             String payRemark) {
        Map<String, String> data = MapBuilder.<String, String>create()
                .put("order_id", orderId)
                .put("dealer_id", config.getDealerId())
                .put("broker_id", config.getBrokerId())
                .put("real_name", realName)
                .put("card_no", cardNo)
                .put("id_card", idCard)
                .put("phone_no", phoneNo)
                .put("pay", pay.toString())
                .put("payRemark", payRemark)
                .put("notify_url", config.getPaymentApi().getOrderNotifyUrl())
                .build();
        try {
            return post(config.getPaymentApi().getOrderRealtime(), OBJECT_MAPPER.writeValueAsString(data));
        } catch (Exception e) {
            throw new RRException(e.getMessage());
        }
    }

    /**
     * 查询单笔订单信息
     *
     * @param orderId 商户订单号 String 是 自定义，唯一，64 个英文字符以内
     * @return map
     * order_id 商户订单号 String 商户订单号，原值返回
     * pay 打款金额 String 单位：元，支持小数点后两位
     * broker_id 综合服务主体 ID String 综合服务主体 ID
     * dealer_id 商户 ID String 云账户为每个商户分配的唯一 ID
     * real_name 姓名 String 姓名
     * card_no 收款人账号 String 收款人账号
     * id_card 身份证号 String 身份证号
     * phone_no 手机号 String 手机号
     * status 订单状态码 String 详见附录 6.1 订单状态码
     * status_detail 订单详细状态码 String 详见附录 6.2 订单详细状态码
     * status_message 订单状态码描述 String 详见附录 6.1 订单状态码
     * status_detail_message 订单详细状态码描述 String 详见附录 6.2 订单详细状态码
     * broker_amount 综合服务主体打款金额 String 等于 pay 金额 单位：元，支持小数点后两位
     * ref 综合服务平台流水号 String 综合服务平台流水号，唯一
     * broker_bank_bill 打款交易流水号 String 综合服务平台打款交易流水号
     * withdraw_platform 提现平台 String bankpay：银行卡 alipay：支付宝 wxpay：微信
     * created_at 订单接收时间 String 精确到秒
     * finished_time 订单完成时间 String 精确到秒
     * broker_fee 综合服务主体服务费 String 单位：元，支持小数点后两位
     * broker_real_fee 余额账户支出服务费 String 单位：元，支持小数点后两位
     * broker_deduct_fee 抵扣账户支出服务费 String 单位：元，支持小数点后两位
     * pay_remark 打款备注 String 原值返回
     * user_fee 用户服务费 String 单位：元，支持小数点后两位
     * bank_name 银行名称 String 银行名称
     * encry_data 加密数据 String 当 且 仅 当 data_type 为 encryption 时，返回且仅返回该加密数据字段
     */
    public Map<String, String> orderQuery(String orderId) {
        if (canMock()) {
            int num = RandomUtil.randomInt(1, 101);
            return MapBuilder.<String, String>create().put("status", num % 2 == 0 ? "1" : "2").build();
        }
        Map<String, String> data = MapBuilder.<String, String>create()
                .put("order_id", orderId)
                .build();
        try {
            return get(config.getPaymentApi().getOrderQuery(), OBJECT_MAPPER.writeValueAsString(data));
        } catch (Exception e) {
            throw new RRException(e.getMessage());
        }
    }

    /**
     * 查询商户余额
     *
     * @return Map
     * dealer_infos 账户信息 List 账户信息
     * broker_id 综合服务主体 ID String 综合服务主体 ID
     * bank_card_balance 银行卡余额 String 单位：元，支持小数点后两位
     * is_bank_card 是否开通银行卡通道 Boolean 是否开通银行卡通道
     * alipay_balance 支付宝余额 String 单位：元，支持小数点后两位
     * is_alipay 是否开通支付宝通道 Boolean 是否开通支付宝通道
     * wxpay_balance 微信余额 String 单位：元，支持小数点后两位
     * is_wxpay 是否开通微信通道 Boolean 是否开通微信
     * rebate_fee_balance 服务费返点余 额 String 单位：元，支持小数点后两位
     * acct_balance 余额账户余额 String 单位：元，支持小数点后两位
     * total_balance 总余额 String 单位：元，支持小数点后两位
     */
    public Map<String, String> dealerBalanceQuery() {
        Map<String, String> data = MapBuilder.<String, String>create()
                .put("dealer_id", config.getDealerId())
                .build();
        try {
            return get(config.getPaymentApi().getDealerBalanceQuery(), OBJECT_MAPPER.writeValueAsString(data));
        } catch (Exception e) {
            throw new RRException(e.getMessage());
        }
    }

    /**
     * 取消待打款订单
     *
     * @param orderId 商户订单号
     */
    public void cancelOrder(String orderId) {
        Map<String, String> data = MapBuilder.<String, String>create()
                .put("dealer_id", config.getDealerId())
                .put("order_id", orderId)
                .build();
        try {
            String result = post(config.getPaymentApi().getCancelOrder(), OBJECT_MAPPER.writeValueAsString(data));
            if (!"ok".equals(result)) {
                throw new RRException("取消待打款订单失败");
            }
        } catch (Exception e) {
            throw new RRException(e.getMessage());
        }
    }

    /**
     * 银行卡信息查询接口
     *
     * @param cardNo 银行卡号
     * @return Map
     * bank_code 银行代码 String 银行代码
     * bank_name 银行名称 String 银行名称
     * card_type 卡类型 String 卡类型
     * is_support 云账户是否支持向该银行打款 Boolean 云账户是否支持向该银行打款
     */
    public Map<String, Object> bankCardInfoQuery(String cardNo) {
        Map<String, String> data = MapBuilder.<String, String>create()
                .put("card_no", cardNo)
                .put("bank_name", null)
                .build();
        try {
            return get(config.getPaymentApi().getBankCardInfoQuery(), OBJECT_MAPPER.writeValueAsString(data));
        } catch (Exception e) {
            throw new RRException(e.getMessage());
        }
    }

    /**
     * 查询商户已开具发票金额和待开具发票金额
     *
     * @param year 查询年份 Number 否 按年份查询已开和待开发票金额，不传代表当前年份
     * @return map
     * dealer_id 商户 ID String 云账户为每个商户分配的唯一 ID
     * broker_id 综合服务主体 ID String 综合服务主体 ID
     * invoiced 已开发票金额 String 单位：元，支持小数点后两位
     * not_invoiced 待开发票金额 String 单位：元，支持小数点后两位
     */
    public Map<String, String> receiptQuery(Integer year) {
        Map<String, Object> data = MapBuilder.<String, Object>create()
                .put("dealer_id", config.getDealerId())
                .put("broker_id", config.getBrokerId())
                .put("year", year)
                .build();
        try {
            return get(config.getPaymentApi().getReceiptQuery(), OBJECT_MAPPER.writeValueAsString(data));
        } catch (Exception e) {
            throw new RRException(e.getMessage());
        }
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
     * order_id 商户订单号 String 商户订单号，原值返回
     * ref 综合服务平台流水号 String 综合服务平台流水号，唯一
     * pay 打款金额 String 单位：元，支持小数点后两位
     * broker_id 综合服务主体 ID String 综合服务主体 ID
     * dealer_id 商户 ID String 云账户为每个商户分配的唯一 ID
     * real_name 姓名 String 姓名
     * card_no 收款人账号 String 收款人账号
     * id_card 身份证号 String 身份证号
     * phone_no 手机号 String 手机号
     * notes 描述信息 String 该字段已废弃
     * status 订单状态码 String 详见附录 6.1 订单状态码
     * status_detail 订单详细状态码 String 详见附录 6.2 订单详细状态码
     * status_message 订单状态码描述 String 详见附录 6.1 订单状态码
     * status_detail_message 订单详细状态码描述 String 详见附录 6.2 订单详细状态码
     * broker_amount 综合服务主体打款金额 String 等于
     * pay 金额 单位：元，支持小数点后两位
     * ref 综合服务平台流水号 String 综合服务平台流水号，唯一
     * broker_bank_bill 打款交易流水号 String 综合服务平台打款交易流水号
     * withdraw_platform 提现平台 String bankpay：银行卡 alipay：支付宝 wxpay：微信
     * created_at 订单接收时间 String 精确到秒
     * finished_time 订单完成时间 String 精确到秒
     * broker_fee 综合服务主体服务费 String 单位：元，支持小数点后两位
     * broker_real_fee 余额账户支出服务费 String 单位：元，支持小数点后两位
     * broker_deduct_fee 抵扣账户支出服务费 String 单位：元，支持小数点后两位
     * pay_remark 打款备注 String 原值返回
     * user_fee 用户服务费 String 单位：元，支持小数点后两位
     * bank_name 银行名称 String 银行名称
     */
    @SuppressWarnings("unchecked")
    public Map<String, String> orderNotify(String data,
                                           String mess,
                                           String timestamp,
                                           String sign,
                                           String sign_type,
                                           HttpServletResponse response) {
        log.info("云账户下单通知:data={} mess={} timestamp={} sign={} sign_type={}", data, mess, timestamp, sign, sign_type);
        // 数据验签
        String plain = "data=" + data + "&mess=" + mess + "&timestamp=" + timestamp + "&key=" + config.getAppKey();
        try {
            if (!verify(plain, sign, config.getPublicKeyPem())) {
                log.error("云账户下单通知验签失败:data={} mess={} timestamp={} sign={} sign_type={}", data, mess, timestamp, sign, sign_type);
                if (response != null) {
                    PrintWriter printWriter = new PrintWriter(response.getOutputStream());
                    printWriter.print("failure");
                    printWriter.flush();
                }
                return null;
            }
            // 验签成功
            if (response != null) {
                PrintWriter printWriter = new PrintWriter(response.getOutputStream());
                printWriter.print("success");
                printWriter.flush();
            }
        } catch (Exception e1) {
            log.error("云账户下单通知处理失败(2):" + data, e1);
        }
        try {
            // 3DES解密
            String dec = decode(data, config.getThreeDesKey());
            // notify_id 通知 ID String 通知 ID
            // notify_time 通知时间 String 通知时间
            // data Map<String, String>
            Map<String, Object> map = OBJECT_MAPPER.readValue(dec, new TypeReference<Map<String, Object>>() {
            });
            return (Map<String, String>) map.get("data");
        } catch (Exception e) {
            log.error("云账户下单通知处理失败(3):" + data, e);
            return null;
        }
    }

    /**
     * 身份证实名验证
     *
     * @param idCard   身份证号 String 是 身份证号
     * @param realName 姓名 String 是 姓名
     */
    public void realNameVerify(String idCard, String realName) {
        Map<String, String> data = MapBuilder.<String, String>create()
                .put("id_card", idCard)
                .put("real_name", realName)
                .build();
        try {
            post(config.getAuthenticationApi().getRealNameVerify(), OBJECT_MAPPER.writeValueAsString(data));
        } catch (Exception e) {
            throw new RRException(e.getMessage());
        }
    }

    /**
     * 银行卡四要素鉴权请求（下发短信验证码）
     *
     * @param idCard   身份证号 String 是 身份证号
     * @param realName 姓名 String 是 姓名
     * @param cardNo   银行卡号 String 是 银行卡号
     * @param mobile   银行预留手机号 String 是 银行预留手机号
     * @return ref 交易凭证 String 交易凭证
     */
    public String fourFactorVerifySendMsg(String idCard, String realName, String cardNo, String mobile) {
        Map<String, String> data = MapBuilder.<String, String>create()
                .put("id_card", idCard)
                .put("real_name", realName)
                .put("card_no", cardNo)
                .put("mobile", mobile)
                .build();
        try {
            return post(config.getAuthenticationApi().getFourFactorVerifySendMsg(), OBJECT_MAPPER.writeValueAsString(data));
        } catch (Exception e) {
            throw new RRException(e.getMessage());
        }
    }

    /**
     * 银行卡四要素确认鉴权（上传短信验证码）
     *
     * @param idCard   身份证号 String 是 身份证号
     * @param realName 姓名 String 是 姓名
     * @param cardNo   银行卡号 String 是 银行卡号
     * @param mobile   银行预留手机号 String 是 银行预留手机号
     * @param captcha  短信验证码 String 是 短信验证码
     * @param ref      交易凭证 String 是 鉴权请求接口响应的交易凭证
     */
    public void fourFactorVerifyConfirm(String idCard, String realName, String cardNo, String mobile, String captcha, String ref) {
        Map<String, String> data = MapBuilder.<String, String>create()
                .put("id_card", idCard)
                .put("real_name", realName)
                .put("card_no", cardNo)
                .put("mobile", mobile)
                .put("captcha", captcha)
                .put("ref", ref)
                .build();
        try {
            post(config.getAuthenticationApi().getFourFactorVerifyConfirm(), OBJECT_MAPPER.writeValueAsString(data));
        } catch (Exception e) {
            throw new RRException(e.getMessage());
        }
    }

    /**
     * 银行卡四要素验证
     *
     * @param idCard   身份证号 String 是 身份证号
     * @param realName 姓名 String 是 姓名
     * @param cardNo   银行卡号 String 是 银行卡号
     * @param mobile   银行预留手机号 String 是 银行预留手机号
     */
    public void fourFactorVerify(String idCard, String realName, String cardNo, String mobile) {
        Map<String, String> data = MapBuilder.<String, String>create()
                .put("id_card", idCard)
                .put("real_name", realName)
                .put("card_no", cardNo)
                .put("mobile", mobile)
                .build();
        try {
            post(config.getAuthenticationApi().getFourFactorVerify(), OBJECT_MAPPER.writeValueAsString(data));
        } catch (Exception e) {
            throw new RRException(e.getMessage());
        }
    }

    /**
     * 银行卡三要素验证
     *
     * @param idCard   身份证号 String 是 身份证号
     * @param realName 姓名 String 是 姓名
     * @param cardNo   银行卡号 String 是 银行卡号
     */
    public void threeFactorVerify(String idCard, String realName, String cardNo) {
        Map<String, String> data = MapBuilder.<String, String>create()
                .put("id_card", idCard)
                .put("real_name", realName)
                .put("card_no", cardNo)
                .build();
        try {
            post(config.getAuthenticationApi().getThreeFactorVerify(), OBJECT_MAPPER.writeValueAsString(data));
        } catch (Exception e) {
            throw new RRException(e.getMessage());
        }
    }

    /**
     * 查询可开票额度
     *
     * @return map
     * amount 可开票金额 String 单位：元，支持小数点后 两位
     * bank_name_account 系统支持的开户行及 账号 Array 系统支持的开户行及账号
     * item 开户行及账号 String 开户行及账号
     * default 是否为默认值 Boolean 是否为默认值
     * goods_services_name 系统支持的货物或应 税劳务、服务名称 Array 系统支持的货物或应税劳 务、服务名称 参数 参数名 类型 说明
     * item 货 物 或 应 税 劳 务、服务名称 String 货物或 应税劳 务、服务名称
     * default 是否为默认值 Boolean 是否为默认值
     */
    public Map<String, Object> invoiceAmountQuery() {
        Map<String, String> data = MapBuilder.<String, String>create()
                .put("dealer_id", config.getDealerId())
                .put("broker_id", config.getBrokerId())
                .build();
        try {
            return post(config.getInvoiceApi().getInvoiceAmountQuery(), OBJECT_MAPPER.writeValueAsString(data));
        } catch (Exception e) {
            throw new RRException(e.getMessage());
        }
    }

    /**
     * 开票申请
     *
     * @param invoiceApplyId 发票申请编号 String 是 发票申请编号
     * @param amount         申请开票金额 String 是 单位：元，支持小数点后两位
     * @return map
     * amount 可开票金额 String 单位：元，支持小数点后 两位
     * bank_name_account 系统支持的开户行及 账号 Array 系统支持的开户行及账号
     * item 开户行及账号 String 开户行及账号
     * default 是否为默认值 Boolean 是否为默认值
     * goods_services_name 系统支持的货物或应 税劳务、服务名称 Array 系统支持的货物或应税劳 务、服务名称 参数 参数名 类型 说明
     * item 货 物 或 应 税 劳 务、服务名称 String 货物或 应税劳 务、服务名称
     * default 是否为默认值 Boolean 是否为默认值
     */
    public Map<String, Object> invoiceApply(String invoiceApplyId, BigDecimal amount) {
        Map<String, String> data = MapBuilder.<String, String>create()
                .put("dealer_id", config.getDealerId())
                .put("broker_id", config.getBrokerId())
                .put("invoice_apply_id", invoiceApplyId)
                .put("amount", amount.toString())
                .put("invoice_type", "1") // 专票
                .build();
        try {
            return post(config.getInvoiceApi().getInvoiceApply(), OBJECT_MAPPER.writeValueAsString(data));
        } catch (Exception e) {
            throw new RRException(e.getMessage());
        }
    }

    /**
     * 查询开票申请状态
     *
     * @param invoiceApplyId 发票申请编号
     * @return map
     * status 申请结果 String 1 开票中；2 待邮寄；3 已邮寄
     * count 发票张数 Number 本次开票申请，发票张数
     * price_tax_amount 价税合计 String 单位：元，支持小数点后两位
     * price_amount 不含税金额 String 单位：元，支持小数点后两位
     * tax_amount 税额 String 单位：元，支持小数点后两位
     * invoice_type 发票类型 String 1：专票；2：普票
     * customer_name 购方名称 String 购方名称
     * customer_tax_num 纳税人识别号 String 纳税人识别号
     * customer_address_tel 购方地址、电话 String 购方地址、电话
     * bank_name_account 开户行及账号 String 开户行及账号
     * goods_services_name 货物或应税劳务、服 务名称 String 货物或应税劳务、服务名称
     * remark 发票备注 String 发票备注
     * post_type 邮寄类型 String delivery：人工送达 sf：顺丰快递
     * waybill_number 快递单号 Array 快递单号
     */
    public Map<String, Object> invoiceApplyStatusQuery(String invoiceApplyId) {
        Map<String, String> data = MapBuilder.<String, String>create()
                .put("invoice_apply_id", invoiceApplyId)
                .build();
        try {
            return post(config.getInvoiceApi().getInvoiceApplyStatusQuery(), OBJECT_MAPPER.writeValueAsString(data));
        } catch (Exception e) {
            throw new RRException(e.getMessage());
        }
    }

    /**
     * 下载发票 PDF
     *
     * @param invoiceApplyId 发票申请编号
     * @return map
     * name 文件名称 String 文件名称
     * url 下载文件 url String 下载文件 url（临时地址，超时失效）
     */
    public Map<String, String> invoicePDF(String invoiceApplyId) {
        Map<String, String> data = MapBuilder.<String, String>create()
                .put("invoice_apply_id", invoiceApplyId)
                .build();
        try {
            return post(config.getInvoiceApi().getInvoicePDF(), OBJECT_MAPPER.writeValueAsString(data));
        } catch (Exception e) {
            throw new RRException(e.getMessage());
        }
    }

    /**
     * 查询商户充值记录
     * <p>
     * 最大查询时间间隔不能超过 30 天。
     *
     * @param beginAt 查询开始日期 String 是 格式：yyyy-MM-dd
     * @param endAt   查询结束日期 String 是 格式：yyyy-MM-dd
     * @return list
     * broker_id 综合服务主体 ID String 综合服务主体 ID
     * dealer_id 商户 ID String 云账户为每个商户分配的唯一 ID
     * recharge_id 充值记录 ID String 充值记录 ID
     * actual_amount 实际到账金额 String 单位：元，支持小数点后两位
     * amount 充值金额 String 单位：元，支持小数点后两位
     * created_at 创建时间 String 创建时间
     * remark 备注 String 备注
     * recharge_channel 资金用途 String 资金用途
     * recharge_account_no 付款银行账号 String 商户充值使用的付款银行账号
     */
    public List<Map<String, String>> rechargeRecordQuery(String beginAt, String endAt) {
        Map<String, String> data = MapBuilder.<String, String>create()
                .put("begin_at", beginAt)
                .put("end_at", endAt)
                .build();
        try {
            return get(config.getDataServiceApi().getRechargeRecordQuery(), OBJECT_MAPPER.writeValueAsString(data));
        } catch (Exception e) {
            throw new RRException(e.getMessage());
        }
    }

    /**
     * 查询日订单数据
     *
     * @param orderDate 订单查询日期 String 是 格式：yyyy-MM-dd
     * @param offset    偏移量 Number 是 偏移量，最小从 0 开始
     * @return list
     * broker_id 综合服务主体 ID String 综合服务主体 ID
     * dealer_id 商户 ID String 云账户为每个商户分配的唯一 ID
     * recharge_id 充值记录 ID String 充值记录 ID
     * actual_amount 实际到账金额 String 单位：元，支持小数点后两位
     * amount 充值金额 String 单位：元，支持小数点后两位
     * created_at 创建时间 String 创建时间
     * remark 备注 String 备注
     * recharge_channel 资金用途 String 资金用途
     * recharge_account_no 付款银行账号 String 商户充值使用的付款银行账号
     */
    public List<Map<String, Object>> dailyBillFileQuery(String orderDate, Integer offset) {
        Map<String, Object> data = MapBuilder.<String, Object>create()
                .put("order_date", orderDate)
                .put("offset", offset)
                .put("length", 100)
                .build();
        try {
            return get(config.getDataServiceApi().getDailyBillFileQuery(), OBJECT_MAPPER.writeValueAsString(data));
        } catch (Exception e) {
            throw new RRException(e.getMessage());
        }
    }

    /**
     * POST 请求
     *
     * @param params 请求参数
     * @param <T>    返回类型
     * @return T
     */
    private <T> T post(String api, String params) throws Exception {
        String requestId = UUID.randomUUID().toString().replace("-", "");
        try {
            String data = encode(params, config.getThreeDesKey());
            String mess = RandomUtil.randomNumbers(8);
            long timestamp = System.currentTimeMillis() / 1000;
//            MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
//            formData.add("data", data);
//            formData.add("mess", mess);
//            formData.add("timestamp", String.valueOf(timestamp));
//            formData.add("sign_type", "rsa");
//            formData.add("sign", getSign(data, mess, timestamp, config.getAppKey(), config.getPrivateKeyPem()));
//            log.info("发送云账户请求: requestId={}\napi={}\nparams={}\nbody={}", requestId, api, params, OBJECT_MAPPER.writeValueAsString(formData));
//            HttpHeaders headers = new HttpHeaders();
//            headers.add("request-id", requestId);
//            headers.add("dealer-id", config.getDealerId());
//            headers.add("Content-Type", MediaType.APPLICATION_FORM_URLENCODED_VALUE);
//            ResponseEntity<YunZhangHuResultDto<T>> response = restTemplateWithProxy.exchange(
//                    api,
//                    HttpMethod.POST,
//                    new HttpEntity<>(formData, headers),
//                    new ParameterizedTypeReference<YunZhangHuResultDto<T>>() {
//                    });
//            if (response.getStatusCode() != HttpStatus.OK) {
//                log.error("接收云账户响应异常: requestId={}\nresponse=null", requestId);
//                throw new Exception("云账户访问异常");
//            }
//            YunZhangHuResultDto<T> responseData = response.getBody();
            List<NameValuePair> body = new ArrayList<>();
            body.add(new BasicNameValuePair(DATA, data));
            body.add(new BasicNameValuePair(MESS, mess));
            body.add(new BasicNameValuePair(TIMESTAMP, String.valueOf(timestamp)));
            body.add(new BasicNameValuePair(SIGN_TYPE, "rsa"));
            body.add(new BasicNameValuePair(SIGN, getSign(data, mess, timestamp, config.getAppKey(), config.getPrivateKeyPem())));
            log.info("发送云账户请求: requestId={}\napi={}\nparams={}\nbody={}", requestId, api, params, OBJECT_MAPPER.writeValueAsString(body));
            CloseableHttpClient httpClient = createHttpClient(proxyConfig);
            HttpPost httpPost = new HttpPost(config.getBaseUrl() + api);
            setHeader(httpPost, requestId, config.getDealerId());

            // 执行get请求.
            httpPost.setEntity(new UrlEncodedFormEntity(body, StandardCharsets.UTF_8));
            CloseableHttpResponse httpResponse = httpClient.execute(httpPost);
            String result = null;
            int statusCode = httpResponse.getStatusLine().getStatusCode();
            if (statusCode != HttpStatus.OK.value()) {
                if (statusCode == HttpStatus.FOUND.value()) {
                    result = httpResponse.getHeaders("location")[0].getValue();
                } else {
                    httpPost.abort();
                    log.error("接收云账户响应异常: requestId={}\nresponse=null", requestId);
                    throw new Exception("云账户访问异常:" + httpResponse.getStatusLine().getReasonPhrase());
                }
            } else {
                org.apache.http.HttpEntity entity = httpResponse.getEntity();
                if (entity != null) {
                    result = EntityUtils.toString(entity, StandardCharsets.UTF_8);
                }
                EntityUtils.consume(entity);
            }
            if (result == null) {
                log.error("接收云账户响应异常: requestId={}\nresponse=null", requestId);
                throw new Exception("云账户访问异常");
            }
            YunZhangHuResultDto<T> response = OBJECT_MAPPER.readValue(result, new TypeReference<YunZhangHuResultDto<T>>() {
            });
            if (response == null) {
                log.error("接收云账户响应异常: requestId={}\nresponse=null", requestId);
                throw new Exception("云账户访问异常");
            }
            log.info("接收云账户响应: requestId={}\nresponse={}", requestId, result);
            if ("0000".equals(response.getCode())) {
                // 成功
                return response.getData();
            }
            // 失败
            throw new Exception(response.getMessage());
        } catch (Exception e) {
            log.error(String.format("发送云账户请求异常: requestId=%s\napi=%s\nparams=%s", requestId, api, params), e);
            throw e;
        }
    }

    /**
     * GET 请求
     *
     * @param params 请求参数
     * @param <T>    返回类型
     * @return T
     */
    private <T> T get(String api, String params) throws Exception {
        String requestId = UUID.randomUUID().toString().replace("-", "");
        try {
            String data = encode(params, config.getThreeDesKey());
            String mess = RandomUtil.randomNumbers(8);
            long timestamp = System.currentTimeMillis() / 1000;
            String url = config.getBaseUrl() + api + "?" + getPlainEncode(data, mess, timestamp, config.getAppKey(), config.getPrivateKeyPem());
            log.info("发送云账户请求: requestId={}\napi={}\nparams={}", requestId, url, params);
//            HttpHeaders headers = new HttpHeaders();
//            headers.add("request-id", requestId);
//            headers.add("dealer-id", config.getDealerId());
//            headers.add("Content-Type", MediaType.APPLICATION_FORM_URLENCODED_VALUE);
//            ResponseEntity<YunZhangHuResultDto<T>> response = restTemplateWithProxy.exchange(
//                    url,
//                    HttpMethod.GET,
//                    new HttpEntity<>(headers),
//                    new ParameterizedTypeReference<YunZhangHuResultDto<T>>() {
//                    });
            CloseableHttpClient httpClient = createHttpClient(proxyConfig);
            HttpGet httpGet = new HttpGet(url);
            setHeader(httpGet, requestId, config.getDealerId());
            // 执行get请求.
            CloseableHttpResponse httpResponse = httpClient.execute(httpGet);
            String result = null;
            int statusCode = httpResponse.getStatusLine().getStatusCode();
            if (statusCode != HttpStatus.OK.value()) {
                if (statusCode == HttpStatus.FOUND.value()) {
                    result = httpResponse.getHeaders("location")[0].getValue();
                } else {
                    httpGet.abort();
                    log.error("接收云账户响应异常: requestId={}\nresponse=null", requestId);
                    throw new Exception("云账户访问异常:" + httpResponse.getStatusLine().getReasonPhrase());
                }
            } else {
                org.apache.http.HttpEntity entity = httpResponse.getEntity();
                if (entity != null) {
                    result = EntityUtils.toString(entity, StandardCharsets.UTF_8);
                }
                EntityUtils.consume(entity);
            }
            if (result == null) {
                log.error("接收云账户响应异常: requestId={}\nresponse=null", requestId);
                throw new Exception("云账户访问异常");
            }
            YunZhangHuResultDto<T> response = OBJECT_MAPPER.readValue(result, new TypeReference<YunZhangHuResultDto<T>>() {
            });
            if (response == null) {
                log.error("接收云账户响应异常: requestId={}\nresponse=null", requestId);
                throw new Exception("云账户访问异常");
            }
            log.info("接收云账户响应: requestId={}\nresponse={}", requestId, result);
            if ("0000".equals(response.getCode())) {
                // 成功
                return response.getData();
            }
            // 失败
            throw new Exception(response.getMessage());
        } catch (Exception e) {
            log.error(String.format("发送云账户请求异常: requestId=%s\napi=%s\nparams=%s", requestId, api, params), e);
            throw e;
        }
    }

    private static void setHeader(HttpRequestBase httpRequest, String requestId, String dealerId) {
        httpRequest.setHeader(HTTP.CONTENT_TYPE, MediaType.APPLICATION_FORM_URLENCODED_VALUE);
        httpRequest.setHeader("dealer-id", dealerId);
        httpRequest.setHeader("request-id", requestId);
    }

    private static CloseableHttpClient createHttpClient(ProxyConfig proxyConfig) throws Exception {
        RequestConfig.Builder configBuilder = RequestConfig.custom()
                .setConnectTimeout(30000)
                .setSocketTimeout(30000);
        if (proxyConfig.getEnabled()) {
            HttpHost proxy = new HttpHost(proxyConfig.getHost(), proxyConfig.getPort());
            configBuilder.setProxy(proxy);
        }
        SSLConnectionSocketFactory sslConnectionSocketFactory = new SSLConnectionSocketFactory(
                SSLContexts.custom().loadTrustMaterial(null, new TrustSelfSignedStrategy()).build(),
                NoopHostnameVerifier.INSTANCE
        );
        return HttpClientBuilder.create().setSSLSocketFactory(sslConnectionSocketFactory).setDefaultRequestConfig(configBuilder.build()).build();
    }

    // 获取get请求明文串
    private static String getPlainEncode(String data, String mess, Long timestamp, String appKey, String privateKeyPem) throws Exception {
        return DATA + "=" + getEncode(data) + "&" +
                MESS + "=" + getEncode(mess) + "&" +
                TIMESTAMP + "=" + timestamp + "&" +
                SIGN_TYPE + "=" + getEncode("rsa") + "&" +
                SIGN + "=" + getEncode(getSign(data, mess, timestamp, appKey, privateKeyPem));
    }

    // 获取签名
    private static String getSign(String data, String mess, Long timestamp, String appKey, String privateKeyPem) throws Exception {
        String plain = DATA + "=" + data + "&" +
                MESS + "=" + mess + "&" +
                TIMESTAMP + "=" + timestamp + "&" +
                KEY + "=" + appKey;
        return sign(plain, privateKeyPem);
    }

    private static String getEncode(String content) throws Exception {
        return URLEncoder.encode(content, StandardCharsets.UTF_8.name());
    }

    private static String encode(String data, String threeDesKey) throws Exception {
        byte[] content = data.getBytes(StandardCharsets.UTF_8);
        byte[] key = threeDesKey.getBytes(StandardCharsets.UTF_8);
        byte[] enc = TripleDesEncrypt(content, key);
        byte[] enc64 = Base64.encodeBase64(enc);
        return new String(enc64);
    }

    private static String decode(String data, String threeDesKey) throws Exception {
        byte[] dec64 = Base64.decodeBase64(data);
        byte[] dec = TripleDesDecrypt(dec64, threeDesKey.getBytes(StandardCharsets.UTF_8));
        return new String(dec);
    }

    private static byte[] TripleDesEncrypt(byte[] content, byte[] key) throws Exception {
        byte[] icv = new byte[8];
        System.arraycopy(key, 0, icv, 0, 8);
        return TripleDesEncrypt(content, key, icv);
    }

    private static byte[] TripleDesEncrypt(byte[] content, byte[] key, byte[] icv) throws Exception {
        final SecretKey secretKey = new SecretKeySpec(key, "DESede");
        final Cipher cipher = Cipher.getInstance("DESede/CBC/PKCS5Padding");
        final IvParameterSpec iv = new IvParameterSpec(icv);
        cipher.init(Cipher.ENCRYPT_MODE, secretKey, iv);
        return cipher.doFinal(content);
    }

    private static byte[] TripleDesDecrypt(byte[] content, byte[] key) throws Exception {
        byte[] icv = new byte[8];
        System.arraycopy(key, 0, icv, 0, 8);
        return TripleDesDecrypt(content, key, icv);
    }

    private static byte[] TripleDesDecrypt(byte[] content, byte[] key, byte[] icv) throws Exception {
        final SecretKey secretKey = new SecretKeySpec(key, "DESede");
        final Cipher cipher = Cipher.getInstance("DESede/CBC/PKCS5Padding");
        final IvParameterSpec iv = new IvParameterSpec(icv);
        cipher.init(Cipher.DECRYPT_MODE, secretKey, iv);
        return cipher.doFinal(content);
    }

    private static String sign(String content, String privateKeyPem) throws Exception {
        byte[] encodedKey = privateKeyPem.getBytes();
        encodedKey = org.bouncycastle.util.encoders.Base64.decode(encodedKey);
        PrivateKey privateKey = KeyFactory.getInstance("RSA").generatePrivate(new PKCS8EncodedKeySpec(encodedKey));
        Signature signature = Signature.getInstance("SHA256WithRSA");
        signature.initSign(privateKey);
        signature.update(content.getBytes(StandardCharsets.UTF_8));
        byte[] signed = signature.sign();
        return new String(org.bouncycastle.util.encoders.Base64.encode(signed));
    }

    private static boolean verify(String content, String sign, String publicKeyPem) throws Exception {
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        byte[] encodedKey = publicKeyPem.getBytes();
        encodedKey = org.bouncycastle.util.encoders.Base64.decode(encodedKey);
        PublicKey publicKey = keyFactory.generatePublic(new X509EncodedKeySpec(encodedKey));
        Signature signature = Signature.getInstance("SHA256WithRSA");
        signature.initVerify(publicKey);
        signature.update(content.getBytes(StandardCharsets.UTF_8));
        return signature.verify(org.bouncycastle.util.encoders.Base64.decode(sign.getBytes()));
    }

    private boolean canMock() {
        String[] profiles = new String[]{"dev", "test"};
        String[] activeProfiles = applicationContext.getEnvironment().getActiveProfiles();
        if (activeProfiles.length > 0) {
            for (String activeProfile : activeProfiles) {
                if ("prod".equals(activeProfile)) {
                    return false;
                }
                for (String profile : profiles) {
                    if (activeProfile.equals(profile)) {
                        return config.isEnableMock();
                    }
                }
            }
        }
        return false;
    }

    private static void main(String[] args) throws Exception {
        String data = "om0UtYwOHYzh7Ujk+APAZnvGVXGweqyUi8H7+PgP++QkFYtZ+snsQmQnSvxm/n191IyG8Grykrk=";
        String mess = "64733615";
        String sign = "ZsBxO3KNm8v8X5CwqKTYlT0Ifv2vd+sDPWI8z2xJwOW++aEHtisfR5+/1a1Q9l4fUGc/hlXuh0YCVLoc64Jx9PWNGmX6HC3ykaJ4UM5f9sRUgUrAWAA4H5HKjg6v8Vh8Nploc5OgvKzN94fsQ0QuKJWY74p87GcXJSTRSrpbOT3fZ4dpb4GC5VA8MfWdjxzXY/ro1P06QQPAJ6CymRc4vHJkkIt2diF2vD5O9QAt+TRGXU3Sg2AIYSPdyAUNqwEDfur+uZ65uVfKnxZBqVdnOIFQCPRaPtlzWtAujsQypgwLgfR7OGmMc3NM1NbPIfnutd2YGLLFW3rRhU0CegsmSQ==";
        System.out.println(decode(data, "63KAw05v0fdu7E1ZA75Y6crQ"));
    }
}
