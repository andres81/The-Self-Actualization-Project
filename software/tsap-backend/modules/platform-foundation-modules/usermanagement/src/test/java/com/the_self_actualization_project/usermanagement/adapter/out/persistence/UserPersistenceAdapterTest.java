package com.the_self_actualization_project.usermanagement.adapter.out.persistence;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.the_self_actualization_project.usermanagement.domain.OAuth2Provider;
import com.the_self_actualization_project.usermanagement.domain.UserEntity;
import java.util.Optional;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class UserPersistenceAdapterTest {

  private static final UUID USER_ID = UUID.randomUUID();
  private static final String SUBJECT = "SUBJECT";
  private static final String USER_NAME = "USER_NAME";

  @Mock private UserRepository userRepository;

  @InjectMocks private UserPersistenceAdapter cut;

  @Test
  void findBySubject() {

    when(userRepository.findBySubject(SUBJECT)).thenReturn(Optional.of(createUserJpaEntity()));

    var result = cut.findBySubject(SUBJECT);

    assertEquals("subject", result.get().subject());
  }

  @Test
  void findByUserId() {

    when(userRepository.findByUserId(USER_ID)).thenReturn(Optional.of(createUserJpaEntity()));

    var result = cut.findByUserId(USER_ID);

    assertEquals("subject", result.get().subject());
  }

  @Test
  void existsByUsername() {

    when(userRepository.existsByUsername(USER_NAME)).thenReturn(true);

    var result = cut.existsByUsername(USER_NAME);

    assertTrue(result);
  }

  @Test
  void givenUserEntity_whenPersistUser_andUserExistsInDb_thenDbUserUpdatedWithNewValues() {

    var userEntity =
        new UserEntity(
            USER_ID, "NEW_USERNAME", "NEW_SUBJECT", OAuth2Provider.GOOGLE, "NEW_EMAIL@GMAIL.COM");

    var jpaEntity = createUserJpaEntity();
    when(userRepository.findByUserId(USER_ID)).thenReturn(Optional.of(jpaEntity));

    cut.persistUser(userEntity);

    var jpaEntityCaptor = ArgumentCaptor.forClass(UserJpaEntity.class);
    verify(userRepository).save(jpaEntityCaptor.capture());
    var capturedValue = jpaEntityCaptor.getValue();
    assertEquals(USER_ID, capturedValue.getUserId());
    assertEquals("NEW_USERNAME", capturedValue.getUsername());
    assertEquals("NEW_SUBJECT", capturedValue.getSubject());
    assertEquals(OAuth2Provider.GOOGLE, capturedValue.getProvider());
    assertEquals("NEW_EMAIL@GMAIL.COM", capturedValue.getEmail());
  }

  @Test
  void givenUserEntity_whenPersistUser_andUserDoesNotExistsInDb_thenDbUserCreatedWithNewValues() {
    var userEntity =
        new UserEntity(
            USER_ID, "NEW_USERNAME", "NEW_SUBJECT", OAuth2Provider.GOOGLE, "NEW_EMAIL@GMAIL.COM");

    when(userRepository.findByUserId(USER_ID)).thenReturn(Optional.empty());

    cut.persistUser(userEntity);

    var jpaEntityCaptor = ArgumentCaptor.forClass(UserJpaEntity.class);
    verify(userRepository).save(jpaEntityCaptor.capture());
    var capturedValue = jpaEntityCaptor.getValue();
    assertEquals(USER_ID, capturedValue.getUserId());
    assertEquals("NEW_USERNAME", capturedValue.getUsername());
    assertEquals("NEW_SUBJECT", capturedValue.getSubject());
    assertEquals(OAuth2Provider.GOOGLE, capturedValue.getProvider());
    assertEquals("NEW_EMAIL@GMAIL.COM", capturedValue.getEmail());
  }

  private UserJpaEntity createUserJpaEntity() {
    var userJpaEntity = new UserJpaEntity();
    userJpaEntity.setSubject("subject");
    userJpaEntity.setUserId(USER_ID);
    userJpaEntity.setUsername("username");
    userJpaEntity.setProvider(OAuth2Provider.FACEBOOK);
    userJpaEntity.setEmail("email@gmail.com");

    return userJpaEntity;
  }
}
