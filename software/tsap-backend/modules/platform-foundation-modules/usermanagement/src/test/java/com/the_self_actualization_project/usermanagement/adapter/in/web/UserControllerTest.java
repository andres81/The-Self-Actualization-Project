package com.the_self_actualization_project.usermanagement.adapter.in.web;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.the_self_actualization_project.usermanagement.application.port.in.CheckUsernameExistenceUseCase;
import com.the_self_actualization_project.usermanagement.application.port.in.GetUserQuery;
import com.the_self_actualization_project.usermanagement.application.port.in.UpdateUsernameUseCase;
import com.the_self_actualization_project.usermanagement.domain.OAuth2Provider;
import com.the_self_actualization_project.usermanagement.domain.UserEntity;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.security.oauth2.jwt.Jwt;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {

  private static final String NEW_USERNAME = "NEW_USERNAME";
  private static final String USERNAME = "USERNAME";
  private static final UUID USER_ID = UUID.randomUUID();

  @Mock private UpdateUsernameUseCase updateUsernameUseCase;

  @Mock private GetUserQuery getUserQuery;

  @Mock private CheckUsernameExistenceUseCase checkUsernameExistenceUseCase;

  @Mock private Jwt jwt;

  @InjectMocks private UserController cut;

  @Test
  void getUserInfo() {

    var userEntity =
        new UserEntity(
            USER_ID, "username", "subject", OAuth2Provider.FACEBOOK, "someemail@gmail.com");

    when(jwt.getSubject()).thenReturn(USER_ID.toString());
    when(getUserQuery.getUserByUserId(USER_ID)).thenReturn(userEntity);

    var response = cut.getUserInfo(jwt);

    verify(getUserQuery).getUserByUserId(USER_ID);

    assertEquals("username", response.getBody().username());
    assertEquals("subject", response.getBody().subject());
    assertEquals(OAuth2Provider.FACEBOOK, response.getBody().provider());
    assertEquals("someemail@gmail.com", response.getBody().email());
  }

  @Test
  void updateUserName() {

    when(jwt.getSubject()).thenReturn(USER_ID.toString());

    var response = cut.updateUserName(jwt, NEW_USERNAME);

    verify(updateUsernameUseCase).updateUsername(USER_ID, NEW_USERNAME);

    assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
  }

  @Test
  void existsUsername() {
    when(checkUsernameExistenceUseCase.doesUsernameExist(USERNAME)).thenReturn(Boolean.TRUE);

    var response = cut.existsUsername(USERNAME);

    verify(checkUsernameExistenceUseCase).doesUsernameExist(USERNAME);

    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals(true, response.getBody());
  }
}
