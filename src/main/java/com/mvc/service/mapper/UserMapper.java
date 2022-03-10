package com.mvc.service.mapper;

import com.mvc.dto.UserDto;
import com.mvc.dto.response.UserResponse;
import com.mvc.model.User;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDto toUserDto(final User user);

    UserResponse toUserResponse(User user);
}
