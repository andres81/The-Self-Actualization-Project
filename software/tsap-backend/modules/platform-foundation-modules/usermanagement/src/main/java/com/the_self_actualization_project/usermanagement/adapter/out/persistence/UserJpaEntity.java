/*
 * Copyright 2025 André Schepers
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

package com.the_self_actualization_project.usermanagement.adapter.out.persistence;

import com.the_self_actualization_project.usermanagement.adapter.out.persistence.auditing.EntityAudit;
import com.the_self_actualization_project.usermanagement.adapter.out.persistence.generator.UUIDV7Id;
import com.the_self_actualization_project.usermanagement.domain.OAuth2Provider;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "user_account")
@Getter
@Setter
public class UserJpaEntity extends EntityAudit {

  @Id @UUIDV7Id private UUID id;
  private UUID userId;
  private String username;
  private String subject;

  @Enumerated(EnumType.STRING)
  private OAuth2Provider provider;

  private String email;
}
