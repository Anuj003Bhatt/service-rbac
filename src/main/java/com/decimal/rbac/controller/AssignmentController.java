package com.decimal.rbac.controller;

import com.decimal.rbac.service.RoleAssignmentService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.UUID;

@RequestMapping("assignments")
@RestController
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
@Tag(name = "Assignments")
public class AssignmentController {

    private final RoleAssignmentService roleAssignmentService;

    @PutMapping("user/{userId}/role/{roleId}")
    public Map<String, String> assignUserToRole(
            @PathVariable("userId") UUID userId,
            @PathVariable("roleId") UUID roleId
    ){
        roleAssignmentService.assignRoleToUser(roleId, userId);
        return Map.of("message","Role has been assigned to the user.");
    }

    @PutMapping("role/{roleId}/permission/{permissionId}")
    public Map<String, String> addPermissionToRole(
            @PathVariable("roleId") UUID roleId,
            @PathVariable("permissionId") UUID permissionId
    ){
        roleAssignmentService.addPermissionToRole(roleId, permissionId);
        return Map.of("message","Permission has been added to the specified role successfully.");
    }

    @PutMapping("user/{userId}/userGroup/{userGroupId}")
    public Map<String, String> assignUserGroupToUser(
            @PathVariable("userId") UUID userId,
            @PathVariable("userGroupId") UUID userGroupId
    ){
        roleAssignmentService.assignUserGroupToUser(userId, userGroupId);
        return Map.of("message","User has been added to the specified user group successfully.");
    }

    @PutMapping("user/{userId}/roleGroup/{roleGroupId}")
    public Map<String, String> assignRoleGroupToUser(
            @PathVariable("userId") UUID userId,
            @PathVariable("roleGroupId") UUID roleGroupId
    ){
        roleAssignmentService.assignRoleGroupToUser(userId, roleGroupId);
        return Map.of("message","User has been assigned the role group successfully.");
    }

    @PutMapping("role/{roleId}/roleGroup/{roleGroupId}")
    public Map<String, String> addRoleToRoleGroup(
            @PathVariable("roleId") UUID roleId,
            @PathVariable("roleGroupId") UUID roleGroupId
    ){
        roleAssignmentService.addRoleToRoleGroup(roleId, roleGroupId);
        return Map.of("message","Role has been added to the role group successfully.");
    }

    @PutMapping("role/{roleId}/userGroup/{userGroupId}")
    public Map<String, String> assignRoleToUserGroup(
            @PathVariable("roleId") UUID roleId,
            @PathVariable("userGroupId") UUID userGroupId
    ){
        roleAssignmentService.assignRoleToUserGroup(roleId, userGroupId);
        return Map.of("message","Role assigned to the user group successfully.");
    }
}
