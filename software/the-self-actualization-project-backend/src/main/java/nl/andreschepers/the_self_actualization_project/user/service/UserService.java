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

import java.util.UUID;
import lombok.RequiredArgsConstructor;
import nl.andreschepers.the_self_actualization_project.user.data.UserEntity;
import nl.andreschepers.the_self_actualization_project.user.data.UserRepository;
import nl.andreschepers.the_self_actualization_project.user.exception.UserException;
import nl.andreschepers.the_self_actualization_project.user.service.dto.UserDto;
import nl.andreschepers.the_self_actualization_project.user.service.dto.UserInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

  private final UserRepository userRepository;

  public UserDto findOrRegisterBySubject(UserLoginDto userLoginDto) {
    var userEntity =
        userRepository
            .findBySubject(userLoginDto.subject)
            .orElseGet(() -> createUserEntity(userLoginDto));
    return new UserDto(userEntity.getId(), userEntity.getSubject());
  }

  public UserInfo getUserInfoByUserId(String userId) {
    if (StringUtils.isBlank(userId)) {
      throw new UserException("user id is empty");
    }
    var userIdUUID = UUID.fromString(userId);
    return userRepository
        .findById(userIdUUID)
        .map(userEntity -> new UserInfo(userEntity.getUsername()))
        .orElseThrow(() -> new UserException("Could not find user"));
  }

  private UserEntity createUserEntity(UserLoginDto userLoginDto) {
    var newUser = new UserEntity();
    newUser.setSubject(userLoginDto.subject());
    newUser.setEmail(userLoginDto.email());
    newUser.setUsername(UUID.randomUUID().toString());
    return userRepository.save(newUser);
  }

  @Transactional
  public void updateUsername(String userId, String newUsername) {
    if (StringUtils.isBlank(userId)) {
      throw new UserException("user id is empty");
    }
    var userIdUUID = UUID.fromString(userId);
    var user = userRepository.findById(userIdUUID).orElseThrow(() -> new UserException("Could not find user"));
    user.setUsername(newUsername);
  }

  public boolean doesUsernameExist(String username) {
    return userRepository.existsByUsername(username);
  }

  public record UserLoginDto(String subject, String email) {}
}
