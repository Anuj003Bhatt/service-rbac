package com.decimal.rbac.controller;

import com.decimal.rbac.config.OpenApiConfig;
import com.decimal.rbac.service.RoleAssignmentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
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
@Tag(name = OpenApiConfig.ASSIGNMENTS_TAG)
public class AssignmentController {

    private final RoleAssignmentService roleAssignmentService;

    @PutMapping("user/{userId}/role/{roleId}")
    @Operation(summary = "Assign Role to User")
    public Map<String, String> assignUserToRole(
            @PathVariable("userId") UUID userId,
            @PathVariable("roleId") UUID roleId
    ){
        roleAssignmentService.assignRoleToUser(roleId, userId);
        return Map.of("message","Role has been assigned to the user.");
    }

    @DeleteMapping("user/{userId}/role/{roleId}/revoke")
    @Operation(summary = "Revoke Role from User")
    public Map<String, String> revokeUserRole(
            @PathVariable("userId") UUID userId,
            @PathVariable("roleId") UUID roleId
    ){
        roleAssignmentService.revokeRoleFromUser(roleId, userId);
        return Map.of("message","Role successfully revoked from the user.");
    }

    @PutMapping("role/{roleId}/permission/{permissionId}")
    @Operation(summary = "Add Permission to Role")
    public Map<String, String> addPermissionToRole(
            @PathVariable("roleId") UUID roleId,
            @PathVariable("permissionId") UUID permissionId
    ){
        roleAssignmentService.addPermissionToRole(roleId, permissionId);
        return Map.of("message","Permission has been added to the specified role successfully.");
    }

    @DeleteMapping("role/{roleId}/permission/{permissionId}/revoke")
    @Operation(summary = "Remove Permission from Role")
    public Map<String, String> removePermissionFromRole(
            @PathVariable("roleId") UUID roleId,
            @PathVariable("permissionId") UUID permissionId
    ){
        roleAssignmentService.removePermissionFromRole(roleId, permissionId);
        return Map.of("message","Permission has been removed from the specified role successfully.");
    }

    @PutMapping("userGroup/{userGroupId}/user/{userId}")
    @Operation(summary = "Add User to User Group")
    public Map<String, String> assignUserGroupToUser(
            @PathVariable("userId") UUID userId,
            @PathVariable("userGroupId") UUID userGroupId
    ){
        roleAssignmentService.assignUserGroupToUser(userId, userGroupId);
        return Map.of("message","User has been added to the specified user group successfully.");
    }

    @DeleteMapping("userGroup/{userGroupId}/user/{userId}/remove")
    @Operation(summary = "Remove User from User Group")
    public Map<String, String> removeUserFromUserGroup(
            @PathVariable("userId") UUID userId,
            @PathVariable("userGroupId") UUID userGroupId
    ){
        roleAssignmentService.removeUserFromUserGroup(userId, userGroupId);
        return Map.of("message","User has been removed from the specified user group successfully.");
    }

    @PutMapping("user/{userId}/roleGroup/{roleGroupId}")
    @Operation(summary = "Assign Role Group to User")
    public Map<String, String> assignRoleGroupToUser(
            @PathVariable("userId") UUID userId,
            @PathVariable("roleGroupId") UUID roleGroupId
    ){
        roleAssignmentService.assignRoleGroupToUser(userId, roleGroupId);
        return Map.of("message","User has been assigned the role group successfully.");
    }

    @DeleteMapping("user/{userId}/roleGroup/{roleGroupId}/revoke")
    @Operation(summary = "Revoke Role Group from User")
    public Map<String, String> revokeRoleGroupFromUser(
            @PathVariable("userId") UUID userId,
            @PathVariable("roleGroupId") UUID roleGroupId
    ){
        roleAssignmentService.revokeRoleGroupFromUser(userId, roleGroupId);
        return Map.of("message","Role group successfully been revoked from User.");
    }

    @PutMapping("roleGroup/{roleGroupId}/role/{roleId}")
    @Operation(summary = "Add Role to Role Group")
    public Map<String, String> addRoleToRoleGroup(
            @PathVariable("roleId") UUID roleId,
            @PathVariable("roleGroupId") UUID roleGroupId
    ){
        roleAssignmentService.addRoleToRoleGroup(roleId, roleGroupId);
        return Map.of("message","Role has been added to the role group successfully.");
    }

    @DeleteMapping("roleGroup/{roleGroupId}/role/{roleId}/remove")
    @Operation(summary = "Remove Role From Role Group")
    public Map<String, String> removeRoleFromRoleGroup(
            @PathVariable("roleId") UUID roleId,
            @PathVariable("roleGroupId") UUID roleGroupId
    ){
        roleAssignmentService.removeRoleFromRoleGroup(roleId, roleGroupId);
        return Map.of("message","Role has been removed from the role group successfully.");
    }

    @PutMapping("userGroup/{userGroupId}/role/{roleId}")
    @Operation(summary = "Assign Role to User Group")
    public Map<String, String> assignRoleToUserGroup(
            @PathVariable("roleId") UUID roleId,
            @PathVariable("userGroupId") UUID userGroupId
    ){
        roleAssignmentService.assignRoleToUserGroup(roleId, userGroupId);
        return Map.of("message","Role assigned to the user group successfully.");
    }

    @DeleteMapping("userGroup/{userGroupId}/role/{roleId}/revoke")
    @Operation(summary = "Revoke Role from User Group")
    public Map<String, String> revokeRoleFromUserGroup(
            @PathVariable("roleId") UUID roleId,
            @PathVariable("userGroupId") UUID userGroupId
    ){
        roleAssignmentService.revokeRoleFromUserGroup(roleId, userGroupId);
        return Map.of("message","Role revoked from the user group successfully.");
    }

    @PutMapping("userGroup/{userGroupId}/roleGroup/{roleGroupId}")
    @Operation(summary = "Assign Role Group to User Group")
    public Map<String, String> assignRoleGroupToUserGroup(
            @PathVariable("roleGroupId") UUID roleGroupId,
            @PathVariable("userGroupId") UUID userGroupId
    ){
        roleAssignmentService.assignRoleGroupToUserGroup(roleGroupId, userGroupId);
        return Map.of("message","Role Group assigned to the user group successfully.");
    }

    @DeleteMapping("userGroup/{userGroupId}/roleGroup/{roleGroupId}/revoke")
    @Operation(summary = "Revoke Role Group from User Group")
    public Map<String, String> revokeRoleGroupFromUserGroup(
            @PathVariable("roleGroupId") UUID roleGroupId,
            @PathVariable("userGroupId") UUID userGroupId
    ){
        roleAssignmentService.revokeRoleGroupFromUserGroup(roleGroupId, userGroupId);
        return Map.of("message","Role Group revoked from the user group successfully.");
    }
}
