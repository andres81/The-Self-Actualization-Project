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
