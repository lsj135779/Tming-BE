package com.spring.tming.domain.user.service;

import com.spring.tming.domain.user.dto.response.UserGetRes;
import com.spring.tming.domain.user.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserServiceMapper {

    UserServiceMapper INSTANCE = Mappers.getMapper(UserServiceMapper.class);

    // TODO add follower, following cnt
    @Mapping(source = "job.description", target = "job")
    UserGetRes toUserGetRes(User user);
}
