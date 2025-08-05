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

package nl.andreschepers.the_self_actualization_project.authentication.jwt;

import java.text.ParseException;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nl.andreschepers.the_self_actualization_project.authentication.jwt.data.JwtRefreshTokenRepository;
import nl.andreschepers.the_self_actualization_project.authentication.jwt.dto.JWTAccessRefreshTokenPairDto;
import nl.andreschepers.the_self_actualization_project.user.service.UserService;
import nl.andreschepers.the_self_actualization_project.user.service.dto.UserDto;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class JWTService {

  private final JwtRefreshTokenRepository jwtRefreshTokenRepository;
  private final JwtHmacService jwtHmacService;
  private final UserService userService;

  public JWTAccessRefreshTokenPairDto createAccessAndRefreshToken(UserDto userDto) {

    var accessToken = jwtHmacService.createHmacSignedAccessTokenJwt(userDto.userId().toString());
    var refreshToken =
        jwtHmacService.createHmacSignedRefreshTokenJwtWithSubject(userDto.userId().toString());

    return new JWTAccessRefreshTokenPairDto(accessToken, refreshToken);
  }

  public Optional<JWTAccessRefreshTokenPairDto> processRefreshTokenRenewal(String refreshToken) {

    var jwt = jwtHmacService.verifyHmacSignedRefreshTokenJwt(refreshToken);
    if (jwt.isEmpty()) {
      return Optional.empty();
    }

    String subject;
    try {
      subject = jwt.get().getJWTClaimsSet().getSubject();
    } catch (ParseException e) {
      return Optional.empty();
    }

    if (StringUtils.isBlank(subject)) {
      return Optional.empty();
    }

    UUID userId;
    try {
      userId = UUID.fromString(subject);
    } catch (IllegalArgumentException ex) {
      // The subject claim is not a UUID, abnormal situation that should never happen! Was it a
      // forged refresh token?
      log.error("Illegal state: JWT contains non UUID subject claim: [{}]", subject);
      jwtRefreshTokenRepository.deleteAllByRefreshToken(refreshToken);
      return Optional.empty();
    }

    var refreshTokenEntity = jwtRefreshTokenRepository.findByRefreshToken(refreshToken);
    if (refreshTokenEntity.isEmpty()) {
      // Token reuse
      jwtRefreshTokenRepository.deleteAllByUserId(userId);
      return Optional.empty();
    }

    var userDto = userService.findByUserId(userId);
    if (userDto.isEmpty()) {
      // No user found, for the userId present in the refresh token, so remove all refresh tokens
      // stored with the same userId.
      jwtRefreshTokenRepository.deleteAllByUserId(userId);
      return Optional.empty();
    }

    return Optional.of(createAccessAndRefreshToken(userDto.get()));
  }
}
