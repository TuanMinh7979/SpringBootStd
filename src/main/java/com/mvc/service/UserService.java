package com.mvc.service;

import com.mvc.dto.UserDto;
import com.mvc.dto.request.CreateUserRequest;
import com.mvc.dto.response.UserResponse;
import com.mvc.exception.BadRequesetException;
import com.mvc.exception.ResourceNotFoundException;
import com.mvc.model.Permission;
import com.mvc.model.Role;
import com.mvc.model.User;
import com.mvc.repo.UserRepo;
import com.mvc.service.factory.UserFactory;
import com.mvc.service.mapper.UserMapper;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.transaction.Transactional;
import java.util.Set;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserMapper userMapper;
    private final UserRepo userRepo;
    public UserDto getUserById(String userId){
        return userRepo.findById(userId)
                .map(userMapper::toUserDto)
                .orElseGet(()->{

                    log.warn("User with id {} not found", userId);
                    throw new ResourceNotFoundException("Khong tim thay user");
                });
    }


    public UserDto getUser(String username) {
        System.out.println("QUERY AUTHEN");
        return userRepo.findByUsernameWithPermission(username)
                .map(usermodel -> userMapper.toUserDto(usermodel))
                .orElseGet(() -> {
                    log.warn("User not found" + username);
                    throw new ResourceNotFoundException("Khong tim thay nguoi dung");
                });
    }

    //test
    public UserDto findByUsernameWithPermission(String username) {
        User user = userRepo.findByUsernameWithPermission(username).get();
        System.out.println(user.getRoles());
        Set<Role> roles = user.getRoles();
        for (Role role : roles) {
            System.out.println("-" + role.getDescription() + " " + role.getId());
            for (Permission per : role.getPermissions()) {
                System.out.println("---" + per.getDescription());
            }
        }

        //test

        // n???u tr??? v??? m???t DTO TH?? S??? ??C DO B???N TH??N TRUY V???N C??? HAI TH L?? 1 V?? ?????U SINH
        // LOOP T???I K???T Q???A TRUY V???N NH??NG T???I DTO TA ?????T(DO TA) C?? ??T R??NG BU???C H??N
        // JACKSON C?? TH???
        // PARSE ??C TH??NH C??NG.
        //
        return userMapper.toUserDto(user);

    }

    @Transactional
    public UserResponse createUser(final CreateUserRequest userRequest) {
        System.out.println("why is exist:"+userRequest.getUsername());
        if (userRepo.existsByUsername(userRequest.getUsername())) {
            log.warn("user name {} existed" + userRequest.getUsername());
            throw new BadRequesetException("Ten tai khoan da ton tai!");
        }
        //factory + builder design pattern
        User user = UserFactory.create(userRequest);
        userRepo.save(user);
        return userMapper.toUserResponse(user);

    }

}
