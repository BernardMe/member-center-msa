package com.zzjdyf.gateway.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.event.RefreshRoutesEvent;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteDefinitionRepository;
import org.springframework.cloud.gateway.support.NotFoundException;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * 功能描述：修改路由配置
 *
 * @author 姚良辉
 * @version V1.0
 * @date 2019/5/14
 */
public interface DynamicRouteService  {


    /**
     *
     * 新增路由
     * @author xueqing wang
     * @date 2021/4/13 12:48
     * @param definition
     * @return null
     */
    public String add(RouteDefinition definition);

    /**
     * 更新路由配置
     * @param definition 路由配置
     * @return String
     */
    public String update(RouteDefinition definition);

    /**
     * 更新路由
     * @param definitions
     * @return
     */
    public String updateList(List<RouteDefinition> definitions);

    /**
     * 删除路由配置
     * @param id 路由id
     * @return String
     */
    public String delete(String id);
}
