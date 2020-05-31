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
package com.example.microservicezuul.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * @author: yonghong.zhang@hand-china.com
 * @date: 2020/5/31 16:42
 * @version: 1.0.0
 * @description: 自定义过滤器，实现校验token的逻辑
 */
@Component
public class MyZuulFilter extends ZuulFilter {

    /**
     * 日志
     */
    private final static Logger LOGGER = LoggerFactory.getLogger(MyZuulFilter.class);

    /**
     * 过滤器执行的时间次序依次时：
     * 前置过滤器（pre Filter）
     * 路由过滤器（Route Filter）
     * 目标服务 （Post Filter）
     * 错误过滤器 Error Filter）
     */
    private final static String FILTER_TYPE = "pre";
    private final static int FILTER_ORDER = 0;

    /**
     * 过滤器类型
     */
    @Override
    public String filterType() {
        LOGGER.info("加载过滤器类型--{}", FILTER_TYPE);
        return FILTER_TYPE;
    }

    /**
     * 过滤器的顺序
     */

    @Override
    public int filterOrder() {
        LOGGER.info("加载过滤器顺序--{}", FILTER_ORDER);
        return FILTER_ORDER;
    }

    /**
     * 过滤器是否启用  false 和 true
     */
    @Override
    public boolean shouldFilter() {
        LOGGER.info("过滤器是否启用");
        return true;
    }

    /**
     * 过滤内容
     * 模拟校验一个 Token
     *
     * @throws ZuulException 路由异常
     */
    @Override
    public Object run() throws ZuulException {
        LOGGER.info("---路由处理开始---");
        RequestContext requestContext = RequestContext.getCurrentContext();
        HttpServletRequest request = requestContext.getRequest();
        LOGGER.info(String.format("%s >>> %s", request.getMethod(), request.getRequestURL().toString()));
        Object accessToken = request.getParameter("token");
        if (accessToken == null) {
            LOGGER.warn("Token Is Empty");
            requestContext.setSendZuulResponse(false);
            requestContext.setResponseStatusCode(401);
            try {
                requestContext.getResponse().getWriter().write("Token Is Empty");
            } catch (Exception e) {
                LOGGER.error("出现了异常 {}", e.getMessage());
            }
            return null;
        }
        LOGGER.info("OK, Token = {}", accessToken);
        return null;
    }
}
