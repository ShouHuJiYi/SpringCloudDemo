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
import org.springframework.beans.factory.annotation.Autowired;
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

    /**
     * set注入 helloService
     */
    ConsumerHelloService consumerHelloService;

    /**
     * set注入 helloService
     */
    @Autowired
    ConsumerHelloService setHelloService(ConsumerHelloService consumerHelloService) {
        return this.consumerHelloService = consumerHelloService;
    }

    /**
     * Hello 服务
     *
     * @param name 姓名
     * @return String
     */
    @GetMapping("/hello")
    String consumerSayHello(@RequestParam(name = "name", defaultValue = "consumer") String name) {
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
        return consumerHelloService.consumerSayHi(name);
    }


    /**
     * Hi 服务
     * 若是参数名name为NULL，则直接抛出异常
     *
     * @return String
     */
    @GetMapping("/getName")
    String consumerGetName() {
        return "hello! " + UUID.randomUUID().toString();
    }

}
