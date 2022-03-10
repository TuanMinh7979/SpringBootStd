package com.mvc.dto;

import java.util.HashSet;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RoleDto extends BaseDto {
    private Integer id;

    private String description;

    private Set<PermissionDto> permissions = new HashSet<>();

    // private Set<UserDto> users = new HashSet<>();

}
