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

import com.example.microserviceconsumer.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: yonghong.zhang@hand-china.com
 * @date: 2020/5/22 15:32
 * @version: 1.0.0
 * @description: Hello 控制类
 */

@RestController
@RequestMapping(value = "consumer")
public class HelloController {

    /**
     * 定义HelloService
     */
    private HelloService helloService;

    /**
     * 使用构造器注入HelloService
     */
    @Autowired
    public void setHelloService(HelloService helloService) {
        this.helloService = helloService;
    }

    /**
     * 外部访问Hello请求
     */
    @GetMapping("/hello")
    public String sayHello(@RequestParam(value = "name", defaultValue = "consumer") String name) {
        return helloService.sayHelloService(name);
    }
}
