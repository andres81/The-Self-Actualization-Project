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

package nl.andreschepers.the_self_actualization_project.authentication.api.util;

import jakarta.servlet.http.Cookie;
import java.time.Duration;
import java.util.Arrays;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import nl.andreschepers.the_self_actualization_project.configuration.properties.AuthenticationConfigProperties;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CookieUtil {

  private static final String REFRESH_TOKEN_COOKIE_PATH = "/api/auth";
  private static final String REFRESH_TOKEN_COOKIE_NAME = "refreshtoken_cookie";

  private final AuthenticationConfigProperties authenticationConfigProperties;

  /**
   * Opiniated creator of a {@link ResponseCookie} with default settings.
   *
   * @param refreshToken
   * @return
   */
  public ResponseCookie createRefreshTokenCookie(String refreshToken) {
    return ResponseCookie.from(REFRESH_TOKEN_COOKIE_NAME, refreshToken)
        .secure(true)
        .httpOnly(true)
//        .domain(authenticationConfigProperties.refreshTokenCookieDomain())
//        .domain("localhost")
        .sameSite("Strict")
        .maxAge(Duration.ofMinutes(30L))
        .path(REFRESH_TOKEN_COOKIE_PATH)
        .build();
  }

  public Optional<Cookie> findRefreshCookie(Cookie[] cookies) {
    if (cookies == null || cookies.length == 0) {
      return Optional.empty();
    }
    return Arrays.stream(cookies)
        .filter(cookie -> cookie.getName().equals(REFRESH_TOKEN_COOKIE_NAME))
        .findFirst();
  }
}
