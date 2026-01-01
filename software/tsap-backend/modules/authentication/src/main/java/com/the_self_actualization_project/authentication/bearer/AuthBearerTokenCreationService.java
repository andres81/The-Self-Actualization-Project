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

package com.the_self_actualization_project.authentication.bearer;

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
import java.util.Base64;
import java.util.Date;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import com.the_self_actualization_project.authentication.bearer.dto.BearerAccessRefreshTokenPairDto;
import com.the_self_actualization_project.authentication.bearer.exception.BearerVerifyJwtException;
import com.the_self_actualization_project.configuration.AuthenticationConfigProperties;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthBearerTokenCreationService {

  public static final String ACCESS_TOKEN_TYPE_VALUE = "ACCESS_TOKEN";
  public static final String REFRESH_TOKEN_TYPE_VALUE = "REFRESH_TOKEN";
  public static final String TOKEN_TYPE_CLAIM_NAME = "TYPE";
  public static final String REFRESH_TOKEN_ID_CLAIM_NAME = "REFRESH_TOKEN_ID";

  private static final int ACCESS_TOKEN_EXPIRATION_IN_MILLI_SECONDS = 10 * 1000;
  private static final int REFRESH_TOKEN_EXPIRATION_IN_MILLI_SECONDS =
      24 * 60 * 60 * 1000;

  private static final String BEARER_JWT_VERIFICATION_NOT_VALID_ERROR_MESSAGE = "JWT not valid";

  private final AuthenticationConfigProperties authenticationConfigProperties;

  private JWSSigner jwsSigner;

  @PostConstruct
  public void init() throws KeyLengthException {
    // Create HMAC signer
    jwsSigner =
        new MACSigner(Base64.getDecoder()
            .decode(authenticationConfigProperties.jwtSecret()));
  }

  public BearerAccessRefreshTokenPairDto createAccessAndRefreshToken(
      String subject, UUID refreshTokenRegistrationId) {

    var claimSetBuilder = createClaimSet(subject);
    var accessToken =
        createToken(
            claimSetBuilder,
            expirationTime(ACCESS_TOKEN_EXPIRATION_IN_MILLI_SECONDS),
            ACCESS_TOKEN_TYPE_VALUE);
    var refreshToken =
        createToken(
            claimSetBuilder,
            expirationTime(REFRESH_TOKEN_EXPIRATION_IN_MILLI_SECONDS),
            REFRESH_TOKEN_TYPE_VALUE,
            refreshTokenRegistrationId);

    return new BearerAccessRefreshTokenPairDto(accessToken, refreshToken);
  }

  private Date expirationTime(int offsetMS) {
    return new Date(new Date().getTime() + offsetMS);
  }

  private String createToken(JWTClaimsSet claimSet, Date expirationTime,
      String tokenType) {
    return createToken(claimSet, expirationTime, tokenType, null);
  }

  private String createToken(
      JWTClaimsSet claimSet,
      Date expirationTime,
      String tokenType,
      UUID refreshTokenRegistrationId) {
    var builder = new JWTClaimsSet.Builder(claimSet);
    if (refreshTokenRegistrationId != null) {
      builder.claim(REFRESH_TOKEN_ID_CLAIM_NAME, refreshTokenRegistrationId);
    }
    var jwtClaimsSet =
        builder.claim(TOKEN_TYPE_CLAIM_NAME, tokenType)
            .expirationTime(expirationTime).build();
    return createJwtWithClaimSet(jwtClaimsSet);
  }

  private JWTClaimsSet createClaimSet(String subject) {
    return new JWTClaimsSet.Builder()
        //        .issuer("https://www.the-self-actualization-project.com")
        .issuer(authenticationConfigProperties.jwtIssuerClaimValue())
        .subject(subject)
        .audience(authenticationConfigProperties.jwtAudienceClaimValue())
        //        .audience("the-self-actualization-project-fe")
        .issueTime(new Date())
        .build();
  }

  public SignedJWT verifyHmacSignedTokenJwt(String token) {
    try {
      var signedJWT = SignedJWT.parse(token);
      JWSVerifier verifier =
          new MACVerifier(Base64.getDecoder()
              .decode(authenticationConfigProperties.jwtSecret()));
      if (!signedJWT.verify(verifier)) {
        throw new BearerVerifyJwtException(
            BEARER_JWT_VERIFICATION_NOT_VALID_ERROR_MESSAGE);
      }
      return signedJWT;
    } catch (ParseException | JOSEException e) {
      log.error("Failed to parse refresh token JWT for verification: [{}]",
          e.getMessage(), e);
      throw new BearerVerifyJwtException(
          BEARER_JWT_VERIFICATION_NOT_VALID_ERROR_MESSAGE, e.getCause());
    }
  }

  private String createJwtWithClaimSet(JWTClaimsSet claimsSet) {
    SignedJWT signedJWT = new SignedJWT(new JWSHeader(JWSAlgorithm.HS256),
        claimsSet);

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
