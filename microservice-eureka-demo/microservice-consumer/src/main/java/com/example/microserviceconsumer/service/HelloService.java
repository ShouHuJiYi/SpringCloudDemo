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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @author: yonghong.zhang@hand-china.com
 * @date: 2020/5/22 15:28
 * @version: 1.0.0
 * @description: Hello 服务调用类，通过服务名调用服务提供者路径
 */

@Service
public class HelloService {

    /**
     * 定义restTemplate
     */
    private RestTemplate restTemplate;

    /**
     * 使用构造器注入
     */
    @Autowired
    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    /**
     * 远程调用服务提供者Hello方法
     */
    public String sayHelloService(String name) {
        return restTemplate.getForObject("http://MICROSERVICE-PROVIDER/provider/hello?name=" + name, String.class);
    }
}
