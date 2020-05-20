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
package com.example.provider.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author: yonghong.zhang@hand-china.com
 * @date: 2020/5/20 14:32
 * @version: 1.0.0
 * @description: 用户实体类
 */
@Entity
@Data
public class User implements Serializable {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    /**
     * 用户名
     */
    @Column
    private String username;
    /**
     * 姓名
     */
    @Column
    private String name;
    /**
     * 年龄
     */
    @Column
    private int age;
    /**
     * 余额
     */
    @Column
    private BigDecimal balance;
}
