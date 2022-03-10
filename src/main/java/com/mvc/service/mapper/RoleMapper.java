package com.mvc.service.mapper;

import com.mvc.dto.RoleDto;
import com.mvc.model.Role;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    RoleDto toRoleDto(final Role role);

}
