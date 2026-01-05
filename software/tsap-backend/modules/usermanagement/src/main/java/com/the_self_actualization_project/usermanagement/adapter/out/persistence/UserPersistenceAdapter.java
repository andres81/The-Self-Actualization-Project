package com.the_self_actualization_project.usermanagement.adapter.out.persistence;

import com.the_self_actualization_project.usermanagement.adapter.out.persistence.mapper.UserEntityMapper;
import com.the_self_actualization_project.usermanagement.application.port.out.ExistsByUsernamePort;
import com.the_self_actualization_project.usermanagement.application.port.out.FindBySubjectPort;
import com.the_self_actualization_project.usermanagement.application.port.out.FindByUserIdPort;
import com.the_self_actualization_project.usermanagement.application.port.out.PersistUserPort;
import com.the_self_actualization_project.usermanagement.domain.UserEntity;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class UserPersistenceAdapter implements FindBySubjectPort,
    FindByUserIdPort, ExistsByUsernamePort, PersistUserPort {

  private final UserRepository userRepository;

  @Override
  public Optional<UserEntity> findBySubject(String subject) {
    return userRepository.findBySubject(subject).map(
        UserEntityMapper.INSTANCE::mapFromJpaEntity);
  }

  @Override
  public Optional<UserEntity> findByUserId(UUID userId) {
    return userRepository.findByUserId(userId).map(
        UserEntityMapper.INSTANCE::mapFromJpaEntity);
  }

  @Override
  public boolean existsByUsername(String username) {
    return userRepository.existsByUsername(username);
  }

  @Override
  public void persistUser(UserEntity user) {
    var existingUser = userRepository.findByUserId(user.userId());
    UserJpaEntity jpaEntity;
    if (existingUser.isPresent()) {
      jpaEntity = existingUser.get();
      jpaEntity.setEmail(user.email());
      jpaEntity.setUsername(user.username());
      jpaEntity.setSubject(user.subject());
      jpaEntity.setProvider(user.provider());
    } else {
      jpaEntity = UserEntityMapper.INSTANCE.mapToJpaEntity(user);
    }
    userRepository.save(jpaEntity);
  }
}
