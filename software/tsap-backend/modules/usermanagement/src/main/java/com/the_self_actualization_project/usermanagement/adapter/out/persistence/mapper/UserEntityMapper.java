package com.the_self_actualization_project.usermanagement.adapter.out.persistence.mapper;

import com.the_self_actualization_project.usermanagement.adapter.out.persistence.UserJpaEntity;
import com.the_self_actualization_project.usermanagement.domain.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserEntityMapper {

  UserEntityMapper INSTANCE = Mappers.getMapper( UserEntityMapper.class );

  UserJpaEntity mapToJpaEntity(UserEntity userEntity);

  UserEntity mapFromJpaEntity(UserJpaEntity userJpaEntity);
}
