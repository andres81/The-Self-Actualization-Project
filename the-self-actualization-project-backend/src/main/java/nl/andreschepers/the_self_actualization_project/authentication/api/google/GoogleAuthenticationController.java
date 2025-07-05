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

package nl.andreschepers.the_self_actualization_project.authentication.api.google;

import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nl.andreschepers.the_self_actualization_project.authentication.api.dto.AccessTokenResponseDto;
import nl.andreschepers.the_self_actualization_project.authentication.api.exception.UnAuthorizedException;
import nl.andreschepers.the_self_actualization_project.authentication.api.google.dto.LoginRequestDto;
import nl.andreschepers.the_self_actualization_project.authentication.api.util.CookieUtil;
import nl.andreschepers.the_self_actualization_project.authentication.google.GoogleUserLoginService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth/google")
@Validated
public class GoogleAuthenticationController {

  private final GoogleUserLoginService googleUserLoginService;
  private final CookieUtil cookieUtil;

  @PostMapping(value = "/login", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<AccessTokenResponseDto> googleAuthTokenEndpoint(
      @RequestBody @NotNull LoginRequestDto loginRequestDto) {

    var accessRefreshTokenPair =
        googleUserLoginService
            .login(loginRequestDto.googleIdToken())
            .orElseThrow(UnAuthorizedException::new);

    var cookie = cookieUtil.createRefreshTokenCookie(accessRefreshTokenPair.refreshToken());

    return ResponseEntity.ok()
        .header(HttpHeaders.SET_COOKIE, cookie.toString())
        .body(new AccessTokenResponseDto(accessRefreshTokenPair.accessToken()));
  }
}
