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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author: yonghong.zhang@hand-china.com
 * @date: 2020/5/26 11:20
 * @version: 1.0.0
 * @description:
 */

@Component
public class ConsumerHelloFallbackService implements ConsumerHelloService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ConsumerHelloFallbackService.class);

    @Value("${server.port}")
    String port;

    @Override
    public String consumerSayHello(String name) {
        String result = "Sorry,I from " + port;
        LOGGER.info("[HelloFallbackService.sayHello]---result = {}", result);
        return result;
    }

    @Override
    public String consumerSayHi(String name) {
        String result = "Sorry,I from " + port;
        LOGGER.info("[HelloFallbackService.sayHi]---result = {}", result);
        return result;
    }
}
