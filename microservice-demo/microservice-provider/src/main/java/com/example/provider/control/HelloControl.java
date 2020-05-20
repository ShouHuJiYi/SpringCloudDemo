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
package com.example.provider.control;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: yonghong.zhang@hand-china.com
 * @date: 2020/5/20 14:04
 * @version: 1.0.0
 * @description: 服务提供方 Hello 控制器
 */


@RestController
@RequestMapping(value = "/provider", produces = "application/json;charset=UTF-8")
public class HelloControl {

    /**
     * 获取服务端口
     */
    @Value("${server.port}")
    String port;

    /**
     * 测试方法hello
     */
    @GetMapping("/hello")
    public String sayHello() {
        return "Hello,I am from " + port;
    }
}
