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

package nl.andreschepers.the_self_actualization_project.user.service.dto;

import java.util.UUID;

public record UserDto(
    UUID userId,
    String subject,
    String email,
    String firstName,
    String lastName,
    String pictureUrl) {

  public UserDto(UUID userId) {
    this(userId, null, null, null, null, null);
  }

  public UserDto(UUID userId, String subject) {
    this(userId, subject, null, null, null, null);
  }
}
