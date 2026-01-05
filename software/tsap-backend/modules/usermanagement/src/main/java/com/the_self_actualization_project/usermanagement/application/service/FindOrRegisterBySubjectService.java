package com.the_self_actualization_project.usermanagement.application.service;

import com.fasterxml.uuid.Generators;
import com.the_self_actualization_project.usermanagement.adapter.out.persistence.UserJpaEntity;
import com.the_self_actualization_project.usermanagement.application.port.in.FindOrRegisterBySubjectCommand;
import com.the_self_actualization_project.usermanagement.application.port.in.FindOrRegisterBySubjectUseCase;
import com.the_self_actualization_project.usermanagement.application.port.out.FindBySubjectPort;
import com.the_self_actualization_project.usermanagement.application.port.out.PersistUserPort;
import com.the_self_actualization_project.usermanagement.domain.UserEntity;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FindOrRegisterBySubjectService implements
    FindOrRegisterBySubjectUseCase {

  private final FindBySubjectPort findBySubjectPort;
  private final PersistUserPort persistUserPort;

  @Override
  public UserEntity findOrRegisterBySubject(
      FindOrRegisterBySubjectCommand findOrRegisterBySubjectCommand) {
    return findBySubjectPort.findBySubject(
            findOrRegisterBySubjectCommand.subject())
        .orElseGet(() -> createUserEntity(findOrRegisterBySubjectCommand));
  }

  private UserEntity createUserEntity(
      FindOrRegisterBySubjectCommand command) {

    var userEntity = new UserEntity(
        Generators.timeBasedEpochGenerator().generate(),
        Generators.timeBasedEpochGenerator().generate().toString(),
        command.subject(),
        command.provider(),
        command.email());

    persistUserPort.persistUser(userEntity);
    return userEntity;
  }
}
