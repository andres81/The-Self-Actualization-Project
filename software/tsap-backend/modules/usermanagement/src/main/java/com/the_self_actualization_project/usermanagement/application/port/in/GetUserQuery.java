package com.the_self_actualization_project.usermanagement.application.port.in;

import com.the_self_actualization_project.usermanagement.domain.UserEntity;

public interface GetUserQuery {

  UserEntity getUserByUserId(String userId);
}
