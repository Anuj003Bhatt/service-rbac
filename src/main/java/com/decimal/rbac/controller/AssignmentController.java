package com.decimal.rbac.controller;

import com.decimal.rbac.service.RoleAssignmentService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.UUID;

@RequestMapping("assignment")
@RestController
@AllArgsConstructor
public class AssignmentController {

    private final RoleAssignmentService roleAssignmentService;

    @PutMapping("userRoles/{userId}/{roleId}")
    public Map<String, String> assignUserToRole(
            @PathVariable("userId") UUID userId,
            @PathVariable("roleId") UUID roleId
    ){
        roleAssignmentService.assignRoleToUser(roleId, userId);
        return Map.of("message","Role has been assigned to the user.");
    }

    @PutMapping("rolePermissions/{roleId}/{permissionId}")
    public Map<String, String> addPermissionToRole(
            @PathVariable("roleId") UUID roleId,
            @PathVariable("permissionId") UUID permissionId
    ){
        roleAssignmentService.addPermissionToRole(roleId, permissionId);
        return Map.of("message","Permission has been added to the specified role successfully.");
    }
}
