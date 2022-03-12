package com.mvc.dto;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    private Integer balance;

//nếu đặt relationship ở đây thì trong mapper UserDtp.setRole(user.getRole)
    //đôi khi ta không muốn dùng role và đôi khi phải dùng

    private Set<RoleDto> roles = new HashSet<>();
}
