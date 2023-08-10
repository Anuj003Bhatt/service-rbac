package com.decimal.rbac.service;

import java.util.UUID;

public interface RoleAssignmentService {
    void addUserToRole(UUID role_id, UUID user_id);
}
