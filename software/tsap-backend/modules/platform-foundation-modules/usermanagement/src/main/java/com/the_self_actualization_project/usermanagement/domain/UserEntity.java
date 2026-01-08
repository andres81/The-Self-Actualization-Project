package com.the_self_actualization_project.usermanagement.domain;

import com.the_self_actualization_project.usermanagement.domain.exception.UserEntityErrorCode;
import com.the_self_actualization_project.usermanagement.domain.exception.UserEntityException;
import java.util.UUID;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.validator.routines.EmailValidator;

public record UserEntity(
    UUID userId, String username, String subject, OAuth2Provider provider, String email) {

  private static final String VALID_USERNAME_REGEX = "^[A-Za-z][\\-_A-Za-z0-9]{2,19}$";
  private static final int MAX_LENGTH_SUBJECT = 50;

  public UserEntity {

    if (userId == null) {
      throw new UserEntityException(UserEntityErrorCode.INVALID_USER_USER_ID);
    }

    if (StringUtils.isBlank(username) || !username.matches(VALID_USERNAME_REGEX)) {
      throw new UserEntityException(UserEntityErrorCode.INVALID_USER_USERNAME);
    }

    if (StringUtils.isBlank(subject) || subject.length() > MAX_LENGTH_SUBJECT) {
      throw new UserEntityException(UserEntityErrorCode.INVALID_USER_SUBJECT);
    }

    if (provider == null) {
      throw new UserEntityException(UserEntityErrorCode.INVALID_USER_PROVIDER);
    }

    if (!EmailValidator.getInstance().isValid(email)) {
      throw new UserEntityException(UserEntityErrorCode.INVALID_USER_EMAIL);
    }
  }

  public UserEntity updateUsername(String newUsername) {

    return new UserEntity(this.userId, newUsername, this.subject, this.provider, this.email);
  }
}
