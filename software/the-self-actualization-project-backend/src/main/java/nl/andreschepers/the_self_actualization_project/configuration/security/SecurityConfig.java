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

package nl.andreschepers.the_self_actualization_project.configuration.security;

import com.nimbusds.jose.util.StandardCharset;
import javax.crypto.spec.SecretKeySpec;
import lombok.RequiredArgsConstructor;
import nl.andreschepers.the_self_actualization_project.configuration.properties.AuthenticationConfigProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer;
import org.springframework.security.config.annotation.web.configurers.SessionManagementConfigurer;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableMethodSecurity(jsr250Enabled = true)
@RequiredArgsConstructor
public class SecurityConfig implements WebMvcConfigurer {

  private final AuthenticationConfigProperties authenticationConfigProperties;

  @Override
  public void addCorsMappings(CorsRegistry registry) {
    registry
        .addMapping("/**")
        .allowedOrigins("http://localhost:3000")
        .allowedMethods("GET", "POST", "PUT", "DELETE", "HEAD");
  }

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    // @formatter:off
    http.csrf((csrf) -> csrf.ignoringRequestMatchers("/api/**"))
        .sessionManagement(this::sessionManagementConfiguration)
        .authorizeHttpRequests(this::authorizeHttpRequestsConfiguration)
        .oauth2ResourceServer(this::oAuth2ResourceServerConfiguration);
    // @formatter:on
    return http.build();
  }

  private void sessionManagementConfiguration(SessionManagementConfigurer<HttpSecurity> session) {
    session.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
  }

  private void authorizeHttpRequestsConfiguration(
      AuthorizeHttpRequestsConfigurer<HttpSecurity>.AuthorizationManagerRequestMatcherRegistry
          auth) {
    auth.requestMatchers("/api/auth/**").permitAll();
  }

  private void oAuth2ResourceServerConfiguration(
      OAuth2ResourceServerConfigurer<HttpSecurity> oauth2ResourceServer) {
    oauth2ResourceServer.jwt(
        (jwt) ->
            jwt.decoder(jwtDecoder()).jwtAuthenticationConverter(this.jwtAuthenticatorConverter()));
  }

  private JwtAuthenticationConverter jwtAuthenticatorConverter() {
    var converter = new JwtAuthenticationConverter();
    var jwtGrantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();
    jwtGrantedAuthoritiesConverter.setAuthorityPrefix("ROLE_");
    converter.setJwtGrantedAuthoritiesConverter(jwtGrantedAuthoritiesConverter);
    return converter;
  }

  private JwtDecoder jwtDecoder() {
    return NimbusJwtDecoder.withSecretKey(
            new SecretKeySpec(
                authenticationConfigProperties.jwtSecret().getBytes(StandardCharset.UTF_8), "MAC"))
        .macAlgorithm(MacAlgorithm.HS512)
        .build();
  }
}
