package com.cheshun.market.config.common;

import com.cheshun.market.domain.entity.enums.DataAuth;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 登陆信息
 *
 * @author 阿隆
 * Created on 2021/6/12 10:38 上午.
 */
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginInfo {
    /**
     * 用户id
     */
    private Long id;
    /**
     * 手机号
     */
    private String phone;
    /**
     * 登陆用户名
     */
    private String username;
    /**
     * 角色
     */
    private Long roleId;

    /**
     * 数据权限
     */
    private DataAuth dataAuth;
}
