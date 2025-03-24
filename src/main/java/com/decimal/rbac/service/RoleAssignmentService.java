package com.decimal.rbac.service;

import java.util.UUID;

public interface RoleAssignmentService {
    void assignRoleToUser(UUID role_id, UUID user_id);

    void revokeRoleFromUser(UUID roleId, UUID userId);

    void addPermissionToRole(UUID roleId, UUID permissionId);

    void removePermissionFromRole(UUID roleId, UUID permissionId);

    void assignUserGroupToUser(UUID userId, UUID userGroupId);

    void removeUserFromUserGroup(UUID userId, UUID userGroupId);

    void assignRoleToUserGroup(UUID roleId, UUID userGroupId);

    void revokeRoleFromUserGroup(UUID roleId, UUID userGroupId);

    void assignRoleGroupToUser(UUID userId, UUID roleGroupId);

    void revokeRoleGroupFromUser(UUID userId, UUID roleGroupId);

    void addRoleToRoleGroup(UUID roleId, UUID roleGroupId);

    void removeRoleFromRoleGroup(UUID roleId, UUID roleGroupId);

    void assignRoleGroupToUserGroup(UUID roleGroupId, UUID userGroupId);

    void revokeRoleGroupFromUserGroup(UUID roleGroupId, UUID userGroupId);
}
