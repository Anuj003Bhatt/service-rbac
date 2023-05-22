package com.decimal.rbac.controller;

import com.decimal.rbac.model.dtos.RoleDto;
import com.decimal.rbac.model.rest.request.AddRole;
import com.decimal.rbac.service.RoleService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("roles")
public class RolesController {

    private final RoleService roleService;

    public RolesController(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping
    public List<RoleDto> listAllRoles() {
        return roleService.getAllRoles();
    }

    @GetMapping("{id}")
    public RoleDto getRoleById(@PathVariable("id") UUID id) {
        return roleService.getRoleById(id);
    }

    @GetMapping("/search/_byName/{name}")
    public List<RoleDto> getRoleById(@PathVariable("name") String name) {
        return roleService.searchRoleByName(name);
    }

    @PostMapping
    public RoleDto createRole(@RequestBody AddRole role) {
        return roleService.createRole(role);
    }
}
