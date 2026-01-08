package com.the_self_actualization_project.usermanagement.domain.exception;

import lombok.Getter;

@Getter
public enum UserEntityErrorCode {
  INVALID_USER_USER_ID("Given value is not a valid userId."),
  INVALID_USER_SUBJECT("Given value is not a valid subject."),
  INVALID_USER_PROVIDER("Given value is not a valid provider."),
  INVALID_USER_EMAIL("Given value is not a valid email address."),
  INVALID_USER_USERNAME(
      "Given value is not a valid username: Start with a letter, max length 20 characters, only alfanumeric and underscore characters allowed.");
  ;

  private final String errorMessage;

  UserEntityErrorCode(String errorMessage) {
    this.errorMessage = errorMessage;
  }
}
