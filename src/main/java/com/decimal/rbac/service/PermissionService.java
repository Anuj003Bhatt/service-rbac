package com.decimal.rbac.service;

import com.decimal.rbac.model.enums.PermissionType;
import com.decimal.rbac.model.dtos.PermissionDto;

import java.util.List;
import java.util.UUID;

public interface PermissionService {

    PermissionDto create(PermissionDto permission);

    List<PermissionDto> getAllPermissions();

    PermissionDto getPermissionById(UUID id);

    PermissionDto getPermissionByName(String name);

    List<PermissionDto> getPermissionByAccessTypes(List<PermissionType> permissionTypes);
}
