package com.mvc.dto.request;


import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class CreateUserRequest {
    @NotNull(message = "username id is required")
    private String username;
    @NotNull(message = "password id is required")
    private String password;
}

