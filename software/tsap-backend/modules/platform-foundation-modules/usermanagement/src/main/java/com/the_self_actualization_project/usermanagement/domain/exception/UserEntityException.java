package com.the_self_actualization_project.usermanagement.domain.exception;

import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

@Getter
public class UserEntityException extends RuntimeException {

  private final UserEntityErrorCode errorCode;

  public UserEntityException(String message) {
    super(message);
    this.errorCode = null;
  }

  public UserEntityException(UserEntityErrorCode errorCode) {
    this.errorCode = errorCode;
  }

  public UserEntityException(String message, UserEntityErrorCode errorCode) {
    super(message);
    this.errorCode = errorCode;
  }

  @Override
  public String getMessage() {
    if (this.errorCode == null) {
      return super.getMessage();
    } else {
      var exceptionMessage = errorCode.name() + ": " + errorCode.getErrorMessage();
      if (StringUtils.isBlank(super.getMessage())) {
        return exceptionMessage;
      } else {
        return super.getMessage() + ", " + exceptionMessage;
      }
    }
  }
}
