package com.the_self_actualization_project.usermanagement.application.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.the_self_actualization_project.usermanagement.application.port.out.FindByUserIdPort;
import com.the_self_actualization_project.usermanagement.application.port.out.PersistUserPort;
import com.the_self_actualization_project.usermanagement.domain.UserEntity;
import java.util.Optional;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class UpdateUsernameServiceTest {

  private static final UUID USER_ID = UUID.randomUUID();
  private static final String NEW_USER_NAME = "NEW_USER_NAME";

  @Mock private FindByUserIdPort findByUserIdPort;

  @Mock private PersistUserPort persistUserPort;

  @InjectMocks private UpdateUsernameService cut;

  @Test
  void updateUsername() {

    var userEntityMock = mock(UserEntity.class);
    var updatedUserEntityMock = mock(UserEntity.class);

    when(findByUserIdPort.findByUserId(USER_ID)).thenReturn(Optional.of(userEntityMock));
    when(userEntityMock.updateUsername(NEW_USER_NAME)).thenReturn(updatedUserEntityMock);

    cut.updateUsername(USER_ID, NEW_USER_NAME);

    verify(userEntityMock).updateUsername(NEW_USER_NAME);
    verifyNoMoreInteractions(userEntityMock);
    verify(persistUserPort).persistUser(updatedUserEntityMock);
  }
}
