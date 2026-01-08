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

package com.the_self_actualization_project.configuration;

import java.util.Base64;
import javax.crypto.spec.SecretKeySpec;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
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
        .addMapping("/api/**")
        .allowCredentials(true)
        .allowedOrigins("http://localhost:3000", "http://127.0.0.1:3000")
        .allowedHeaders("*")
        .allowedMethods("GET", "POST", "PUT", "DELETE", "HEAD", "OPTIONS");
  }

  @Bean
  public SecurityFilterChain filterChainAuthenticatedEndpoints(
      HttpSecurity http) throws Exception {
    http.csrf((csrf) -> csrf.ignoringRequestMatchers("/api/**"))
        .cors(Customizer.withDefaults())
        .sessionManagement(
            session -> session.sessionCreationPolicy(
                SessionCreationPolicy.STATELESS))
        .authorizeHttpRequests(
            auth -> {
              auth.requestMatchers("/api/auth/**")
                  .permitAll()
                  .requestMatchers("/api/**")
                  .authenticated()
                  .anyRequest().denyAll()
              ;
            })
        .oauth2ResourceServer(
            resourceServer ->
                resourceServer.jwt(
                    (jwt) ->
                        jwt.decoder(jwtDecoder())
                            .jwtAuthenticationConverter(
                                this.jwtAuthenticatorConverter())));
    return http.build();
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
                Base64.getDecoder()
                    .decode(authenticationConfigProperties.jwtSecret()), "MAC"))
        .macAlgorithm(MacAlgorithm.HS256)
        .build();
  }
}
