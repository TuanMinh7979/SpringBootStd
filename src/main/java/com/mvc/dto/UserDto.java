package com.mvc.dto;

import java.util.HashSet;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter

@NoArgsConstructor
@AllArgsConstructor

public class UserDto extends BaseDto {
    private String id;

    private String username;

    private String password;

    private Set<RoleDto> roles = new HashSet<>();
}