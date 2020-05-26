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

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: yonghong.zhang@hand-china.com
 * @date: 2020/5/26 10:11
 * @version: 1.0.0
 * @description: 服务提供者，提供Hello服务
 * 设置默认错误回调方法 @DefaultProperties(defaultFallback = "providerGlobalFallback")
 */

@RestController
@RequestMapping("/provider")
@DefaultProperties(defaultFallback = "providerGlobalFallback")
public class ProviderHelloController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProviderHelloController.class);

    /**
     * 设置默认用户
     */
    private final static String PROVIDER = "provider";

    /**
     * 端口
     */
    @Value("${server.port}")
    String port;

    /**
     * Hello 服务
     *
     * @param name 姓名
     * @return String
     * @description 对于sayHello方法，我们指定了固定的失败回调函数errorSayHello，
     * 所以errorSayHello的参数必须要与sayHello方法参数一致，不然就会出现找不到该方法的异常
     */
    @GetMapping("/hello")
    @HystrixCommand(fallbackMethod = "providerErrorSayHello")
    String providerSayHello(@RequestParam(value = "name", defaultValue = "provider") String name) {
        String result = "Hello,I am " + name + ",I from " + port;
        LOGGER.info("[sayHello]---result = {}", result);
        return result;
    }

    /**
     * 当Hello 出现异常，下面的方法进行处理
     */
    public String providerErrorSayHello(String name) {
        String result = "Sorry, I am busy! I am " + name;
        LOGGER.info("[errorSayHello]---result = {}", result);
        return result;
    }

    /**
     * Hi 服务
     * 若是参数名name为provider，则直接抛出异常
     *
     * @param name 姓名
     * @return String
     * @description 对于sayHi我们没有指定特殊的失败回调函数，所以默认使用了
     * 全局失败回调函数@DefaultProperties(defaultFallback = "globalFallback")，而globalFallback则是无参的函数
     */
    @GetMapping("/hi")
    @HystrixCommand
    String providerSayHi(@RequestParam(value = "name", defaultValue = "provider") String name) throws Exception {
        String result = "hi,I am " + name + ",I from " + port;
        if (PROVIDER.equals(name)) {
            LOGGER.error("[sayHi]--- throw new exception!");
            throw new Exception("name is provider");
        } else {
            LOGGER.info("[sayHi]---result = {}", result);
            return result;
        }
    }

    /**
     * 全局fallBack处理方法
     */
    private String providerGlobalFallback() {
        String result = "Sorry ,I failed, I from globalFallback";
        LOGGER.info("[globalFallback]---result = {}", result);
        return result;
    }
}
