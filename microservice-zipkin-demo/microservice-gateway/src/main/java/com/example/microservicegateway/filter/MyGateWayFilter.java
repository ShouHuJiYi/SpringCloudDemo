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
package com.example.microservicegateway.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;
import java.util.Date;

/**
 * @author: yonghong.zhang@hand-china.com
 * @date: 2020/6/2 21:05
 * @version: 1.0.0
 * @description: 服务网关自定义过滤器
 */
@Component
public class MyGateWayFilter implements GlobalFilter, Ordered {
    /**
     * 日志
     */
    private final static Logger LOGGER = LoggerFactory.getLogger(MyGateWayFilter.class);

    /**
     * 过滤内容
     *
     * @param exchange
     * @param chain
     * @return Mono<Void>
     */
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        LOGGER.info("welcome to my filter ! {}", new Date());
        //获取用户名
        String username = exchange.getRequest().getQueryParams().getFirst("username");
        //用户名为空，则返回406代码
        if (username == null) {
            LOGGER.info("用户名为NULL，该用户非法 {}", new Date());
            byte[] bytes = "username is not null".getBytes(StandardCharsets.UTF_8);
            DataBuffer buffer = exchange.getResponse().bufferFactory().wrap(bytes);
            exchange.getResponse().setStatusCode(HttpStatus.NOT_ACCEPTABLE);
            return exchange.getResponse().writeWith(Flux.just(buffer));
        }
        return chain.filter(exchange);
    }


    /**
     * 顺序
     *
     * @return int
     */
    @Override
    public int getOrder() {
        LOGGER.info(" get order ! {}", new Date());
        return 0;
    }
}
