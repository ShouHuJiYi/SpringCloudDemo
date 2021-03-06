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
package com.example.provider;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

@AutoConfigureDataJpa
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ProviderDemoApplication.class, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class ProviderDemoApplicationTests {

    /**
     * 注入mockMvc
     */
    @Autowired
    private MockMvc mockMvc;


    /**
     * 测试hello请求
     */

    @Test
    void testHelloSayHello() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/provider/hello")
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(equalTo("Hello,I am from 8080")))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }


    /**
     * 测试Get方法
     */
    @Test
    void testUserGetById() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/provider/get/1")
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.username", is("001")))
                .andDo(MockMvcResultHandlers.print());
    }
}
