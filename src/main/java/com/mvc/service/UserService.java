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

    public UserDto getUser(String username) {
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

        // nếu trả về một DTO THÌ SẼ ĐC DO BẢN THÂN TRUY VẤN CẢ HAI TH LÀ 1 VÀ ĐỀU SINH
        // LOOP TẠI KẾT QỦA TRUY VẤN NHƯNG TẠI DTO TA ĐẶT(DO TA) CÓ ÍT RÀNG BUỘC HƠN
        // JACKSON CÓ THỂ
        // PARSE ĐC THÀNH CÔNG.
        //
        return userMapper.toUserDto(user);

    }

    @Transactional
    public UserResponse createUser(final CreateUserRequest userRequest) {
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
