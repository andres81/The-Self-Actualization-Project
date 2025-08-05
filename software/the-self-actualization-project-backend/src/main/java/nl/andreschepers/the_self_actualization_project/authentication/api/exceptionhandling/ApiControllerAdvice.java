package nl.andreschepers.the_self_actualization_project.authentication.api.exceptionhandling;

import nl.andreschepers.the_self_actualization_project.authentication.api.exception.ForbiddenException;
import nl.andreschepers.the_self_actualization_project.authentication.api.exception.UnAuthorizedException;
import nl.andreschepers.the_self_actualization_project.authentication.google.exception.GoogleIdTokenVerifierServiceException;
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

  @ExceptionHandler(GoogleIdTokenVerifierServiceException.class)
  public ResponseEntity<AuthenticationErrorDto> handleAuthenticationErrors(
      GoogleIdTokenVerifierServiceException ex) {
    return switch (ex.getExceptionType()) {
      case GOOGLE_TOKEN_VERIFICATION_EXCEPTION ->
          ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
              .body(new AuthenticationErrorDto(ex.getMessage()));
    };
  }
}
