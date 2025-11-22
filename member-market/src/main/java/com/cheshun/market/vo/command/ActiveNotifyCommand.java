package com.cheshun.market.vo.command;

import com.cheshun.market.domain.entity.enums.ActiveMethod;
import com.fasterxml.jackson.annotation.JsonAlias;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @author 阿隆
 * Created on 2021/8/12 9:35 上午.
 */
@Data
@ToString
@ApiModel("设备激活结果通知")
public class ActiveNotifyCommand {
    /**
     * 代理商id
     */
    @ApiModelProperty("代理商id")
    private String agentId;
    /**
     * 激活的用户id
     */
    @NotNull(message = "请配置激活的用户id")
    @ApiModelProperty("激活的用户id")
    private Long userId;
    /**
     * 激活渠道
     */
//    @NotNull(message = "请配置激活渠道")
    @ApiModelProperty("激活渠道")
    private String channel;
    /**
     * 激活方式:E-用户线上申请,自己激活 S-代理商线下安装激活
     */
    @NotNull(message = "请配置激活方式")
    @ApiModelProperty("激活方式:E-用户线上申请,自己激活 S-代理商线下安装激活")
    @JsonAlias("recvtype")
    private ActiveMethod method;
    /**
     * 激活的车牌号
     */
    @NotNull(message = "请配置激活的车牌号")
    @ApiModelProperty("激活的车牌号")
    private String activeCarNo;
    /**
     * 车牌号颜色
     */
    @NotNull(message = "请配置车牌号颜色")
    @ApiModelProperty("车牌号颜色")
    private String activeCarColor;
    /**
     * 激活的ECT账户
     */
    @ApiModelProperty("激活的ECT账户")
    private String etcAccount;
    /**
     * 激活的ETC卡号
     */
    @ApiModelProperty("激活的ETC卡号")
    private String cardSn;
    /**
     * 激活的OBU标签号
     */
    @ApiModelProperty("激活的OBU标签号")
    private String deviceSn;
    /**
     * 激活时间
     */
    @ApiModelProperty("激活时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date activeTime;
}
