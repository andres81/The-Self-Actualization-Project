package nl.andreschepers.the_self_actualization_project.authentication.bearer.exception;

public class BearerVerifyJwtException extends RuntimeException{
  public BearerVerifyJwtException(String message) {
      super(message);
    }

  public BearerVerifyJwtException(String message, Throwable cause) {
    super(message, cause);
  }
}
