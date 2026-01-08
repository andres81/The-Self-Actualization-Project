package com.the_self_actualization_project.usermanagement.domain.exception;

import org.junit.jupiter.api.Test;

import static com.the_self_actualization_project.usermanagement.domain.exception.UserEntityErrorCode.INVALID_USER_EMAIL;
import static org.junit.jupiter.api.Assertions.*;

class UserEntityExceptionTest {

  @Test
  void testGetMessage() {
    var ex = new UserEntityException("Message");
    assertEquals("Message", ex.getMessage());

    ex = new UserEntityException(UserEntityErrorCode.INVALID_USER_SUBJECT);
    assertEquals("INVALID_USER_SUBJECT: Given value is not a valid subject.", ex.getMessage());

    ex = new UserEntityException("Error message", UserEntityErrorCode.INVALID_USER_SUBJECT);
    assertEquals("Error message, INVALID_USER_SUBJECT: Given value is not a valid subject.", ex.getMessage());
  }

  @Test
  void testGetErrorCode() {
    var ex = new UserEntityException(INVALID_USER_EMAIL);
    assertEquals(INVALID_USER_EMAIL, ex.getErrorCode());
  }
}
