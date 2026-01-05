package com.the_self_actualization_project.usermanagement.application.port.out;

import com.the_self_actualization_project.usermanagement.domain.UserEntity;
import java.util.Optional;

public interface FindBySubjectPort {

  Optional<UserEntity> findBySubject(String subject);
}
