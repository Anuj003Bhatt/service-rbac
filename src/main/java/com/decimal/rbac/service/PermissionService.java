package com.decimal.rbac.service;

import com.decimal.rbac.model.entities.constants.PermissionType;
import com.decimal.rbac.model.dtos.PermissionDto;

import java.util.List;
import java.util.UUID;

public interface PermissionService {
    List<PermissionDto> getAllPermissions();

    PermissionDto getPermissionById(UUID id);

    PermissionDto getPermissionByName(String name);

    List<PermissionDto> getPermissionByAccessTypes(List<PermissionType> permissionTypes);
}
