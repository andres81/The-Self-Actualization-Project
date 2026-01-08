package com.the_self_actualization_project.usermanagement.application.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

import com.the_self_actualization_project.usermanagement.application.port.out.ExistsByUsernamePort;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CheckUsernameExistenceServiceTest {

  private static final String USERNAME = "USERNAME";

  @Mock private ExistsByUsernamePort existsByUsernamePort;

  @InjectMocks private CheckUsernameExistenceService cut;

  @Test
  void doesUsernameExist() {
    cut.doesUsernameExist(USERNAME);
    verify(existsByUsernamePort).existsByUsername(USERNAME);
  }
}
