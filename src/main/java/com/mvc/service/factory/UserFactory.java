package com.mvc.service.factory;

import com.mvc.constant.UserStatus;
import com.mvc.dto.request.CreateUserRequest;
import com.mvc.model.User;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.UUID;

@Component
public class UserFactory {

    private static PasswordEncoder PECODER;

    public UserFactory(final PasswordEncoder passwordEncoder){
        PECODER= passwordEncoder;

    }
    public static User create(final CreateUserRequest userRequest) {

        return User.builder()
                .id(UUID.randomUUID().toString())
                .username(userRequest.getUsername())
                .password(PECODER.encode(userRequest.getPassword()))
                .balance(0)
                .status(UserStatus.ENABLE)
                .roles(new HashSet<>())
                .build();

    }
}
