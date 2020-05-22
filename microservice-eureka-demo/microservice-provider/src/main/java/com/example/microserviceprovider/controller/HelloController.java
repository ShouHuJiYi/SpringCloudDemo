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
package com.example.microserviceprovider.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: yonghong.zhang@hand-china.com
 * @date: 2020/5/22 9:56
 * @version: 1.0.0
 * @description: 服务提供者的控制层
 */

@RestController
@RequestMapping(value = "/provider")
public class HelloController {

    /**
     * 读取端口配置
     */
    @Value("${server.port}")
    String port;

    /**
     * hello Get 请求
     */
    @GetMapping("/hello")
    public String sayHello(@RequestParam(value = "name", defaultValue = "provider") String name) {
        return "Hello,I am " + name + ",I from " + port;
    }
}
