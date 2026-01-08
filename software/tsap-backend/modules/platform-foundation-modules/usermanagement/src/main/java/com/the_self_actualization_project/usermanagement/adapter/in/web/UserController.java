package com.the_self_actualization_project.usermanagement.adapter.in.web;

import com.the_self_actualization_project.usermanagement.adapter.in.web.mapper.UserInfoResponseMapper;
import com.the_self_actualization_project.usermanagement.adapter.in.web.response.UserInfoResponse;
import com.the_self_actualization_project.usermanagement.application.port.in.CheckUsernameExistenceUseCase;
import com.the_self_actualization_project.usermanagement.application.port.in.GetUserQuery;
import com.the_self_actualization_project.usermanagement.application.port.in.UpdateUsernameUseCase;
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
class UserController {

  private final UpdateUsernameUseCase updateUsernameUseCase;
  private final GetUserQuery getUserQuery;
  private final CheckUsernameExistenceUseCase checkUsernameExistenceUseCase;

  @GetMapping("/info")
  public ResponseEntity<UserInfoResponse> getUserInfo(@AuthenticationPrincipal Jwt jwt) {
    var userId = UUID.fromString(jwt.getSubject());
    var userEntity = getUserQuery.getUserByUserId(userId);
    var response = UserInfoResponseMapper.INSTANCE.toResponse(userEntity);
    return ResponseEntity.ok(response);
  }

  @PutMapping("/update-username/{username}")
  public ResponseEntity<Void> updateUserName(
      @AuthenticationPrincipal Jwt jwt, @PathVariable("username") String newUsername) {
    var userId = jwt.getSubject();
    updateUsernameUseCase.updateUsername(UUID.fromString(userId), newUsername);
    return ResponseEntity.noContent().build();
  }

  @GetMapping("/exists-username")
  public ResponseEntity<Boolean> existsUsername(String username) {
    return ResponseEntity.ok(checkUsernameExistenceUseCase.doesUsernameExist(username));
  }
}
