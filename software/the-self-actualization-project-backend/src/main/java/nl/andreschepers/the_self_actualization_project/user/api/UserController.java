package nl.andreschepers.the_self_actualization_project.user.api;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nl.andreschepers.the_self_actualization_project.user.service.UserService;
import nl.andreschepers.the_self_actualization_project.user.service.dto.UserInfo;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {

  private final UserService userService;

  @GetMapping("/info")
  public ResponseEntity<UserInfo> getUserInfo(@AuthenticationPrincipal Jwt jwt) {
    var userId = jwt.getSubject();
    return ResponseEntity.ok(userService.getUserInfoByUserId(userId));
  }

  @PutMapping("/update-username/{username}")
  public ResponseEntity<Void> updateUserName(
      @AuthenticationPrincipal Jwt jwt, @PathVariable("username") String newUsername) {
    var userId = jwt.getSubject();
    userService.updateUsername(userId, newUsername);
    return ResponseEntity.noContent().build();
  }

  @GetMapping("/exists-username")
  public ResponseEntity<Boolean> existsUsername(String username) {
    return ResponseEntity.ok(userService.doesUsernameExist(username));
  }
}
