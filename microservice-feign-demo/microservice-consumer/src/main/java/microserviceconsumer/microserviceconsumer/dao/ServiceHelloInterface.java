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
package microserviceconsumer.microserviceconsumer.dao;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author: yonghong.zhang@hand-china.com
 * @date: 2020/5/25 10:16
 * @version: 1.0.0
 * @description: openfeign客户端接口
 */
@FeignClient(value = "MICROSERVICE-PROVIDER")
public interface ServiceHelloInterface {

    /**
     * 服务提供者中提供的Hello服务
     * 请求地址：provider/hello
     *
     * @param name 用户名 可选参数name
     * @return String
     */

    @GetMapping("/provider/hello")
    String sayHello(@RequestParam(value = "name", defaultValue = "consumer") String name);
}
