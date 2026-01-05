package com.the_self_actualization_project.usermanagement.application.port.out;

import com.the_self_actualization_project.usermanagement.domain.UserEntity;
import java.util.Optional;
import java.util.UUID;

public interface PersistUserPort {

  void persistUser(UserEntity user);
}
