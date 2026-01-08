package com.the_self_actualization_project.usermanagement.adapter.in.web.mapper;

import com.the_self_actualization_project.usermanagement.adapter.in.web.response.UserInfoResponse;
import com.the_self_actualization_project.usermanagement.domain.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserInfoResponseMapper {

  UserInfoResponseMapper INSTANCE = Mappers.getMapper(UserInfoResponseMapper.class);

  UserInfoResponse toResponse(UserEntity userEntity);
}
