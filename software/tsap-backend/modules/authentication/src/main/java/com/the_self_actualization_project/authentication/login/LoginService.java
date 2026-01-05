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

package com.the_self_actualization_project.authentication.login;

import com.the_self_actualization_project.usermanagement.application.port.in.FindOrRegisterBySubjectCommand;
import com.the_self_actualization_project.usermanagement.application.port.in.FindOrRegisterBySubjectUseCase;
import com.the_self_actualization_project.usermanagement.domain.OAuth2Provider;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import com.the_self_actualization_project.authentication.bearer.AuthBearerTokenCreationService;
import com.the_self_actualization_project.authentication.bearer.dto.BearerAccessRefreshTokenPairDto;
import com.the_self_actualization_project.authentication.google.GoogleTokenService;
import com.the_self_actualization_project.authentication.jwt.JWTService;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class LoginService {

  private final GoogleTokenService googleTokenService;
  private final FindOrRegisterBySubjectUseCase findOrRegisterBySubjectUseCase;
  private final AuthBearerTokenCreationService authBearerTokenCreationService;
  private final JWTService jwtService;

  /**
   * <a
   * href="https://developers.google.com/identity/gsi/web/guides/verify-google-id-token">Google
   * guide on verifying token from Google.</a>
   */
  public BearerAccessRefreshTokenPairDto googleLogin(String googleIdToken) {
    var googleUserLoginResult = googleTokenService.processGoogleIdToken(
        googleIdToken);

    return processLogin(
        new FindOrRegisterBySubjectCommand(googleUserLoginResult.subject(),
            googleUserLoginResult.email(), OAuth2Provider.GOOGLE));
  }

  public Optional<BearerAccessRefreshTokenPairDto> facebookLogin(
      String facebookIdToken) {
    // @TODO
    return Optional.empty();
  }

  private BearerAccessRefreshTokenPairDto processLogin(
      FindOrRegisterBySubjectCommand findOrRegisterBySubjectCommand) {

    var userDto = findOrRegisterBySubjectUseCase.findOrRegisterBySubject(
        findOrRegisterBySubjectCommand);
    var refreshTokenId = jwtService.createRefreshTokenRegistration(
        userDto.userId());
    return authBearerTokenCreationService.createAccessAndRefreshToken(
        userDto.userId().toString(), refreshTokenId);
  }
}
