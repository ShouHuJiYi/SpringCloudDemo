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
package com.example.microservicezuul.fallback;

import com.netflix.hystrix.exception.HystrixTimeoutException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.netflix.zuul.filters.route.FallbackProvider;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author: yonghong.zhang@hand-china.com
 * @date: 2020/5/31 17:18
 * @version: 1.0.0
 * @description: 服务熔断后处理方式
 * <p>
 * Zuul 目前只支持服务级别的熔断，不支持具体到某个URL进行熔断。
 */
@Component
public class MyFallbackProvider implements FallbackProvider {
    /**
     * 日志
     */
    private static Logger LOGGER = LoggerFactory.getLogger(MyFallbackProvider.class);

    /**
     * 回退的路由
     * 对所有路由进行处理
     */
    @Override
    public String getRoute() {
        LOGGER.info("获取处理路由");
        return "*";
    }

    @Override
    public ClientHttpResponse fallbackResponse(String route, Throwable cause) {
        if (cause instanceof HystrixTimeoutException) {
            return response(HttpStatus.GATEWAY_TIMEOUT);
        } else {
            return response(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private ClientHttpResponse response(final HttpStatus httpStatus) {
        return new ClientHttpResponse() {
            /** 请求状态*/
            @Override
            public HttpStatus getStatusCode() throws IOException {
                return httpStatus;
            }

            /**
             * 请求状态值
             * */
            @Override
            public int getRawStatusCode() throws IOException {
                return httpStatus.value();
            }

            /** 请求体*/
            @Override
            public String getStatusText() throws IOException {
                return httpStatus.getReasonPhrase();
            }

            @Override
            public void close() {
            }

            /** 返回内容*/
            @Override
            public InputStream getBody() throws IOException {
                return new ByteArrayInputStream(" Hello Fallback,I am Error.".getBytes());
            }

            @Override
            public HttpHeaders getHeaders() {
                HttpHeaders httpHeaders = new HttpHeaders();
                httpHeaders.setContentType(MediaType.APPLICATION_JSON);
                return httpHeaders;
            }
        };
    }
}
