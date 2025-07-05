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

package nl.andreschepers.the_self_actualization_project.authentication.google;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken.Payload;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import nl.andreschepers.the_self_actualization_project.authentication.google.dto.GoogleIDTokenDto;
import nl.andreschepers.the_self_actualization_project.authentication.google.exception.EAuthenticationExceptionType;
import nl.andreschepers.the_self_actualization_project.authentication.google.exception.GoogleIdTokenVerifierServiceException;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class GoogleIdTokenVerifierService {

  private final GoogleIdTokenVerifier verifier;

  public GoogleIdTokenVerifierService() {
    this.verifier =
        new GoogleIdTokenVerifier.Builder(new NetHttpTransport(), new GsonFactory())
            // Specify the WEB_CLIENT_ID of the app that accesses the backend:
            .setAudience(Collections.singletonList("WEB_CLIENT_ID"))
            // Or, if multiple clients access the backend:
            // .setAudience(Arrays.asList(WEB_CLIENT_ID_1, WEB_CLIENT_ID_2, WEB_CLIENT_ID_3))
            .build();
  }

  public Optional<GoogleIDTokenDto> processGoogleIdToken(String idTokenString) {

    // (Receive idTokenString by HTTPS POST)

    GoogleIdToken idToken;
    try {
      idToken = verifier.verify(idTokenString);
    } catch (GeneralSecurityException | IOException e) {
      // Exceptions thrown when the Google lib tries to refresh public keys from the server.
      log.info("Could not verify Google Id token with Google: [{}].", e.getMessage(), e);
      throw new GoogleIdTokenVerifierServiceException(
          EAuthenticationExceptionType.GOOGLE_TOKEN_VERIFICATION_EXCEPTION,
          "Could not verify Google Id token with Google at this moment.");
    }
    Payload payload;
    if (idToken == null || idToken.getPayload() == null) {
      log.info("Could not verify Google Id token with Google, returned id token not processable.");
      return Optional.empty();
    }

    payload = idToken.getPayload();
    var googleIdTokenDto = new GoogleIDTokenDto(payload.getSubject());

    // Print user identifier
    String userId = payload.getSubject();
    System.out.println("User ID: " + userId);

    // Get profile information from payload
    String email = payload.getEmail();
    boolean emailVerified = Boolean.valueOf(payload.getEmailVerified());
    String name = (String) payload.get("name");
    String pictureUrl = (String) payload.get("picture");
    String locale = (String) payload.get("locale");
    String familyName = (String) payload.get("family_name");
    String givenName = (String) payload.get("given_name");

    // Use or store profile information
    // ...
    return Optional.of(googleIdTokenDto);
  }
}
