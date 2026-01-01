package com.the_self_actualization_project.usermanagement.application.service;

import com.the_self_actualization_project.usermanagement.application.port.in.CheckUsernameExistenceUseCase;
import com.the_self_actualization_project.usermanagement.application.port.out.ExistsByUsernamePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CheckUsernameExistenceService implements
    CheckUsernameExistenceUseCase {

  private final ExistsByUsernamePort existsByUsernamePort;

  public boolean doesUsernameExist(String username) {
    return existsByUsernamePort.existsByUsername(username);
  }
}
