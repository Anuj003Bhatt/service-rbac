package com.decimal.rbac.controller;

import com.decimal.rbac.config.OpenApiConfig;
import com.decimal.rbac.model.dtos.UserDto;
import com.decimal.rbac.model.dtos.UserGroupDto;
import com.decimal.rbac.model.rest.request.AddUser;
import com.decimal.rbac.model.rest.request.AddUserGroup;
import com.decimal.rbac.model.rest.request.UserAuthenticationRequest;
import com.decimal.rbac.model.rest.response.ListResponse;
import com.decimal.rbac.service.UserService;
import com.decimal.rbac.util.JwtUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
@Tag(name = OpenApiConfig.USERS_TAG)
public class UsersController {

    private final UserService userService;

    @GetMapping
    @Operation(
            summary = "List users",
            description = "Find the list of all users"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found user by ID"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ListResponse<UserDto> getUsersPage(
            @PageableDefault(size = 20)
            @SortDefault.SortDefaults({
                    @SortDefault(sort = "id", direction = Sort.Direction.ASC)
            }) Pageable pageable){
        return userService.getPaginated(pageable);
    }

    @GetMapping("/_byId/{id}")
    @Operation(
            summary = "Fetch user by ID",
            description = "Find a user by its ID"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found user by ID"),
            @ApiResponse(responseCode = "404", description = "No user found for given ID")
    })
    public UserDto findUserById(@PathVariable("id") UUID id){
        return userService.findUserById(id);
    }

    @GetMapping("/_byUsername/{name}")
    @Operation(
            summary = "Fetch user by username",
            description = "Find a user by its username"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found user by username"),
            @ApiResponse(responseCode = "404", description = "Username not found")
    })
    public UserDto findUserByUsername(@PathVariable("name") String username){
        return userService.findUserByUsername(username);
    }

    @PostMapping
    @Operation(
            summary = "Add new user",
            description = "Create a new user for the platform"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "User added successfully")
    })
    @ResponseStatus(HttpStatus.CREATED)
    public UserDto createUser(@RequestBody AddUser user){
        return userService.addUser(user);
    }

    @PatchMapping("/{id}/disable")
    @Operation(
            summary = "Disable an existing user"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User disabled successfully"),
            @ApiResponse(responseCode = "404", description = "No user found for given ID")
    })
    public Map<String, String> disableUser(@PathVariable("id")UUID id){
        userService.disableUser(id);
        return Map.of("message",String.format("User %s has been disabled", id));
    }

    @PatchMapping("/{id}/enable")
    @Operation(
            summary = "Enable an already disabled user"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User enabled successfully"),
            @ApiResponse(responseCode = "404", description = "No disabled user found for given ID")
    })
    public Map<String, String> enableUser(@PathVariable("id")UUID id){
        userService.enableUser(id);
        return Map.of("message",String.format("User %s has been enabled", id));
    }

    @PostMapping("authenticate")
    @Operation(summary = "Authenticate a user by username and password and return a JWT")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Authentication successful"),
            @ApiResponse(responseCode = "401", description = "Authentication failed")
    })
    public Map<String, String> authenticate(
            @RequestBody @Valid UserAuthenticationRequest request
    ) {
        String jwt = JwtUtil.generateToken(userService.authenticate(request.getUserName(), request.getPassword()));
        return Map.of("jwt", jwt);
    }

    @PostMapping("groups")
    @Operation(summary = "Create a user group")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "User Group created successfully")
    })
    @ResponseStatus(HttpStatus.CREATED)
    public UserGroupDto createUserGroup(
            @RequestBody @Valid AddUserGroup userGroup
    ) {
        return userService.addUserGroup(userGroup);
    }

    @GetMapping("groups/{id}")
    @Operation(summary = "Get a user group by ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "User Group created successfully")
    })
    public UserGroupDto getUserGroupById(
            @PathVariable("id") UUID id
    ) {
        return userService.getUserGroupById(id);
    }

    @GetMapping("groups")
    @Operation(summary = "List User groups")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "User Group created successfully")
    })
    public ListResponse<UserGroupDto> getUserGroups(
            @PageableDefault(size = 20)
            @SortDefault.SortDefaults({
                    @SortDefault(sort = "id", direction = Sort.Direction.ASC)
            }) Pageable pageable
    ) {
        return userService.getUserGroupsPaginated(pageable);
    }

    @GetMapping("_byGroup/{id}")
    @Operation(summary = "List Users in a group")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "User Group created successfully")
    })
    public ListResponse<UserDto> getUserGroups(
            @PathVariable("id") UUID id
    ) {
        return userService.listUsersInGroup(id);
    }
}
