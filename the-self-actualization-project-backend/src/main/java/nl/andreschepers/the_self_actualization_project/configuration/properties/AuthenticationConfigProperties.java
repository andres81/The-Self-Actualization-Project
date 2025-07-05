package nl.andreschepers.the_self_actualization_project.configuration.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "app.auth")
public record AuthenticationConfigProperties(String jwtSecret, String refreshTokenCookieDomain) {}
