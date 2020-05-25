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
 * @date: 2020/5/25 10:03
 * @version: 1.0.0
 * @description: Hello请求控制类
 */

@RestController
@RequestMapping("/provider")
public class HelloController {
    /**
     * 端口
     */
    @Value("${server.port}")
    private String port;


    /**
     * 外部hello 请求
     *
     * @param name 姓名， 可选参数
     * @return String
     */
    @GetMapping("/hello")
    public String sayHello(@RequestParam(value = "name", defaultValue = "provider") String name) {
        return "Hello,I am " + name + ",I from " + port;
    }
}
