package com.decimal.rbac.service;

import com.decimal.rbac.model.dtos.RoleDto;
import com.decimal.rbac.model.rest.request.AddRole;

import java.util.List;
import java.util.UUID;

public interface RoleService {

    List<RoleDto> getAllRoles();

    RoleDto getRoleById(UUID id);

    List<RoleDto> searchRoleByName(String name);

    RoleDto createRole(AddRole role);
}
