package com.mvc.controller;

import com.mvc.dto.UserDto;
import com.mvc.dto.request.CreateUserRequest;
import com.mvc.dto.response.UserResponse;
import com.mvc.model.User;
import com.mvc.repo.UserRepo;
import com.mvc.service.UserService;

import com.mvc.service.factory.UserFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final UserRepo userRepo;

    @GetMapping("{username}")
    public ResponseEntity<UserDto> searchByUsernameWithPermission(@PathVariable("username") String username) {
        return new ResponseEntity<>(userService.findByUsernameWithPermission(username), HttpStatus.OK);

    }
    //TEST
//    @GetMapping("{username}")
//    public ResponseEntity<User> searchByUsernameWithPermission(@PathVariable("username") String username) {
//        return new ResponseEntity<>(userService.findByUsernameWithPermissionUser(username), HttpStatus.OK);
//
//    }
    //TEST

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
