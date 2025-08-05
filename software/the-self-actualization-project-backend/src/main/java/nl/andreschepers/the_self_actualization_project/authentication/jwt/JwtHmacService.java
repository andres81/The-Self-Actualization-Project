package nl.andreschepers.the_self_actualization_project.authentication.jwt;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSSigner;
import com.nimbusds.jose.JWSVerifier;
import com.nimbusds.jose.KeyLengthException;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import jakarta.annotation.PostConstruct;
import java.text.ParseException;
import java.util.Date;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import nl.andreschepers.the_self_actualization_project.configuration.properties.AuthenticationConfigProperties;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class JwtHmacService {

  private final AuthenticationConfigProperties authenticationConfigProperties;
  private JWSSigner jwsSigner;

  @PostConstruct
  public void init() throws KeyLengthException {
    // Create HMAC signer
    jwsSigner = new MACSigner(authenticationConfigProperties.jwtSecret());
  }

  public String createHmacSignedAccessTokenJwt(String subject) {
    // Prepare JWT with claims set
    JWTClaimsSet claimsSet =
        new JWTClaimsSet.Builder()
            .subject(subject)
            .audience("the-self-actualization-project-fe")
            .issueTime(new Date())
            .issuer("https://www.the-self-actualization-project.com")
            .expirationTime(
                new Date(
                    new Date().getTime()
                        + 30
                            * 1000)) // @TODO: Make variable. For testing purposes very short, later
            // will be 10 minutes
            .build();

    return createJwtWithClaimSet(claimsSet);
  }

  public String createHmacSignedRefreshTokenJwtWithSubject(String subject) {
    JWTClaimsSet claimsSet =
        new JWTClaimsSet.Builder()
            .subject(subject)
            .expirationTime(new Date(new Date().getTime() + 24 * 60 * 60 * 1000))
            .build();

    return createJwtWithClaimSet(claimsSet);
  }

  public Optional<SignedJWT> verifyHmacSignedRefreshTokenJwt(String refreshToken) {
    try {
      var signedJWT = SignedJWT.parse(refreshToken);
      JWSVerifier verifier = new MACVerifier(authenticationConfigProperties.jwtSecret());
      if (!signedJWT.verify(verifier)) {
        return Optional.empty();
      }
      return Optional.of(signedJWT);
    } catch (ParseException | JOSEException e) {
      return Optional.empty();
    }
  }

  private String createJwtWithClaimSet(JWTClaimsSet claimsSet) {
    SignedJWT signedJWT = new SignedJWT(new JWSHeader(JWSAlgorithm.HS512), claimsSet);

    // Apply the HMAC protection
    try {
      signedJWT.sign(jwsSigner);
    } catch (JOSEException e) {
      throw new RuntimeException(e);
    }

    // Serialize to compact form, produces something like
    return signedJWT.serialize();
  }
}
