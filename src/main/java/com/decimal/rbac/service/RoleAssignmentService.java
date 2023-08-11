package com.decimal.rbac.service;

import java.util.UUID;

public interface RoleAssignmentService {
    void assignRoleToUser(UUID role_id, UUID user_id);
    void addPermissionToRole(UUID roleId, UUID permissionId);

    void assignUserGroupToUser(UUID userId, UUID userGroupId);

    void assignRoleToUserGroup(UUID roleId, UUID userGroupId);

    void assignRoleGroupToUser(UUID userId, UUID roleGroupId);

    void addRoleToRoleGroup(UUID roleId, UUID roleGroupId);

    void addUserToUserGroup(UUID userId, UUID userGroupId);
}
