package com.mvc.controller;

import com.mvc.dto.UserDto;
import com.mvc.dto.request.CreateUserRequest;
import com.mvc.dto.response.UserResponse;
import com.mvc.repo.UserRepo;
import com.mvc.service.UserService;
import com.mvc.service.factory.UserFactory;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final UserRepo userRepo;

    @GetMapping("{username}")
    // trong th cần lấy thêm set vai trò và quyền thì ra return UserDto
    // nếu không cần thiết thì trả về UserResponse thì ok hơn
    // trong trường cần xử lý phức tạp thì dùng userDto
    public ResponseEntity<UserDto> searchByUsernameWithPermission(@PathVariable("username") String username) {
        return new ResponseEntity<>(userService.findByUsernameWithPermission(username), HttpStatus.OK);

    }

    // @GetMapping("detail/{username}")
    // public String searchByUsernameWithPermission(@PathVariable("username") String
    // username) {
    // System.out.println(userRepo.findByUsernameWithPermission(username));
    // return userRepo.findByUsernameWithPermission(username).toString();

    // }

    @PostMapping("add")
    public UserResponse createUser(@RequestBody final CreateUserRequest userRequest) {
        userRepo.save(UserFactory.create(userRequest));
        return userService.createUser(userRequest);

    }
}
