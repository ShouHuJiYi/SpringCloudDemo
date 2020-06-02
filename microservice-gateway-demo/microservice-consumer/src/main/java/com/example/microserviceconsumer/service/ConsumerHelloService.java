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
package com.example.microserviceconsumer.service;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author: yonghong.zhang@hand-china.com
 * @date: 2020/5/26 10:58
 * @version: 1.0.0
 * @description: openFeign客户端，开启断路回调 HelloFallbackService 方法
 * 若是服务提供方出现了异常，则直接调用异常处理方法 HelloFallbackService 进行处理
 */

@Component
@FeignClient(value = "MICROSERVICE-PROVIDER", fallback = ConsumerHelloFallbackService.class)
public interface ConsumerHelloService {
    /**
     * Hello 服务
     *
     * @param name 姓名
     * @return String
     */

    @GetMapping("/provider/hello")
    String consumerSayHello(@RequestParam(value = "name", defaultValue = "consumer") String name);

    /**
     * Hi 服务
     * 若是参数名name为NULL，则直接抛出异常
     *
     * @param name 姓名
     * @return String
     */
    @GetMapping("/provider/hi")
    String consumerSayHi(@RequestParam(value = "name", defaultValue = "provider") String name);
}
