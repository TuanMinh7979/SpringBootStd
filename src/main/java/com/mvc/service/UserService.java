package com.mvc.service;

import com.mvc.dto.UserDto;
import com.mvc.dto.request.CreateUserRequest;
import com.mvc.exception.ResourceNotFoundException;
import com.mvc.model.User;
import com.mvc.repo.UserRepo;
import com.mvc.service.factory.UserFactory;
import com.mvc.service.mapper.UserMapper;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.swing.text.html.Option;
import javax.transaction.Transactional;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserMapper userMapper;
    private final UserRepo userRepo;

    public UserDto findByUsernameWithPermission(String username) {

        return userRepo.findByUsernameWithPermission(username)
                .map(usermodel -> userMapper.toUserDto(usermodel))
                .orElseGet(() -> {
                    log.warn("User not found" + username);
                    throw new ResourceNotFoundException("Khong tim thay nguoi dung");
                });
    }

    @Transactional
    public UserDto createUser(final CreateUserRequest userRequest) {
        User user = UserFactory.create(userRequest);
        userRepo.save(user);
        return userMapper.toUserDto(user);

    }

    //Thử sử dụng model để return cho client và không sử dụng json ignore
//    public User findByUsernameWithPermissionUser(String username) {
//        Optional<User> useropt = userRepo.findByUsernameWithPermission(username);
//        int i = 1;
//        useropt.ifPresent((x) -> {
//            System.out.println("- USER user: " + x.getUsername());
//            System.out.println("TA VẪN TÌM ĐC BẰNG QUERY KHÔNG THEO QUAN HỆ(nên sử dụng cho lazy) như khi render json với dto sẽ không có logic quan hệ nào");
//        });
//
//        return userRepo.findByUsernameWithPermission(username)
//
//                .orElseGet(() -> {
//                    log.warn("User not found" + username);
//                    throw new ResourceNotFoundException("Khong tim thay nguoi dung");
//                });
//    }
}
