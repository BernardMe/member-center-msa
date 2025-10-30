package com.zzjdyf.common.exception;

/***
 * 错误码和错误信息定义类
 * 1. 错误码定义规则为5为数字
 * 2. 前两位表示业务场景，最后三位表示错误码。例如：100001。10:通用 001:系统未知异常
 * 3. 维护错误码后需要维护错误描述，将他们定义为枚举形式
 * 错误码列表：
 *  10: 通用
 *      001：参数格式校验
 *  11: 账户
 *  12: 订单
 *  ...
 * @author Mark sunlightcs@gmail.com
 */
public enum BizCodeEnum {

    /**
     * 成功
     */
    SUCCESS(0, "成功"),
    /**
     * 系统未知异常
     */
    UNKNOW_EXCEPTION(10000, "系统未知异常"),
    /**
     * 参数格式校验失败
     */
    VAILD_EXCEPTION(10001, "参数格式校验失败"),
    /**
     * 请求流量过大，请稍后再试
     */
    TO_MANY_REQUEST(10002, "请求流量过大，请稍后再试"),
    /**
     * 验证码获取频率太高，请稍后再试
     */
    SMS_CODE_EXCEPTION(10003, "验证码获取频率太高，请稍后再试"),
    /**
     * 远程调用服务超时，请重新再试
     */
    READ_TIME_OUT_EXCEPTION(10004, "远程调用服务超时，请重新再试"),
    /**
     * 图片大小不能超过500KB
     */
    IMAGE_MAX_SIZE_500KB(10005, "图片大小不能超过500KB"),
    /**
     * 账户系统-中金-影印件采集API调用失败
     */
    CPCN_API_4600_FAILED(20000, "cpcn_api_4600_failed"),
    /**
     * 账户系统-中金-开户API调用失败
     */
    CPCN_API_4601_FAILED(20001, "cpcn_api_4601_failed"),
    /**
     * 账户系统-中金-绑卡API调用失败
     */
    CPCN_API_4611_FAILED(20002, "cpcn_api_4611_failed"),
    /**
     * 账户系统-中金-绑卡确认API调用失败
     */
    CPCN_API_4613_FAILED(20003, "cpcn_api_4613_failed"),
    /**
     * 账户系统-中金-查询开户API调用失败
     */
    CPCN_API_4616_4601_FAILED(20004, "cpcn_api_4616_4601_failed"),
    /**
     * 账户系统-中金-查询绑卡API调用失败
     */
    CPCN_API_6416_4611_FAILED(20005, "cpcn_api_6416_4611_failed"),
    /**
     * 账户系统-中金-充值API调用失败
     */
    CPCN_API_4641_FAILED(20006, "cpcn_api_4641_failed"),
    /**
     * 账户系统-中金-提现API调用失败
     */
    CPCN_API_4643_FAILED(20007, "cpcn_api_4643_failed"),
    /**
     * 账户系统-中金-单笔代付API调用失败
     */
    CPCN_API_4645_FAILED(20008, "cpcn_api_4645_failed"),
    /**
     * 账户系统-中金-充值查询API调用失败
     */
    CPCN_API_4656_4641_FAILED(20009, "cpcn_api_4656_4641_failed"),
    /**
     * 账户系统-中金-提现查询API调用失败
     */
    CPCN_API_4656_4643_FAILED(20010, "cpcn_api_4656_4643_failed"),
    /**
     * 账户系统-中金-代付查询API调用失败
     */
    CPCN_API_4656_4645_FAILED(20011, "cpcn_api_4656_4645_failed"),
    /**
     * 账户系统-中金-转账查询API调用失败
     */
    CPCN_API_4656_4651_FAILED(20012, "cpcn_api_4656_4651_failed"),
    /**
     * 账户系统-中金-余额冻结API调用失败
     */
    CPCN_API_4663_10_FAILED(20013, "cpcn_api_4663_10_failed"),
    /**
     * 账户系统-中金-余额解冻API调用失败
     */
    CPCN_API_4663_20_FAILED(20014, "cpcn_api_4663_20_failed"),
    /**
     * 账户系统-中金-余额冻结查询API调用失败
     */
    CPCN_API_4664_10_FAILED(20015, "cpcn_api_4664_10_failed"),
    /**
     * 账户系统-中金-余额解冻查询API调用失败
     */
    CPCN_API_4664_20_FAILED(20016, "cpcn_api_4664_20_failed"),
    /**
     * 账户系统-中金-用户信息查询API调用失败
     */
    CPCN_API_4691_FAILED(20017, "cpcn_api_4691_failed"),
    /**
     * 账户系统-中金-用户账户明细查询API调用失败
     */
    CPCN_API_4692_FAILED(20018, "cpcn_api_4692_failed"),
    /**
     * 账户系统-中金-绑定银行账户查询API调用失败
     */
    CPCN_API_4693_FAILED(20019, "cpcn_api_4693_failed"),
    /**
     * 账户系统-中金-电子回单下载API调用失败
     */
    CPCN_API_4698_FAILED(20020, "cpcn_api_4698_failed"),
    /**
     * 账户系统-中金-支付API调用失败
     */
    CPCN_API_5011_FAILED(20021, "cpcn_api_5011_failed"),
    /**
     * 账户系统-中金-订单关闭API调用失败
     */
    CPCN_API_5014_FAILED(20022, "cpcn_api_5014_failed"),
    /**
     * 账户系统-中金-快捷支付验短API调用失败
     */
    CPCN_API_5015_FAILED(20023, "cpcn_api_5015_failed"),
    /**
     * 账户系统-中金-支付查询API调用失败
     */
    CPCN_API_5016_FAILED(20024, "cpcn_api_5016_failed"),
    /**
     * 账户系统-中金-退款API调用失败
     */
    CPCN_API_5021_FAILED(20025, "cpcn_api_5021_failed"),
    /**
     * 账户系统-中金-退款查询API调用失败
     */
    CPCN_API_5026_FAILED(20026, "cpcn_api_5026_failed"),
    /**
     * 账户系统-中金-延迟分账API调用失败
     */
    CPCN_API_5031_FAILED(20027, "cpcn_api_5031_failed"),
    /**
     * 账户系统-中金-延迟分账查询API调用失败
     */
    CPCN_API_5036_FAILED(20028, "cpcn_api_5036_failed"),
    /**
     * 账户系统-开户申请已提交
     */
    ACCOUNT_OPEN_PROCESSING(20100, "open_account_processing"),
    /**
     * 账户系统-已开户成功
     */
    ACCOUNT_OPEN_SUCCESS(20101, "open_account_success"),
    /**
     * 账户系统-账户信息保存失败
     */
    ACCOUNT_SAVE_FAILED(20102, "account_save_failed"),
    /**
     * 账户系统-账户信息不存在
     */
    ACCOUNT_NOT_FOUND(20103, "account_not_found"),
    /**
     * 账户系统-开户失败
     */
    ACCOUNT_OPEN_FAILED(20104, "open_account_failed"),
    /**
     * 账户系统-用户id为空
     */
    ACCOUNT_USER_ID_IS_NULL(20105, "user_id_is_empty"),
    /**
     * 账户系统-请求来源为空
     */
    ACCOUNT_REQUEST_SOURCE_IS_ERROR(20106, "request_source_is_error"),
    /**
     * 账户系统-身份证号不正确
     */
    ACCOUNT_CREDENTIAL_NUM_IS_ERROR(20107, "credential_num_is_error"),
    /**
     * 账户系统-短信验证码不正确
     */
    ACCOUNT_SMS_CODE_IS_ERROR(20108, "sms_code_is_error"),
    /**
     * 账户系统-短信验证码发送失败
     */
    ACCOUNT_SMS_SEND_ERROR(20109, "age_limit"),
    /**
     * 账户系统-年龄受限
     */
    ACCOUNT_AGE_LIMIT(20110, "open_account_age_limit"),
    /**
     * 账户系统-还未绑定该卡
     */
    ACCOUNT_BINDING_CARD_NOT_FOUND(20200, "binding_card_not_found"),
    /**
     * 账户系统-该账户已提交绑卡申请
     */
    ACCOUNT_BINDING_CARD_PROCESSING(20201, "binding_card_processing"),
    /**
     * 账户系统-待短信验证
     */
    ACCOUNT_BINDING_CARD_WAIT_SMS_VERIFY(20202, "binding_card_wait_sms_verify"),
    /**
     * 账户系统-待被动打款验证
     */
    ACCOUNT_BINDING_CARD_WAIT_PASSIVE_PAY_VERIFY(20203, "binding_card_wait_passive_pay_verify"),
    /**
     * 账户系统-被动已打款待验证
     */
    ACCOUNT_BINDING_CARD_WAIT_PASSIVE_PAID_VERIFY(20204, "binding_card_wait_passive_paid_verify"),
    /**
     * 账户系统-验证处理中
     */
    ACCOUNT_BINDING_CARD_VERIFY_PROCESSING(20205, "binding_card_verify_processing"),
    /**
     * 账户系统-该卡不可用
     */
    ACCOUNT_BINDING_CARD_CAN_NOT_USE(20206, "binding_card_can_not_use"),
    /**
     * 账户系统-绑卡信息保存失败
     */
    ACCOUNT_BINDING_CARD_SAVE_FAILED(20207, "binding_card_save_failed"),
    /**
     * 账户系统-绑卡失败
     */
    ACCOUNT_BINDING_CARD_FAILED(20208, "binding_card_failed"),
    /**
     * 账户系统-已绑定该卡
     */
    ACCOUNT_BINDING_CARD_EXIST(20209, "binding_card_exist"),
    /**
     * 账户系统-短信验证失败
     */
    ACCOUNT_BINDING_CARD_SMS_VERIFY_FAILED(20210, "binding_card_sms_verify_failed"),
    /**
     * 账户系统-打款验证失败
     */
    ACCOUNT_BINDING_CARD_PASSIVE_PAID_VERIFY_FAILED(20211, "binding_card_passive_paid_verify_failed"),
    /**
     * 账户系统-至少需要绑定一张卡
     */
    ACCOUNT_BINDING_CARD_MIN_COUNT(20212, "binding_card_min_count"),
    /**
     * 账户系统-请选择开户银行
     */
    ACCOUNT_BINDING_CARD_SELECT_BANK(20213, "binding_card_select_bank"),
    /**
     * 账户系统-解绑银行卡失败
     */
    ACCOUNT_UNBINDING_CARD_FAILED(20300, "unbinding_card_failed"),
    /**
     * 账户系统-该账户已提交解绑申请
     */
    ACCOUNT_UNBINDING_CARD_PROCESSING(20301, "unbinding_card_processing"),
    /**
     * 账户系统-该卡已解绑
     */
    ACCOUNT_UNBINDING_CARD_HAD_SUCCESS(20302, "unbinding_card_had_success"),
    /**
     * 账户系统-解绑银行卡失败,有未完成的交易
     */
    ACCOUNT_UNBINDING_HAD_NOT_FINISHED_TRANS(20303, "unbinding_failed_had_not_finished_trans"),
    /**
     * 账户系统-不支持该银行卡
     */
    ACCOUNT_NOT_SUPPORT_BANK_CARD(20304, "not_support_bank_card"),
    /**
     * 账户系统-银行卡长度不正确
     */
    ACCOUNT_BANK_CARD_LENGTH_ERROR(20305, "bank_card_length_error"),
    /**
     * 账户系统-卡号与银行不匹配
     */
    ACCOUNT_BANK_ERROR(20306, "bank_error"),
    /**
     * 账户系统-提现失败
     */
    ACCOUNT_WITHDRAW_FAILED(20400, "withdraw_failed"),
    /**
     * 账户系统-余额不足
     */
    ACCOUNT_BALANCE_NOT_ENOUGH(20401, "balance_not_enough"),
    /**
     * 账户系统-保证金余额不足
     */
    ACCOUNT_FREEZE_DEPOSIT_AMOUNT_NOT_ENOUGH(20402, "freeze_deposit_amount_not_enough"),
    /**
     * 账户系统-解冻保证金失败
     */
    ACCOUNT_UNFREEZE_DEPOSIT_AMOUNT_FAILED(20403, "unfreeze_deposit_amount_failed"),
    /**
     * 账户系统-没有可解冻的保证金
     */
    ACCOUNT_NOT_FOUND_FREEZE_DEPOSIT_AMOUNT(20403, "not_found_deposit_amount"),
    /**
     * 账户系统-充值失败
     */
    ACCOUNT_RECHARGE_FAILED(20404, "recharge_failed"),
    /**
     * 账户系统-超出了单笔交易限额
     */
    ACCOUNT_SINGLE_AMOUNT_TOO_MORE(20405, "single_amount_too_more"),
    /**
     * 账户系统-超出了单日交易限额
     */
    ACCOUNT_DAY_AMOUNT_TOO_MORE(20406, "day_amount_too_more"),
    /**
     * 账户系统-超出了待缴纳总金额
     */
    ACCOUNT_RECHARGE_AMOUNT_TOO_MORE(20407, "超出了待缴纳总金额"),
    /**
     * 账户系统-车辆已缴纳保证金
     */
    ACCOUNT_CAR_DEPOSIT_EXIST(20408, "该车辆已缴纳预存金"),
    /**
     * 账户系统-不是保证金账户
     */
    ACCOUNT_NO_DEPOSIT(20409, "不是保证金账户"),
    /**
     * 账户系统-已设置过交易密码
     */
    ACCOUNT_HAD_EXIST_TRANS_PWD(20500, "trans_pwd_had_exist"),
    /**
     * 账户系统-设置交易密码失败
     */
    ACCOUNT_SET_TRANS_PWD_FAILED(20501, "set_trans_pwd_failed"),
    /**
     * 账户系统-交易密码输入不正确
     */
    ACCOUNT_TRANS_PWD_INCORRECT(20502, "trans_pwd_incorrect"),
    /**
     * 账户系统-还未设置交易密码
     */
    ACCOUNT_NOT_EXIST_TRANS_PWD(20503, "trans_pwd_not_exist"),
    /**
     * 账户系统-交易密码格式不正确
     */
    ACCOUNT_TRANS_PWD_FORMAT_ERROR(20504, "trans_pwd_format_error"),
    /**
     * 账户系统-交易密码充值失败
     */
    ACCOUNT_RESET_TRANS_PWD_FAILED(20505, "reset_trans_pwd_error"),
    /**
     * 账户系统-身份证号输入不正确
     */
    ACCOUNT_ID_CARD_NUM_INCORRECT(20506, "id_card_num_incorrect"),
    /**
     * 账户系统-新的支付密码不能是原来密码
     */
    ACCOUNT_NEW_TRANS_PWD_IS_OLD(20507, "new_trans_pwd_is_old"),
    /**
     * 账户系统-交易密码不能是相同的数字
     */
    ACCOUNT_TRANS_PWD_IS_SAME(20508, "trans_pwd_is_same"),
    /**
     * 账户系统-交易密码不能是相同的数字
     */
    ACCOUNT_TRANS_PWD_IS_CONTINUOUS(20509, "trans_pwd_is_continuous"),
    /**
     * 账户系统-交易不存在
     */
    ACCOUNT_NOT_FOUND_TRANS_HISTORY(20600, "not_found_trans_history"),
    /**
     * 账户系统-注销失败-账户金额未清0
     */
    ACCOUNT_UNREGISTER_FAILED_AMOUNT_NOT_ZERO(20700, "unregister_failed_amount_not_zero"),
    /**
     * 账户系统-注销失败-有未完成的充值交易
     */
    ACCOUNT_UNREGISTER_FAILED_HAD_NOT_FINISHED_RECHARGE_TRANS(20701, "unregister_failed_had_not_finished_recharge_trans"),
    /**
     * 账户系统-注销失败-有未完成的提现交易
     */
    ACCOUNT_UNREGISTER_FAILED_HAD_NOT_FINISHED_WITHDRAW_TRANS(20702, "unregister_failed_had_not_finished_withdraw_trans"),
    /**
     * 账户系统-注销失败-有未完成的支付交易
     */
    ACCOUNT_UNREGISTER_FAILED_HAD_NOT_FINISHED_PAYMENT_TRANS(20703, "unregister_failed_had_not_finished_payment_trans"),
    /**
     * 账户系统-注销失败-有未完成的冻结交易
     */
    ACCOUNT_UNREGISTER_FAILED_HAD_NOT_FINISHED_FREEZE_TRANS(20704, "unregister_failed_had_not_finished_freeze_trans"),
    /**
     * 账户系统-注销失败-有未完成的解冻交易
     */
    ACCOUNT_UNREGISTER_FAILED_HAD_NOT_FINISHED_UNFREEZE_TRANS(20705, "unregister_failed_had_not_finished_unfreeze_trans"),
    /**
     * 账户系统-注销失败-有未完成的交易
     */
    ACCOUNT_UNREGISTER_FAILED_HAD_NOT_FINISHED_TRANS(20706, "unregister_failed_had_not_finished_trans"),
    /**
     * 账户系统-注销失败-有冻结中或解冻中的保证金
     */
    ACCOUNT_UNREGISTER_FAILED_HAD_NOT_FINISHED_DEPOSIT(20707, "unregister_failed_had_not_finished_deposit"),
    /**
     * 账户系统-注销失败-解绑银行卡失败
     */
    ACCOUNT_UNREGISTER_FAILED_UNBINDING_FAILED(20708, "unregister_failed_unbinding_failed"),
    /**
     * 账户系统-注销失败
     */
    ACCOUNT_UNREGISTER_FAILED(20709, "unregister_failed"),
    /**
     * 账户系统-注销失败-有未完成的还款交易
     */
    ACCOUNT_UNREGISTER_FAILED_HAD_NOT_FINISHED_REPAYMENT_TRANS(20710, "unregister_failed_had_not_finished_repayment_trans"),
    /**
     * 账户系统-支付失败
     */
    ACCOUNT_PAYMENT_FAILED(20800, "pay_failed"),
    /**
     * 账户系统-还款失败
     */
    ACCOUNT_REPAYMENT_FAILED(20900, "repayment_failed"),
    /**
     * 账户系统-退款失败
     */
    ACCOUNT_REFUND_FAILED(21000, "refund_failed"),

    //--------------订单开始----------------------------------
    /**
     * 订单-订单请求类型异常
     */
    ORDER_TYPE_ERROR(12001, "订单请求类型异常"),
    /**
     * 订单-订单请求类型异常
     */
    ORDER_CREATE_FAILED(12002, "订单录入失败"),
    //--------------订单结束----------------------------------

    /**
     * 还未登陆
     */
    NO_LOGIN(40000, "您还未登陆"),
    /**
     * 登陆已失效, 请重新登陆
     */
    LOGIN_TOKEN_EXPIRED(40001, "登陆已失效,请重新登陆"),
    /**
     * 用户不存在
     */
    USER_NOT_EXIST(40002, "用户不存在"),
    /**
     * 验证码不正确
     */
    CAPTCHA_INCORRECT(40003, "验证码不正确"),
    /**
     * 添加失败
     */
    ADD_FAILED(40004, "添加失败"),
    /**
     * 获取验证码失败
     */
    GET_CAPTCHA_FAILED(40005, "获取验证码失败"),
    /**
     * 未拥有该角色
     */
    NOT_OWNED_ROLE(40006, "未拥有该角色"),
    /**
     * 被禁用
     */
    USER_DISABLED(40007, "账号已被禁用"),
    /**
     * 验证码已失效,请重新获取
     */
    CREATE_EXPIRED(40008, "验证码已失效,请重新获取"),
    /**
     * 角色不存在
     */
    ROLE_NOT_EXIST(40009, "角色不存在"),
    /**
     * 用户名/密码不正确
     */
    PASSWORD_INCORRECT(40010, "用户名/密码不正确"),
    /**
     * 您没有权限执行该操作
     */
    NO_AUTHORIZATION(40011, "您没有权限执行该操作"),
    /**
     * 登陆失败
     */
    LOGIN_FAILED(40012, "登陆失败"),
    /**
     * 未通过实名认证
     */
    ID_CERT_FAILED(40013, "未通过实名认证"),
    /**
     * 用户已存在
     */
    USER_EXIST(40014, "用户已存在"),

    /**
     * 添加失败
     */
    UPDATE_FAILED(40015, "修改失败"),
    ;

    /**
     * 错误码
     */
    private final int code;
    /**
     * 错误信息
     */
    private final String msg;

    BizCodeEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
