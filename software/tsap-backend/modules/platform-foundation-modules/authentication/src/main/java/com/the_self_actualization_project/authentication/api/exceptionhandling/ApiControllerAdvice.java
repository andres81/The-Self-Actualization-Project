/*
 * Copyright 2025 André Schepers
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.the_self_actualization_project.authentication.api.exceptionhandling;

import com.the_self_actualization_project.authentication.api.dto.AuthenticationErrorDto;
import com.the_self_actualization_project.authentication.api.exception.ForbiddenException;
import com.the_self_actualization_project.authentication.api.exception.UnAuthorizedException;
import com.the_self_actualization_project.authentication.google.exception.GoogleTokenServiceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ApiControllerAdvice {

  @ExceptionHandler(UnAuthorizedException.class)
  public ResponseEntity<Void> handleUnAuthorizedException() {
    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
  }

  @ExceptionHandler(ForbiddenException.class)
  public ResponseEntity<Void> handleForbiddenException() {
    return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
  }

  @ExceptionHandler(GoogleTokenServiceException.class)
  public ResponseEntity<AuthenticationErrorDto> handleAuthenticationErrors(
      GoogleTokenServiceException ex) {
    return switch (ex.getExceptionType()) {
      case GOOGLE_TOKEN_VERIFICATION_EXCEPTION ->
          ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
              .body(new AuthenticationErrorDto(ex.getMessage()));
    };
  }
}
