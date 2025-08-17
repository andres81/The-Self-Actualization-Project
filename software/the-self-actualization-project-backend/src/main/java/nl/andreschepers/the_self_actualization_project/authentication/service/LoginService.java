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

package nl.andreschepers.the_self_actualization_project.authentication.service;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import nl.andreschepers.the_self_actualization_project.authentication.google.GoogleIdTokenVerifierService;
import nl.andreschepers.the_self_actualization_project.authentication.google.dto.GoogleIDTokenDto;
import nl.andreschepers.the_self_actualization_project.authentication.jwt.JWTService;
import nl.andreschepers.the_self_actualization_project.authentication.jwt.dto.JWTAccessRefreshTokenPairDto;
import nl.andreschepers.the_self_actualization_project.user.service.UserService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginService {

  private final GoogleIdTokenVerifierService googleIdTokenVerifierService;
  private final UserService userService;
  private final JWTService jwtService;

  /**
   * <a href="https://developers.google.com/identity/gsi/web/guides/verify-google-id-token">Google
   * guide on verifying token from Google.</a>
   */
  public Optional<JWTAccessRefreshTokenPairDto> googleLogin(String googleIdToken) {
    return googleIdTokenVerifierService
        .processGoogleIdToken(googleIdToken)
        .map(GoogleIDTokenDto::subject)
        .map(userService::findOrRegisterBySubject)
        .map(jwtService::createAccessAndRefreshToken);
  }
}
