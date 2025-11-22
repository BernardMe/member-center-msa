package com.cheshun.market.config;

import com.cheshun.market.service.common.JwtService;
import com.cheshun.common.Const;
import com.cheshun.common.annotation.NoToken;
import com.cheshun.common.annotation.SuperToken;
import com.cheshun.common.annotation.SystemApi;
import com.cheshun.common.exception.BizCodeEnum;
import com.cheshun.common.exception.RRException;
import com.cheshun.market.config.common.LoginInfo;
import com.cheshun.market.config.common.RoleDefine;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;
import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

/**
 * @author 阿隆
 * Created on 2021/6/12 10:38 上午.
 */
@Slf4j
public class WebMvcInterceptor implements HandlerInterceptor {
    private final JwtService jwtService;
    private final static Pattern ANONYMOUS_API_PATTERN = Pattern.compile("(/error)|(webjars)|(doc.html)|(swagger)|(api-docs)|(actuator)");

    public WebMvcInterceptor(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @Override
    public boolean preHandle(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull Object handler) {
        if (handler instanceof HandlerMethod) {
            final String api = request.getServletPath();
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            if (ANONYMOUS_API_PATTERN.matcher(api).find()) {
                // 无需登陆即可访问
                return true;
            }
            // 校验角色id
            Long roleId = Optional.ofNullable(request.getHeader(Const.ROLE))
                    .filter(StringUtils::hasText)
                    // 角色id只能是数字
                    .filter(t -> Const.NUMBER_PATTERN.matcher(t).find())
                    .map(Long::parseLong)
                    .orElse(-1L);
            // 校验API访问权限
            SystemApi systemApi = handlerMethod.getMethodAnnotation(SystemApi.class);
            if (systemApi != null) {
                // 是系统API
                if (!roleId.equals(RoleDefine.SYSTEM)) {
                    // 仅允许系统角色访问
                    throw new RRException(BizCodeEnum.NO_AUTHORIZATION);
                }
                return true;
            } else if (roleId.equals(RoleDefine.SYSTEM)) {
                // 不允许系统角色访问其他API
                throw new RRException(BizCodeEnum.NO_AUTHORIZATION);
            }
            // 校验token
            String token = request.getHeader(Const.TOKEN);
            if (!StringUtils.hasText(token) || Const.NULL.equals(token.trim())) {
                if (hasNoToken(handlerMethod)) {
                    // 无需登陆即可访问
                    return true;
                }
                throw new RRException(BizCodeEnum.NO_LOGIN);
            }
            // 解析token
            LoginInfo loginInfo;
            try {
                loginInfo = jwtService.verify(token);
            } catch (Exception e) {
                if (hasNoToken(handlerMethod)) {
                    // 无需登陆即可访问
                    return true;
                }
                if (hasSuperToken(request, handlerMethod)) {
                    // 有超级token即可访问
                    return true;
                }
                // 登陆信息已失效
                throw new RRException(BizCodeEnum.LOGIN_TOKEN_EXPIRED, e);
            }
            // 校验API访问权限
           /* AdminApi adminApi = handlerMethod.getMethodAnnotation(AdminApi.class);
            if (adminApi != null) {
                // 是系统管理API
                if (loginInfo.getRoleId() == null || !loginInfo.getRoleId().equals(RoleDefine.SYSTEM_MANAGER)) {
                    // 仅允许系统管理员角色访问
                    throw new RRException(BizCodeEnum.NO_AUTHORIZATION);
                }
            } else if (loginInfo.getRoleId() != null && loginInfo.getRoleId().equals(RoleDefine.SYSTEM_MANAGER)) {
                // 不允许系统管理员角色访问其他API
                throw new RRException(BizCodeEnum.NO_AUTHORIZATION);
            }*/
            request.setAttribute(Const.LOGIN_INFO, loginInfo);
        }
        return true;
    }

    private boolean hasNoToken(HandlerMethod handlerMethod) {
        return handlerMethod.getMethodAnnotation(NoToken.class) != null;
    }

    private boolean hasSuperToken(HttpServletRequest request, HandlerMethod handlerMethod) {
        // 从header中获取token
        String token = request.getHeader(Const.TOKEN);
        if (!StringUtils.hasText(token)) {
            return false;
        }
        SuperToken superToken = handlerMethod.getMethodAnnotation(SuperToken.class);
        if (superToken == null) {
            return false;
        }
        // 有超级token的请求才可以访问
        String[] superTokens = superToken.token();
        List<String> superTokenList = Arrays.asList(superTokens);
        return superTokenList.contains(token);
    }
}
