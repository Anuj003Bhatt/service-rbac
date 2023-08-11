package com.decimal.rbac.service;

import java.util.UUID;

public interface RoleAssignmentService {
    void assignRoleToUser(UUID role_id, UUID user_id);
    void addPermissionToRole(UUID roleId, UUID permissionId);
}
