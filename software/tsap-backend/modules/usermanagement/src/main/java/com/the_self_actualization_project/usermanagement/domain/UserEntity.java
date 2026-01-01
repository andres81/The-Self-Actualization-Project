package com.the_self_actualization_project.usermanagement.domain;

import java.util.UUID;

public record UserEntity(
    UUID userId,
    String username,
    String subject,
    OAuth2Provider provider,
    String email) {

  public UserEntity updateUsername(String newUsername) {

    return new UserEntity(this.userId, newUsername, this.subject, this.provider,
        this.email);
  }
}