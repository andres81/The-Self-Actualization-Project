package com.the_self_actualization_project.usermanagement.api;

import com.the_self_actualization_project.usermanagement.application.port.in.CheckUsernameExistenceUseCase;
import com.the_self_actualization_project.usermanagement.application.port.in.GetUserQuery;
import com.the_self_actualization_project.usermanagement.application.port.in.UpdateUsernameUseCase;
import com.the_self_actualization_project.usermanagement.application.service.dto.UserInfoDto;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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

  private final UpdateUsernameUseCase updateUsernameUseCase;
  private final GetUserQuery getUserQuery;
  private final CheckUsernameExistenceUseCase checkUsernameExistenceUseCase;

  @GetMapping("/info")
  public ResponseEntity<UserInfoDto> getUserInfo(
      @AuthenticationPrincipal Jwt jwt) {
    var userId = jwt.getSubject();
    return ResponseEntity.ok(new UserInfoDto(
        getUserQuery.getUserByUserId(userId).username()));
  }

  @PutMapping("/update-username/{username}")
  public ResponseEntity<Void> updateUserName(
      @AuthenticationPrincipal Jwt jwt,
      @PathVariable("username") String newUsername) {
    var userId = jwt.getSubject();
    updateUsernameUseCase.updateUsername(UUID.fromString(userId), newUsername);
    return ResponseEntity.noContent().build();
  }

  @GetMapping("/exists-username")
  public ResponseEntity<Boolean> existsUsername(String username) {
    return ResponseEntity.ok(
        checkUsernameExistenceUseCase.doesUsernameExist(username));
  }
}
