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

package nl.andreschepers.the_self_actualization_project.authentication.api.jwt;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import nl.andreschepers.the_self_actualization_project.authentication.api.dto.AccessTokenResponseDto;
import nl.andreschepers.the_self_actualization_project.authentication.api.exception.ForbiddenException;
import nl.andreschepers.the_self_actualization_project.authentication.api.exception.UnAuthorizedException;
import nl.andreschepers.the_self_actualization_project.authentication.api.util.CookieUtil;
import nl.andreschepers.the_self_actualization_project.authentication.jwt.JWTService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth/refreshtoken")
@RequiredArgsConstructor
public class JwtRefreshTokenController {

  private final CookieUtil cookieUtil;
  private final JWTService jwtService;

  @GetMapping
  public ResponseEntity<AccessTokenResponseDto> refreshToken(HttpServletRequest request) {

    var cookies = request.getCookies();
    var jwtCookie = cookieUtil.findRefreshCookie(cookies);
    if (jwtCookie.isEmpty()) {
      throw new UnAuthorizedException();
    }

    var accessRefreshTokenPair =
        jwtService
            .processRefreshTokenRenewal(jwtCookie.get().getValue())
            .orElseThrow(ForbiddenException::new);

    var cookie = cookieUtil.createRefreshTokenCookie(accessRefreshTokenPair.refreshToken());

    return ResponseEntity.ok()
        .header(HttpHeaders.SET_COOKIE, cookie.toString())
        .body(new AccessTokenResponseDto(accessRefreshTokenPair.accessToken()));
  }
}
