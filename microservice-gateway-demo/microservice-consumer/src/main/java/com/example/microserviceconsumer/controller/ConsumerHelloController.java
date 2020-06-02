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
package com.example.microserviceconsumer.controller;


import com.example.microserviceconsumer.service.ConsumerHelloService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * @author: yonghong.zhang@hand-china.com
 * @date: 2020/5/26 10:57
 * @version: 1.0.0
 * @description: 消费方控制函数
 */

@RestController
@RequestMapping("/consumer")
public class ConsumerHelloController {

    private final static Logger LOGGER = LoggerFactory.getLogger(ConsumerHelloController.class);

    /**
     * 获取端口
     */
    @Value("${server.port}")
    String port;

    /**
     * set注入 helloService
     */
    ConsumerHelloService consumerHelloService;

    /**
     * set注入 helloService
     */
    @Autowired
    ConsumerHelloService setHelloService(ConsumerHelloService consumerHelloService) {
        LOGGER.info("set 方式注入consumerHelloService = {}", consumerHelloService.hashCode());
        return this.consumerHelloService = consumerHelloService;
    }

    /**
     * Hello 服务
     * 调用
     *
     * @param name 姓名
     * @return String
     */
    @GetMapping("/hello")
    String consumerSayHello(@RequestParam(name = "name", defaultValue = "consumer") String name) {
        LOGGER.info("[consumerSayHello]--[端口={}]--name = {}", port, name);
        return consumerHelloService.consumerSayHello(name);
    }

    /**
     * Hi 服务
     * 若是参数名name为NULL，则直接抛出异常
     *
     * @param name 姓名
     * @return String
     */
    @GetMapping("/hi")
    String consumerSayHi(@RequestParam(value = "name", defaultValue = "provider") String name) {
        LOGGER.info("[consumerSayHi]--[端口={}]--name = {}", port, name);
        return consumerHelloService.consumerSayHi(name);
    }


    /**
     * getName 服务
     * 返回 hello + UUID
     *
     * @return String
     */
    @GetMapping("/getName")
    String consumerGetName() {
        LOGGER.info("[consumerGetName]--[端口={}]--参数为NULL", port);
        return "hello! " + UUID.randomUUID().toString();
    }
}
