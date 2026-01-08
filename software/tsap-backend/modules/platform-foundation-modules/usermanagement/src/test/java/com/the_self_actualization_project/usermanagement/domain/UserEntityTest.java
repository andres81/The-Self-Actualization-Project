package com.the_self_actualization_project.usermanagement.domain;

import static org.junit.jupiter.api.Assertions.*;

import com.the_self_actualization_project.usermanagement.domain.exception.UserEntityErrorCode;
import com.the_self_actualization_project.usermanagement.domain.exception.UserEntityException;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.Test;

class UserEntityTest {

  private static final UUID USER_ID = UUID.randomUUID();
  private static final String USERNAME = "USERNAME";
  private static final String SUBJECT = "SUBJECT";
  private static final OAuth2Provider PROVIDER = OAuth2Provider.FACEBOOK;
  private static final String EMAIL = "someemail@gmail.com";

  private static final List<String> INVALID_EMAILS =
      Arrays.asList("someemail_thatisinvalid.com", "", null);
  private static final List<String> INVALID_USERNAMES =
      Arrays.asList(9 + USERNAME, '_' + USERNAME, USERNAME + '%', null);
  private static final List<String> INVALID_SUBJECTs =
      Arrays.asList("123456789012345678901234567890123456789012345678901", "", null);

  @Test
  void testMandatoryFields() {
    var userEntity = new UserEntity(USER_ID, USERNAME, SUBJECT, PROVIDER, EMAIL);
    assertEquals(USER_ID, userEntity.userId());
    assertEquals(USERNAME, userEntity.username());
    assertEquals(SUBJECT, userEntity.subject());
    assertEquals(PROVIDER, userEntity.provider());
    assertEquals(EMAIL, userEntity.email());
  }

  @Test
  void testInvalidUsernameThrowsException() {
    INVALID_USERNAMES.forEach(
        (value) -> {
          var ex =
              assertThrows(
                  UserEntityException.class,
                  () -> new UserEntity(USER_ID, value, SUBJECT, PROVIDER, EMAIL));
          assertEquals(UserEntityErrorCode.INVALID_USER_USERNAME, ex.getErrorCode());
        });
  }

  @Test
  void testInvalidEmailThrowsException() {
    INVALID_EMAILS.forEach(
        (value) -> {
          var ex =
              assertThrows(
                  UserEntityException.class,
                  () -> new UserEntity(USER_ID, USERNAME, SUBJECT, PROVIDER, value));
          assertEquals(UserEntityErrorCode.INVALID_USER_EMAIL, ex.getErrorCode());
        });
  }

  @Test
  void testInvalidSubjectThrowsException() {
    INVALID_SUBJECTs.forEach(
        (value) -> {
          var ex =
              assertThrows(
                  UserEntityException.class,
                  () -> new UserEntity(USER_ID, USERNAME, value, PROVIDER, EMAIL));
          assertEquals(UserEntityErrorCode.INVALID_USER_SUBJECT, ex.getErrorCode());
        });
  }

  @Test
  void testInvalidUserIdThrowsException() {
    var ex =
        assertThrows(
            UserEntityException.class,
            () -> new UserEntity(null, USERNAME, SUBJECT, PROVIDER, EMAIL));
    assertEquals(UserEntityErrorCode.INVALID_USER_USER_ID, ex.getErrorCode());
  }

  @Test
  void testInvalidProviderThrowsException() {
    var ex =
        assertThrows(
            UserEntityException.class,
            () -> new UserEntity(USER_ID, USERNAME, SUBJECT, null, EMAIL));
    assertEquals(UserEntityErrorCode.INVALID_USER_PROVIDER, ex.getErrorCode());
  }

  @Test
  void testUpdateUsername() {
    var userEntity = new UserEntity(USER_ID, USERNAME, SUBJECT, PROVIDER, EMAIL);
    var updatedUsernameEntity = userEntity.updateUsername("NEW_USER_NAME");
    assertEquals(USER_ID, updatedUsernameEntity.userId());
    assertEquals("NEW_USER_NAME", updatedUsernameEntity.username());
    assertEquals(SUBJECT, userEntity.subject());
    assertEquals(PROVIDER, updatedUsernameEntity.provider());
    assertEquals(EMAIL, updatedUsernameEntity.email());
  }
}
