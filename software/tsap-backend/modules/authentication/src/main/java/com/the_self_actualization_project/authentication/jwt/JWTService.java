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

package com.the_self_actualization_project.authentication.jwt;

import com.nimbusds.jwt.JWTClaimNames;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import java.text.ParseException;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import com.the_self_actualization_project.authentication.bearer.AuthBearerTokenCreationService;
import com.the_self_actualization_project.authentication.bearer.dto.BearerAccessRefreshTokenPairDto;
import com.the_self_actualization_project.authentication.jwt.data.JwtRefreshTokenEntity;
import com.the_self_actualization_project.authentication.jwt.data.JwtRefreshTokenEntity.Status;
import com.the_self_actualization_project.authentication.jwt.data.JwtRefreshTokenRepository;
import com.the_self_actualization_project.authentication.jwt.exception.JwtServiceRefreshRenewalException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class JWTService {

  private final JwtRefreshTokenRepository jwtRefreshTokenRepository;
  private final AuthBearerTokenCreationService authBearerTokenCreationService;

  @Transactional
  public UUID createRefreshTokenRegistration(UUID userId) {
    jwtRefreshTokenRepository
        .findByUserId(userId)
        .forEach(jwtRefreshTokenEntity -> jwtRefreshTokenEntity.setStatus(
            Status.INACTIVE));
    return jwtRefreshTokenRepository.save(new JwtRefreshTokenEntity(userId))
        .getId();
  }

  @Transactional
  public BearerAccessRefreshTokenPairDto processRefreshTokenRenewal(
      String refreshToken) {

    var verifiedJwt = authBearerTokenCreationService.verifyHmacSignedTokenJwt(
        refreshToken);

    var jwtClaimsSet = retrieveJwtClaimSet(verifiedJwt);

    var refreshTokenEntity = retrieveRefreshToken(jwtClaimsSet);

    refreshTokenEntity.setStatus(Status.INACTIVE);

    var newRefreshTokenId = createRefreshTokenRegistration(
        refreshTokenEntity.getUserId());

    return authBearerTokenCreationService.createAccessAndRefreshToken(
        refreshTokenEntity.getUserId().toString(), newRefreshTokenId);
  }

  private UUID obtainUserId(JWTClaimsSet jwtClaimSet) {
    var subject = getClaimFromJwt(jwtClaimSet, JWTClaimNames.SUBJECT);
    return UUID.fromString((String) subject);
  }

  private JWTClaimsSet retrieveJwtClaimSet(SignedJWT verifiedJwt) {
    try {
      return verifiedJwt.getJWTClaimsSet();
    } catch (ParseException e) {
      throw new JwtServiceRefreshRenewalException(
          "Could not obtain JWTClaimsSet from JWT.");
    }
  }

  private UUID obtainRefreshTokenId(JWTClaimsSet jwtClaimSet) {
    var refreshTokenId =
        getClaimFromJwt(jwtClaimSet,
            AuthBearerTokenCreationService.REFRESH_TOKEN_ID_CLAIM_NAME);
    return UUID.fromString((String) refreshTokenId);
  }

  private JwtRefreshTokenEntity retrieveRefreshToken(
      JWTClaimsSet jwtClaimsSet) {
    var userId = obtainUserId(jwtClaimsSet);
    var refreshTokenId = obtainRefreshTokenId(jwtClaimsSet);
    return jwtRefreshTokenRepository
        .findById(refreshTokenId)
        .orElseThrow(
            () -> {
              log.error("SEVERE: Unknown refresh token used to authenticate.");
              jwtRefreshTokenRepository.deleteAllByUserId(userId);
              return new JwtServiceRefreshRenewalException(
                  "Refresh token not present in database, unauthorized.");
            });
  }

  private Object getClaimFromJwt(JWTClaimsSet jwtClaimsSet, String claimName) {
    return Optional.ofNullable(jwtClaimsSet.getClaim(claimName))
        .orElseThrow(
            () -> {
              log.error("Could not obtain claim from jwt.");
              return new JwtServiceRefreshRenewalException(
                  "Could not obtain claim from jwt.");
            });
  }
}
