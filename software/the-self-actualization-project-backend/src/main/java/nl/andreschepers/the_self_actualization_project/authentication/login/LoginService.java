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

package nl.andreschepers.the_self_actualization_project.authentication.login;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nl.andreschepers.the_self_actualization_project.authentication.bearer.AuthBearerTokenCreationService;
import nl.andreschepers.the_self_actualization_project.authentication.bearer.dto.BearerAccessRefreshTokenPairDto;
import nl.andreschepers.the_self_actualization_project.authentication.google.GoogleIdTokenVerifierService;
import nl.andreschepers.the_self_actualization_project.authentication.jwt.JWTService;
import nl.andreschepers.the_self_actualization_project.user.service.UserService;
import nl.andreschepers.the_self_actualization_project.user.service.UserService.UserLoginDto;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class LoginService {

  private final GoogleIdTokenVerifierService googleIdTokenVerifierService;
  private final UserService userService;
  private final AuthBearerTokenCreationService authBearerTokenCreationService;
  private final JWTService jwtService;

  /**
   * <a href="https://developers.google.com/identity/gsi/web/guides/verify-google-id-token">Google
   * guide on verifying token from Google.</a>
   */
  public BearerAccessRefreshTokenPairDto googleLogin(String googleIdToken) {
    var googleUserLoginResult = googleIdTokenVerifierService.processGoogleIdToken(googleIdToken);

    return processLogin(
        new UserLoginDto(googleUserLoginResult.subject(), googleUserLoginResult.email()));
  }

  public Optional<BearerAccessRefreshTokenPairDto> facebookLogin(String facebookIdToken) {
    // @TODO
    return Optional.empty();
  }

  private BearerAccessRefreshTokenPairDto processLogin(UserLoginDto userLoginDto) {

    var userDto = userService.findOrRegisterBySubject(userLoginDto);
    var refreshTokenId = jwtService.createRefreshTokenRegistration(userDto.userId());
    return authBearerTokenCreationService.createAccessAndRefreshToken(
        userDto.userId().toString(), refreshTokenId);
  }
}
