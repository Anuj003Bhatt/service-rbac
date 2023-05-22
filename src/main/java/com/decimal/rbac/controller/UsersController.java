package com.decimal.rbac.controller;

import com.decimal.rbac.model.dtos.UserDto;
import com.decimal.rbac.model.rest.request.AddUser;
import com.decimal.rbac.model.rest.response.ListUserResponse;
import com.decimal.rbac.service.UserService;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UsersController {

    private final UserService userService;

    public UsersController(UserService userService) {
        this.userService = userService;
    }

//    @GetMapping
    public List<UserDto> listAllUsers(){
        return userService.listAllUsers();
    }

    @GetMapping
    public ListUserResponse getUsersPage(
            @PageableDefault(size = 20)
            @SortDefault.SortDefaults({
                    @SortDefault(sort = "id", direction = Sort.Direction.ASC)
            }) Pageable pageable){
        return userService.getPaginated(pageable);
    }

    @GetMapping("/_byId/{id}")
    public UserDto findUserById(@PathVariable("id") UUID id){
        return userService.findUserById(id);
    }

    @GetMapping("/_byUsername/{name}")
    public UserDto findUserByUsername(@PathVariable("name") String username){
        return userService.findUserByUsername(username);
    }

    @PostMapping
    public UserDto createUser(@RequestBody AddUser user){
        return userService.addUser(user);
    }

    @PatchMapping("/{id}/disable")
    public Map<String, String> disableUser(@PathVariable("id")UUID id){
        userService.disableUser(id);
        return Map.of("message",String.format("User %s has been disabled", id));
    }

    @PatchMapping("/{id}/enable")
    public Map<String, String> enableUser(@PathVariable("id")UUID id){
        userService.enableUser(id);
        return Map.of("message",String.format("User %s has been enabled", id));
    }


}
