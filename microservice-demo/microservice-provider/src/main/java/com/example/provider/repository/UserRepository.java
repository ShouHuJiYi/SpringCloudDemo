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
package com.example.provider.repository;

import com.example.provider.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author: yonghong.zhang@hand-china.com
 * @date: 2020/5/20 14:37
 * @version: 1.0.0
 * @description: 继承 Spring Boot 的Jpa
 */

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
