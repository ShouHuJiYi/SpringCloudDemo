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
package com.example.microservicegateway;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;

/**
 * @author: yonghong.zhang@hand-china.com
 * @date: 2020/6/2 21:05
 * @version: 1.0.0
 * @description: 服务网关启动类
 */
@SpringBootApplication
@EnableEurekaClient
@EnableCircuitBreaker
@EnableHystrix
public class MicroserviceGatewayApplication {
    /**
     * 日志
     */
    private final static Logger LOGGER = LoggerFactory.getLogger(MicroserviceGatewayApplication.class);

    /**
     * 主启动类
     */
    public static void main(String[] args) {
        SpringApplication.run(MicroserviceGatewayApplication.class, args);
        LOGGER.info("启动成功");
    }
}
