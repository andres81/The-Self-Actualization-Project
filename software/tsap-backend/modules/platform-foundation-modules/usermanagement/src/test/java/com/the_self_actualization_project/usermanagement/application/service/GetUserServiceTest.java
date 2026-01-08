package com.the_self_actualization_project.usermanagement.application.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.the_self_actualization_project.usermanagement.application.port.out.FindByUserIdPort;

import java.util.Optional;
import java.util.UUID;

import com.the_self_actualization_project.usermanagement.domain.UserEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class GetUserServiceTest {

  private final UUID USER_ID = UUID.randomUUID();

  @Mock private FindByUserIdPort findByUserIdPort;

  @InjectMocks private GetUserService cut;

  @Test
  void givenUserId_whenUserRequestedAndPresent_thenUserReturned() {

    var userEntityMock = mock(UserEntity.class);

    when(findByUserIdPort.findByUserId(USER_ID)).thenReturn(Optional.of(userEntityMock));

    var result = cut.getUserByUserId(USER_ID);

    assertEquals(userEntityMock, result);
  }

  @Test
  void givenUserId_whenUserRequestedAndNotPresent_thenExceptionThrown() {

    when(findByUserIdPort.findByUserId(USER_ID)).thenReturn(Optional.empty());

    var ex = assertThrows(Exception.class, () -> cut.getUserByUserId(USER_ID));

    assertEquals("Could not find user", ex.getMessage());
  }
}
