package com.the_self_actualization_project.usermanagement.application.service;

import com.fasterxml.uuid.Generators;
import com.the_self_actualization_project.usermanagement.application.port.in.FindOrRegisterBySubjectCommand;
import com.the_self_actualization_project.usermanagement.application.port.in.FindOrRegisterBySubjectUseCase;
import com.the_self_actualization_project.usermanagement.application.port.out.FindBySubjectPort;
import com.the_self_actualization_project.usermanagement.application.port.out.PersistUserPort;
import com.the_self_actualization_project.usermanagement.domain.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class FindOrRegisterBySubjectService implements FindOrRegisterBySubjectUseCase {

  private final FindBySubjectPort findBySubjectPort;
  private final PersistUserPort persistUserPort;

  @Override
  public UserEntity findOrRegisterBySubject(
      FindOrRegisterBySubjectCommand findOrRegisterBySubjectCommand) {
    return findBySubjectPort
        .findBySubject(findOrRegisterBySubjectCommand.subject())
        .orElseGet(() -> createUserEntity(findOrRegisterBySubjectCommand));
  }

  private UserEntity createUserEntity(FindOrRegisterBySubjectCommand command) {

    var userEntity =
        new UserEntity(
            Generators.timeBasedEpochGenerator().generate(),
            "DEFAULT-" + Generators.timeBasedEpochGenerator().generate().toString().substring(24),
            command.subject(),
            command.provider(),
            command.email());

    persistUserPort.persistUser(userEntity);
    return userEntity;
  }
}
