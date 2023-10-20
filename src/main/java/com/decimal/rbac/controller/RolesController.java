package com.decimal.rbac.controller;

import com.decimal.rbac.model.dtos.RoleDto;
import com.decimal.rbac.model.dtos.RoleGroupDto;
import com.decimal.rbac.model.dtos.UserGroupDto;
import com.decimal.rbac.model.rest.request.AddRole;
import com.decimal.rbac.model.rest.request.AddRoleGroup;
import com.decimal.rbac.model.rest.response.ListResponse;
import com.decimal.rbac.service.RoleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("roles")
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class RolesController {

    private final RoleService roleService;

    @PostMapping
    @Operation(summary = "Add role", description = "Create a new role")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Successfully added a new role")
    })
    @ResponseStatus(HttpStatus.CREATED)
    public RoleDto createRole(@RequestBody AddRole role) {
        return roleService.createRole(role);
    }

    @GetMapping
    @Operation(summary = "List roles")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved list of roles"),
            @ApiResponse(responseCode = "500", description = "Internal Server error")
    })

    public ListResponse<RoleDto> listAllRoles(
            @PageableDefault(size = 20)
            @SortDefault.SortDefaults({
                    @SortDefault(sort = "id", direction = Sort.Direction.ASC)
            }) @Parameter(hidden = true) Pageable pageable
    ) {
        return roleService.getAllRoles(pageable);
    }

    @GetMapping("{id}")
    @Operation(summary = "Get role by ID", description = "Fetch a role by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully fetched role"),
            @ApiResponse(responseCode = "404", description = "No role found for the given ID")
    })
    public RoleDto getRoleById(@PathVariable("id") UUID id) {
        return roleService.getRoleById(id);
    }

    @GetMapping("/search/_byName/{name}")
    @Operation(summary = "Search roles by name")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved roles by name")
    })
    public ListResponse<RoleDto> getRoleById(
            @PathVariable("name") String name,
            @PageableDefault(size = 20)
            @SortDefault.SortDefaults({
                    @SortDefault(sort = "id", direction = Sort.Direction.ASC)
            }) @Parameter(hidden = true) Pageable pageable) {
        return roleService.searchRoleByName(name, pageable);
    }

    @PostMapping("groups")
    @Operation(summary = "Create a role group")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Role Group found")
    })
    public RoleGroupDto createUserGroup(
            @RequestBody @Valid AddRoleGroup userGroup
    ) {
        return roleService.addRoleGroup(userGroup);
    }

    @GetMapping("groups/{id}")
    @Operation(summary = "Get a role group by ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Role Group found")
    })
    public RoleGroupDto getUserGroupById(
            @PathVariable("id") UUID id
    ) {
        return roleService.getRoleGroupById(id);
    }
}
