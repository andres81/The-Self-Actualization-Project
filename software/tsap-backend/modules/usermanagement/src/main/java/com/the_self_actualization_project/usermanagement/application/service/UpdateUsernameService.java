package com.the_self_actualization_project.usermanagement.application.service;

import com.the_self_actualization_project.usermanagement.application.port.in.UpdateUsernameUseCase;
import com.the_self_actualization_project.usermanagement.application.port.out.FindByUserIdPort;
import com.the_self_actualization_project.usermanagement.application.port.out.PersistUserPort;
import com.the_self_actualization_project.usermanagement.exception.UserException;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UpdateUsernameService implements UpdateUsernameUseCase {

  private final FindByUserIdPort findByUserIdPort;
  private final PersistUserPort persistUserPort;

  @Override
  @Transactional
  public void updateUsername(UUID userId, String newUsername) {
    var user = findByUserIdPort.findByUserId(userId)
        .orElseThrow(() -> new UserException("Could not find user"));
    var updatedUser = user.updateUsername(newUsername);
    persistUserPort.persistUser(updatedUser);
  }
}
