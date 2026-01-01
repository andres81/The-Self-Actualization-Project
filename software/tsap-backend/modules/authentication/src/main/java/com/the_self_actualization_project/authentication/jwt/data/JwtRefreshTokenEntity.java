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

package com.the_self_actualization_project.authentication.jwt.data;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import java.util.UUID;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import com.the_self_actualization_project.authentication.jwt.data.generator.UUIDV7Id;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "jwt_refresh_token_entity")
public class JwtRefreshTokenEntity {

  @Id
  @UUIDV7Id
  private UUID id;
  @NotNull
  private UUID userId;

  @NotNull
  @Enumerated(EnumType.STRING)
  private Status status = Status.ACTIVE;

  public JwtRefreshTokenEntity(UUID userId) {
    this.userId = userId;
  }

  public enum Status {
    ACTIVE,
    INACTIVE
  }
}
