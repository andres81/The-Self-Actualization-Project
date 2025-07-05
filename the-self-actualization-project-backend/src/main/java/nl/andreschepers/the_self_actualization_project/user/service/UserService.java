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

package nl.andreschepers.the_self_actualization_project.user.service;

import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import nl.andreschepers.the_self_actualization_project.user.data.UserEntity;
import nl.andreschepers.the_self_actualization_project.user.data.UserRepository;
import nl.andreschepers.the_self_actualization_project.user.service.dto.UserDto;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

  private final UserRepository userRepository;

  public UserDto findOrRegisterBySubject(String subject) {
    var userEntity =
        userRepository.findBySubject(subject).orElseGet(() -> createUserEntity(subject));
    return new UserDto(userEntity.getId(), userEntity.getSubject());
  }

  public Optional<UserDto> findByUserId(UUID userId) {
    return userRepository
        .findById(userId)
        .map(userEntity -> new UserDto(userEntity.getId(), userEntity.getSubject()));
  }

  private UserEntity createUserEntity(String subject) {
    var newUser = new UserEntity();
    newUser.setSubject(subject);
    return userRepository.save(newUser);
  }
}
