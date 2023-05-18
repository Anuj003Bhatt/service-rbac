package com.decimal.rbac.controller;

import com.decimal.rbac.model.entities.constants.PermissionType;
import com.decimal.rbac.model.dtos.PermissionDto;
import com.decimal.rbac.service.PermissionService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/permissions")
public class PermissionsController {
    private final PermissionService permissionService;

    public PermissionsController(PermissionService permissionService) {
        this.permissionService = permissionService;
    }

    @GetMapping
    public List<PermissionDto> findAll() {
        return this.permissionService.getAllPermissions();
    }

    @GetMapping("/_byId/{id}")
    public PermissionDto findById(@PathVariable(name = "id") UUID id) {
        return this.permissionService.getPermissionById(id);
    }

    @GetMapping("/_byName/{name}")
    public PermissionDto findByName(@PathVariable(name = "name") String name) {
        return this.permissionService.getPermissionByName(name);
    }

    @GetMapping("/_byAccess/{types}")
    public List<PermissionDto> findByAccessTypes(@PathVariable(name = "types") List<String> types) {
        return this.permissionService.getPermissionByAccessTypes(
                types.stream().map(it -> PermissionType.valueOf(it.toUpperCase())).toList()
        );
    }
}
