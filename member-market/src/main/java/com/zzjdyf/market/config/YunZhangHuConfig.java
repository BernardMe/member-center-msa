package com.zzjdyf.market.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author 阿隆
 * Created on 2021/8/2 4:06 下午.
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "yun-zhang-hu")
public class YunZhangHuConfig {
    private String baseUrl = "https://api-service.yunzhanghu.com";
    private String brokerId;
    private String dealerId;
    private String appKey;
    private String threeDesKey;
    private String privateKeyPem;
    private String publicKeyPem;
    private boolean enableMock;
    private PaymentApi paymentApi = new PaymentApi();
    private InvoiceApi invoiceApi = new InvoiceApi();
    private AuthenticationApi authenticationApi = new AuthenticationApi();
    private DataServiceApi dataServiceApi = new DataServiceApi();

    @Data
    public static class PaymentApi {
        /**
         * 银行卡实时下单
         * <p>
         * POST
         * <p>
         * 请求参数
         * order_id 商户订单号 String 是 自定义，唯一，64 个英文字符以内
         * dealer_id 商户 ID String 是 云账户为每个商户分配的唯一 ID
         * broker_id 综合服务主体 ID String 是 综合服务主体 ID
         * real_name 姓名 String 是 银行卡开户姓名
         * card_no 银行卡号 String 是 银行开户卡号
         * id_card 身份证号 String 是 身份证号
         * phone_no 手机号 String 否 用户手机号
         * pay 打款金额 String 是 用户实际到账金额，单位：元，支持小数点后两位
         * pay_remark 打款备注 String 否 最大 20 个字符 不支持特殊字符 ' " & | @% ( ) - : # + / < > ¥ \ ,
         * notify_url 回调地址 String 否 异步通知地址
         * <p>
         * 响应参数
         * code 响应码 String 0000：成功，其他详见附录 6.3 响应码列表
         * message 响应码描述 String 详见附录 6.3 响应码列表
         * request_id 请求 ID String 请求 ID，原值返回
         * data
         * order_id 商户订单号 String 商户订单号，原值返回
         * ref 综合服务平台流水号 String 综合服务平台流水号，唯一
         * pay 打款金额 String 单位：元，支持小数点后两位
         */
        private String orderRealtime = "/api/payment/v1/order-realtime";
        /**
         * 查询单笔订单信息
         * <p>
         * GET
         * <p>
         * 请求参数
         * order_id 商户订单号 String 是 自定义，唯一，64 个英文字符以内
         * channel 渠道名 String 否 银行卡（默认）、支付宝、微信
         * data_type 数据类型 String 否 如果为 encryption，则对返回的 data 进行加密
         * <p>
         * 响应参数
         * code 响应码 String 0000：成功，其他详见附录 6.3 响应码列表
         * message 响应码描述 String 详见附录 6.3 响应码列表
         * request_id 请求 ID String 请求 ID，原值返回
         * data
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
        private String orderQuery = "/api/payment/v1/query-realtime-order";
        /**
         * 查询商户余额
         * <p>
         * GET
         * <p>
         * 请求参数
         * dealer_id 商户 ID String 是 商户云账户为每个商户分配的唯一 ID
         * <p>
         * 响应参数
         * code 响应码 String 0000：成功，其他详见附录 6.3 响应码列表
         * message 响应码描述 String 详见附录 6.3 响应码列表
         * request_id 请求 ID String 请求 ID，原值返回
         * data
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
        private String dealerBalanceQuery = "/api/payment/v1/query-accounts";
        /**
         * 取消待打款订单
         * <p>
         * POST
         * <p>
         * 请求参数
         * dealer_id 商户 ID String 是 云账户为每个商户分配的唯一 ID
         * order_id 商户订单号 String 否 与平台流水号不能同时为空
         * ref 综合服务平台流水号 String 否 与商户订单号不能同时为空
         * channel 渠道名 String 否 银行卡（默认）、支付宝、微信
         * <p>
         * 响应参数
         * code 响应码 String 0000：成功，其他详见附录 6.3 响应码列表
         * message 响应码描述 String 详见附录 6.3 响应码列表
         * request_id 请求 ID String 请求 ID，原值返回
         * data
         * ok 请求标识 String 返回 true，表示订单取消成功
         */
        private String cancelOrder = "/api/payment/v1/order/fail";
        /**
         * 银行卡信息查询接口
         * <p>
         * GET
         * <p>
         * 请求参数
         * card_no 银行卡号 String 否 银行卡号、银行名称二选一，两者都传时优先匹配银行卡号
         * bank_name 银行名称 String 否 银行卡号、银行名称二选一，两者都传时优先匹配银行卡号
         * <p>
         * 响应参数
         * code 响应码 String 0000：成功，其他详见附录 6.3 响应码列表
         * message 响应码描述 String 详见附录 6.3 响应码列表
         * request_id 请求 ID String 请求 ID，原值返回
         * data
         * bank_code 银行代码 String 银行代码
         * bank_name 银行名称 String 银行名称
         * card_type 卡类型 String 卡类型
         * is_support 云账户是否支持向该银行打款 Boolean 云账户是否支持向该银行打款
         */
        private String bankCardInfoQuery = "/api/payment/v1/card";
        /**
         * 查询商户已开具发票金额和待开具发票金额
         * <p>
         * GET
         * <p>
         * 请求参数
         * broker_id 综合服务主体 ID String 是 综合服务主体 ID
         * dealer_id 商户 ID String 是 云账户为每个商户分配的唯一 ID
         * year 查询年份 Number 否 按年份查询已开和待开发票金额，不传代表当前年份
         * <p>
         * 响应参数
         * code 响应码 String 0000：成功，其他详见附录 6.3 响应码列表
         * message 响应码描述 String 详见附录 6.3 响应码列表
         * request_id 请求 ID String 请求 ID，原值返回
         * data
         * dealer_id 商户 ID String 云账户为每个商户分配的唯一 ID
         * broker_id 综合服务主体 ID String 综合服务主体 ID
         * invoiced 已开发票金额 String 单位：元，支持小数点后两位
         * not_invoiced 待开发票金额 String 单位：元，支持小数点后两位
         */
        private String receiptQuery = "/api/payment/v1/invoice-stat";
        /**
         * 回调消息通知
         * <p>
         * POST
         * <p>
         * 通知参数
         * notify_id 通知 ID String 通知 ID
         * notify_time 通知时间 String 通知时间
         * data
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
        private String orderNotifyUrl = "";
    }

    @Data
    public static class InvoiceApi {
        /**
         * 查询可开票额度
         * <p>
         * POST
         * <p>
         * 请求参数
         * broker_id 综合服务主体 ID String 是 综合服务主体 ID
         * dealer_id 商户 ID String 是 云账户为每个商户分配的唯一 ID
         * <p>
         * 响应参数
         * code 响应码 String 0000：成功，其他详见附录 6.3 响应码列表
         * message 响应码描述 String 详见附录 6.3 响应码列表
         * request_id 请求 ID String 请求 ID，原值返回
         * data
         * amount 可开票金额 String 单位：元，支持小数点后 两位
         * bank_name_account 系统支持的开户行及 账号 Array 系统支持的开户行及账号
         * item 开户行及账号 String 开户行及账号
         * default 是否为默认值 Boolean 是否为默认值
         * goods_services_name 系统支持的货物或应 税劳务、服务名称 Array 系统支持的货物或应税劳 务、服务名称 参数 参数名 类型 说明
         * item 货 物 或 应 税 劳 务、服务名称 String 货物或 应税劳 务、服务名称
         * default 是否为默认值 Boolean 是否为默认值
         */
        private String invoiceAmountQuery = "/api/invoice/v2/invoice-amount";
        /**
         * 开票申请
         * <p>
         * POST
         * <p>
         * 请求参数
         * invoice_apply_id 发票申请编号 String 是 发票申请编号
         * broker_id 综合服务主体 ID String 是 综合服务主体 ID
         * dealer_id 商户 ID String 是 云账户为每个商户分配的唯一 ID
         * amount 申请开票金额 String 是 单位：元，支持小数点后两位
         * invoice_type 发票类型 String 是 1：专票；2：普票
         * bank_name_account 开户行及账号 String 否 不填使用默认值
         * goods_services_name 货物或应税劳务、服务 名称 String 否 不填使用默认值
         * remark 发票备注 String 否 每张发票备注栏相同
         * <p>
         * 响应参数
         * code 响应码 String 0000：成功，其他详见附录 6.3 响应码列表
         * message 响应码描述 String 详见附录 6.3 响应码列表
         * request_id 请求 ID String 请求 ID，原值返回
         * data
         * application_id 发票申请单 ID String 发票申请单 ID
         * count 发票张数 Number 本次开票申请，发票张数
         */
        private String invoiceApply = "/api/invoice/v2/apply";
        /**
         * 查询开票申请状态
         * <p>
         * POST
         * <p>
         * 请求参数
         * invoice_apply_id 发票申请编号 String 否 发票申请编号与 application_id 不能同时为空
         * application_id 发票申请单 ID String 否 发票申请单 ID 与 invoice_apply_id 不能同时为空
         * <p>
         * 响应参数
         * code 响应码 String 0000：成功，其他详见附录 6.3 响应码列表
         * message 响应码描述 String 详见附录 6.3 响应码列表
         * request_id 请求 ID String 请求 ID，原值返回
         * data
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
        private String invoiceApplyStatusQuery = "/api/invoice/v2/invoice/invoice-status";
        /**
         * 下载发票 PDF
         * <p>
         * POST
         * <p>
         * 请求参数
         * invoice_apply_id 发票申请编号 String 否 发票申请编号与 application_id 不能同时为空
         * application_id 发票申请单 ID String 否 发票申请单 ID 与 invoice_apply_id 不能同时为空
         * <p>
         * 响应参数
         * code 响应码 String 0000：成功，其他详见附录 6.3 响应码列表
         * message 响应码描述 String 详见附录 6.3 响应码列表
         * request_id 请求 ID String 请求 ID，原值返回
         * data
         * name 文件名称 String 文件名称
         * url 下载文件 url String 下载文件 url（临时地址，超时失效）
         */
        private String invoicePDF = "/api/invoice/v2/invoice/invoice-pdf";
        /**
         * 发送发票扫描件压缩包下载链接邮件
         * <p>
         * POST
         * <p>
         * 请求参数
         * invoice_apply_id 发票申请编号 String 否 发票申请编号与 application_id 不能同时为空
         * application_id 发票申请单 ID String 否 发票申请单 ID 与 invoice_apply_id 不能同时为空
         * <p>
         * 响应参数
         * code 响应码 String 0000：成功，其他详见附录 6.3 响应码列表
         * message 响应码描述 String 详见附录 6.3 响应码列表
         * request_id 请求 ID String 请求 ID，原值返回
         * data 返回数据 Json 返回数据
         */
        private String email = "/api/invoice/v2/invoice/reminder/email";
    }

    @Data
    public static class AuthenticationApi {
        /**
         * 身份证实名验证
         * <p>
         * POST
         * <p>
         * 请求参数
         * id_card 身份证号 String 是 身份证号
         * real_name 姓名 String 是 姓名
         * <p>
         * 响应参数
         * code 响应码 String 0000：成功，其他详见附录 6.3 响应码列表
         * message 响应码描述 String 详见附录 6.3 响应码列表
         * request_id 请求 ID String 请求 ID，原值返回
         */
        private String realNameVerify = "/authentication/verify-id";
        /**
         * 银行卡四要素鉴权请求（下发短信验证码）
         * <p>
         * POST
         * 请求参数
         * card_no 银行卡号 String 是 银行卡号
         * id_card 身份证号 String 是 身份证号
         * real_name 姓名 String 是 姓名
         * mobile 银行预留手机号 String 是 银行预留手机号
         * <p>
         * 响应参数
         * code 响应码 String 0000：成功，其他详见附录 6.3 响应码列表
         * message 响应码描述 String 详见附录 6.3 响应码列表
         * request_id 请求 ID String 请求 ID，原值返回
         * data
         * ref 交易凭证 String 交易凭证
         */
        private String fourFactorVerifySendMsg = "/authentication/verify-request";
        /**
         * 银行卡四要素确认鉴权（上传短信验证码）
         * <p>
         * POST
         * 请求参数
         * card_no 银行卡号 String 是 银行卡号
         * id_card 身份证号 String 是 身份证号
         * real_name 开户人姓名 String 是 开户人姓名
         * mobile 开户预留手机号 String 是 开户预留手机号
         * captcha 短信验证码 String 是 短信验证码
         * ref 交易凭证 String 是 鉴权请求接口响应的交易凭证
         * <p>
         * 响应参数
         * code 响应码 String 0000：成功，其他详见附录 6.3 响应码列表
         * message 响应码描述 String 详见附录 6.3 响应码列表
         * request_id 请求 ID String 请求 ID，原值返回
         */
        private String fourFactorVerifyConfirm = "/authentication/verify-confirm";
        /**
         * 银行卡四要素验证
         * <p>
         * POST
         * 请求参数
         * card_no 银行卡号 String 是 银行卡号
         * id_card 身份证号 String 是 身份证号
         * real_name 开户人姓名 String 是 开户人姓名
         * mobile 开户预留手机号 String 是 开户预留手机号
         * 响应参数
         * code 响应码 String 0000：成功，其他详见附录 6.3 响应码列表
         * message 响应码描述 String 详见附录 6.3 响应码列表
         * request_id 请求 ID String 请求 ID，原值返回
         */
        private String fourFactorVerify = "/authentication/verify-bankcard-four-factor";
        /**
         * 银行卡三要素验证
         * <p>
         * POST
         * <p>
         * 请求参数
         * card_no 银行卡号 String 是 银行卡号
         * id_card 身份证号 String 是 身份证号
         * real_name 姓名 String 是 姓名
         * <p>
         * 响应参数
         * code 响应码 String 0000：成功，其他详见附录 6.3 响应码列表
         * message 响应码描述 String 详见附录 6.3 响应码列表
         * request_id 请求 ID String 请求 ID，原值返回
         */
        private String threeFactorVerify = "/authentication/verify-bankcard-three-factor";
    }

    @Data
    public static class DataServiceApi {
        /**
         * 查询商户充值记录
         * <p>
         * 最大查询时间间隔不能超过 30 天。
         * <p>
         * GET
         * <p>
         * 请求参数
         * begin_at 查询开始日期 String 是 格式：yyyy-MM-dd
         * end_at 查询结束日期 String 是 格式：yyyy-MM-dd
         * <p>
         * 响应参数
         * code 响应码 String 0000：成功，其他详见附录 6.3 响应码列表
         * message 响应码描述 String 详见附录 6.3 响应码列表
         * request_id 请求 ID String 请求 ID，原值返回
         * data list
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
        private String rechargeRecordQuery = "/api/dataservice/v2/recharge-record";
        /**
         * 查询日订单数据
         * <p>
         * 只能查询 90 天之内的订单。
         * 最晚只能查询前一日的订单，当日订单不能查询。
         * <p>
         * GET
         * <p>
         * 请求参数
         * order_date 订单查询日期 String 是 格式：yyyy-MM-dd
         * offset 偏移量 Number 是 偏移量，最小从 0 开始
         * length 条数 Number 是 每页最多返回条数，最大为 200
         * channel 渠道名称 String 否 银行卡（默认）、支付宝、微信
         * data_type 数据类型 String 否 如果为 encryption，则对返回的 data 进行加密
         * <p>
         * 响应参数
         * code 响应码 String 0000：成功，其他详见附录 6.3 响应码列表
         * message 响应码描述 String 详见附录 6.3 响应码列表
         * request_id 请求 ID String 请求 ID，原值返回
         * data
         * total_num 总数目 Number 总条数
         * list 条目信息 List 条目信息
         * broker_id 商户综合服务主体 ID String 商户综合服务主体 ID
         * dealer_id 商户 ID String 云账户为每个商户分配的 唯一 ID
         * order_id 商户订单号 String 商户订单号 ref 流水号 String 流水号
         * batch_id 批次号 String 批次号
         * real_name 姓名 String 姓名
         * card_no 收款账号 String 收款账号
         * broker_amount 综合服务主体打款金 额 String 单位：元，支持小数点后 两位
         * broker_fee 综合服务主体服务费 String 单位：元，支持小数点后 两位
         * bill 渠道流水号 String 渠道流水号
         * status 订单状态码 String 详见附录 6.1 订单状态码
         * status_detail 订单详细状态码 String 详见附录 6.2 订单详细状 态码
         * status_message 订单状态码描述 String 详见附录 6.1 订单状态码
         * status_detail_message 订单详细状态码描述 String 详见附录 6.2 订单详细状 态码
         * statment_id 短周期授信账单号 String 短周期授信账单号
         * fee_statment_id 服务费账单号 String 服务费账单号
         * bal_statment_id 余额账单号 String 余额账单号
         * channel 支付渠道 String 银行卡、支付宝、微信
         * created_at 订单接收时间 String 精确到秒
         * finished_time 订单完成时间 String 精确到秒
         */
        private String dailyBillFileQuery = "/api/dataservice/v1/orders";
    }
}
