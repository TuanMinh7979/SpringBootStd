package com.mvc.service.mapper;

import com.mvc.dto.PermissionDto;
import com.mvc.model.Permission;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PermissionMapper {
    PermissionDto toPermissionDTo(final Permission permission);
}
