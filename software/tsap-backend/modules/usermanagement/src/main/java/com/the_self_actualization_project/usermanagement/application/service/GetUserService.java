package com.the_self_actualization_project.usermanagement.application.service;

import com.the_self_actualization_project.usermanagement.adapter.out.persistence.UserPersistenceAdapter;
import com.the_self_actualization_project.usermanagement.application.port.in.GetUserQuery;
import com.the_self_actualization_project.usermanagement.application.port.out.FindByUserIdPort;
import com.the_self_actualization_project.usermanagement.domain.UserEntity;
import com.the_self_actualization_project.usermanagement.exception.UserException;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetUserService implements GetUserQuery {

  private final FindByUserIdPort findByUserIdPort;

  public UserEntity getUserByUserId(String userId) {
    if (StringUtils.isBlank(userId)) {
      throw new UserException("user id is empty");
    }
    var userIdUUID = UUID.fromString(userId);
    return findByUserIdPort.findByUserId(userIdUUID)
        .orElseThrow(() -> new UserException("Could not find user"));
  }
}
