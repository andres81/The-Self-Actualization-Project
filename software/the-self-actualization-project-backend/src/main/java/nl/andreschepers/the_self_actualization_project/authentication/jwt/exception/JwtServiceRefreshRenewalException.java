package nl.andreschepers.the_self_actualization_project.authentication.jwt.exception;

public class JwtServiceRefreshRenewalException extends RuntimeException {

  public JwtServiceRefreshRenewalException(String message) {
    super(message);
  }

  public JwtServiceRefreshRenewalException(String message,  Throwable cause) {
    super(message, cause);
  }
}
