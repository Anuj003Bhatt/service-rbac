package com.decimal.rbac.controller;

import com.decimal.rbac.model.dtos.UserDto;
import com.decimal.rbac.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UsersController {

    private final UserService userService;

    public UsersController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<UserDto> listAllUsers(){
        return userService.listAllUsers();
    }

    @GetMapping("/_byId/{id}")
    public UserDto findUserById(@PathVariable("id") UUID id){
        return userService.findUserById(id);
    }

    @GetMapping("/_byUsername/{name}")
    public UserDto findUserByUsername(@PathVariable("name") String username){
        return userService.findUserByUsername(username);
    }
}
