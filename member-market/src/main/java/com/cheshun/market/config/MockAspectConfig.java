package com.cheshun.market.config;

import com.cheshun.common.annotation.MockData;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.lang.reflect.Type;

/**
 * @author 阿隆
 * Created on 2021/6/12 10:38 上午.
 */
@Aspect
@Component
@AllArgsConstructor
@Profile({"dev", "test"})
public class MockAspectConfig {
    private final ApplicationContext applicationContext;

    /**
     * 环绕通知
     *
     * @param joinPoint ProceedingJoinPoint
     */
    @Around("@annotation(com.zzjdyf.common.annotation.MockData)")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();
        // 下面方法可能获取不到mockData，因为获取到的可能是代理对象的method
//        MockData mockData = method.getDeclaredAnnotation(MockData.class);
        // 推荐
        Method realMethod = joinPoint.getTarget().getClass().getDeclaredMethod(method.getName(), method.getParameterTypes());
        MockData mockData = realMethod.getDeclaredAnnotation(MockData.class);
        if (mockData != null && mockData.profiles().length > 0) {
            String[] activeProfiles = applicationContext.getEnvironment().getActiveProfiles();
            if (activeProfiles.length > 0) {
                for (String profile : mockData.profiles()) {
                    for (String activeProfile : activeProfiles) {
                        if (activeProfile.equals(profile)) {
                            if (mockData.returnType() == Void.class) {
                                return null;
                            }
                            if (mockData.returnType() == Byte.class) {
                                return mockData.returnByteValue();
                            }
                            if (mockData.returnType() == Short.class) {
                                return mockData.returnShortValue();
                            }
                            if (mockData.returnType() == Integer.class) {
                                return mockData.returnIntValue();
                            }
                            if (mockData.returnType() == Long.class) {
                                return mockData.returnLongValue();
                            }
                            if (mockData.returnType() == Float.class) {
                                return mockData.returnFloatValue();
                            }
                            if (mockData.returnType() == Double.class) {
                                return mockData.returnDoubleValue();
                            }
                            if (mockData.returnType() == Boolean.class) {
                                return mockData.returnBooleanValue();
                            }
                            if (mockData.returnType() == Character.class) {
                                return mockData.returnCharValue();
                            }
                            if (mockData.returnType() == String.class) {
                                return mockData.returnStringValue();
                            }
                            return new ObjectMapper().readValue(mockData.returnStringValue(), new TypeReference() {
                                @Override
                                public Type getType() {
                                    return mockData.returnType();
                                }
                            });
                        }
                    }
                }
            }
        }
        return joinPoint.proceed();
    }
}
