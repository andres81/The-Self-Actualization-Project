package com.the_self_actualization_project.usermanagement.application.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.the_self_actualization_project.usermanagement.application.port.in.FindOrRegisterBySubjectCommand;
import com.the_self_actualization_project.usermanagement.application.port.out.FindBySubjectPort;
import com.the_self_actualization_project.usermanagement.application.port.out.PersistUserPort;
import com.the_self_actualization_project.usermanagement.domain.OAuth2Provider;
import com.the_self_actualization_project.usermanagement.domain.UserEntity;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class FindOrRegisterBySubjectServiceTest {

  private static final String SUBJECT = "SUBJECT";
  private static final String EMAIL = "EMAIL@gmail.com";
  private static final OAuth2Provider PROVIDER = OAuth2Provider.FACEBOOK;

  @Mock private FindBySubjectPort findBySubjectPort;

  @Mock private PersistUserPort persistUserPort;

  @InjectMocks private FindOrRegisterBySubjectService cut;

  @Test
  void givenCommand_whenUserEntityFoundBySubject_thenReturnUserEntity() {
    var userEntityMock = mock(UserEntity.class);
    var command = createCommand();
    when(findBySubjectPort.findBySubject(SUBJECT)).thenReturn(Optional.of(userEntityMock));
    var result = cut.findOrRegisterBySubject(command);
    assertEquals(userEntityMock, result);
  }

  @Test
  void givenCommand_whenUserEntityNotFoundBySubject_thenNewUserEntityCreatedAndPersisted() {

    var command = createCommand();
    when(findBySubjectPort.findBySubject(SUBJECT)).thenReturn(Optional.empty());

    var result = cut.findOrRegisterBySubject(command);

    var userEntityCaptor = ArgumentCaptor.forClass(UserEntity.class);
    verify(persistUserPort).persistUser(userEntityCaptor.capture());
    var persistedUserEntity = userEntityCaptor.getValue();

    assertEquals(SUBJECT, persistedUserEntity.subject());
    assertEquals(EMAIL, persistedUserEntity.email());
    assertEquals(PROVIDER, persistedUserEntity.provider());

    assertEquals(persistedUserEntity, result);
  }

  private FindOrRegisterBySubjectCommand createCommand() {
    return new FindOrRegisterBySubjectCommand(SUBJECT, EMAIL, PROVIDER);
  }
}
