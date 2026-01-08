<a href="https://github.com/andres81/The-Self-Actualization-Project">The
Self-Actualization Project Documentation</a> © 2025
by <a href="https://www.andreschepers.nl">André Schepers</a> is licensed
under <a href="https://creativecommons.org/licenses/by/4.0/">CC BY
4.0</a><img src="https://mirrors.creativecommons.org/presskit/icons/cc.svg" alt="" style="max-width: 1em;max-height:1em;margin-left: .2em;"><img src="https://mirrors.creativecommons.org/presskit/icons/by.svg" alt="" style="max-width: 1em;max-height:1em;margin-left: .2em;">

# The Self-Actualization Project backend

## Appendix A: Local setup

### Docker

To run Postgres, use the follow command:

```Bash
docker run --name postgreslocal -d -p 5432:5432 -e POSTGRES_PASSWORD=postgrespw -e POSTGRES_USERNAME=postgres postgres:alpine

```

## Appendix B: Utils

To create a HMAC key, the following code can be used:

```Java 
import java.security.SecureRandom;
import java.util.Base64;

public final class HMACUtil {

  public static String hmac256KeyGenerator() {
    // Generate random 256-bit (32-byte) shared secret
    SecureRandom random = new SecureRandom();
    byte[] sharedSecret = new byte[32];
    random.nextBytes(sharedSecret);

    return Base64.getEncoder().encodeToString(sharedSecret);
  }

  public static void main(String[] args) {
    var result = hmac256KeyGenerator();
    System.out.println(result);
  }
}
```