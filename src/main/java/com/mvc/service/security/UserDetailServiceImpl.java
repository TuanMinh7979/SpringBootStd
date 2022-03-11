package com.mvc.service.security;

import com.mvc.dto.UserDto;

import com.mvc.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserDetailServiceImpl implements UserDetailsService {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final UserDto user = userService.getUser(username);
        Set<SimpleGrantedAuthority> setRole = user.getRoles()
                .stream()
                .map((r) -> r.getDescription())
                .map(rdes -> new SimpleGrantedAuthority(rdes))
                .collect(Collectors.toSet());

        return new User(user.getUsername(), passwordEncoder.encode(user.getPassword()), setRole);


    }

}
