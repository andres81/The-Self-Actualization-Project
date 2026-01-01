package com.the_self_actualization_project.usermanagement.application.port.out;

import com.the_self_actualization_project.usermanagement.domain.UserEntity;
import java.util.Optional;

public interface ExistsByUsernamePort {

  boolean existsByUsername(String username);
}
