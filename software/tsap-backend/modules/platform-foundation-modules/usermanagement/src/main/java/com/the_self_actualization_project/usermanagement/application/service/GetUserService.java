package com.the_self_actualization_project.usermanagement.application.service;

import com.the_self_actualization_project.usermanagement.application.port.in.GetUserQuery;
import com.the_self_actualization_project.usermanagement.application.port.out.FindByUserIdPort;
import com.the_self_actualization_project.usermanagement.domain.UserEntity;
import com.the_self_actualization_project.usermanagement.domain.exception.UserEntityException;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class GetUserService implements GetUserQuery {

  private final FindByUserIdPort findByUserIdPort;

  @Override
  public UserEntity getUserByUserId(UUID userId) {
    return findByUserIdPort
        .findByUserId(userId)
        .orElseThrow(() -> new UserEntityException("Could not find user"));
  }
}
