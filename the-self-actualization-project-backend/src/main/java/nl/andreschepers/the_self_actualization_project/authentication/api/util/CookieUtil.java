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
        .domain(authenticationConfigProperties.refreshTokenCookieDomain())
        .sameSite("Strict")
        .maxAge(Duration.ofDays(1))
        .path(REFRESH_TOKEN_COOKIE_PATH)
        .build();
  }

  public Optional<Cookie> findRefreshCookie(Cookie[] cookies) {
    return Arrays.stream(cookies)
        .filter(cookie -> cookie.getName().equals(REFRESH_TOKEN_COOKIE_NAME))
        .findFirst();
  }
}
