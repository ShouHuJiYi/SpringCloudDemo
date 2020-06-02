/*
 * Copyright © 2020 yonghong.zhang (shouhujiyi@gmail.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.microservicegateway.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author: yonghong.zhang@hand-china.com
 * @date: 2020/6/2 21:16
 * @version: 1.0.0
 * @description: 通过配置类实现过滤器
 */

@Configuration
public class GateWayConfig {
    /**
     * 日志
     */
    private final static Logger LOGGER = LoggerFactory.getLogger(GateWayConfig.class);

    /**
     * 构建路由规则
     *
     * @param routeLocatorBuilder
     * @return RouteLocator
     */
    @Bean
    public RouteLocator myRouteLocator(RouteLocatorBuilder routeLocatorBuilder) {
        LOGGER.info("构建路由开始");
        RouteLocatorBuilder.Builder routes = routeLocatorBuilder.routes();
        //构建国内路由
        routes.route("guonei", r -> r.path("/guonei")
                .uri("http://news.baidu.com/guonei")).build();
        //构建国际路由
        routes.route("guoji", r -> r.path("/guoji")
                .uri("http://news.baidu.com/guoji")).build();
        LOGGER.info("构建路由结束");
        return routes.build();
    }
}
