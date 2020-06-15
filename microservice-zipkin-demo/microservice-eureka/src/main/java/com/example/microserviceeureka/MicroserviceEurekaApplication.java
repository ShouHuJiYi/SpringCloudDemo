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
package com.example.microserviceeureka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author: yonghong.zhang@hand-china.com
 * @date: 2020/5/26 11:58
 * @version: 1.0.0
 * @description: eureka 服务端
 */
@SpringBootApplication
@EnableEurekaServer
public class MicroserviceEurekaApplication {
    /**
     * 日志
     */
    private final static Logger LOGGER = LoggerFactory.getLogger(MicroserviceEurekaApplication.class);

    /**
     * 启动类
     */
    public static void main(String[] args) {
        SpringApplication.run(MicroserviceEurekaApplication.class, args);
        LOGGER.info("服务注册中心启动成功");
    }
}
