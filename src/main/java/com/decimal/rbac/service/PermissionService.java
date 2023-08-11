package com.decimal.rbac.service;

import com.decimal.rbac.model.enums.PermissionType;
import com.decimal.rbac.model.dtos.PermissionDto;
import com.decimal.rbac.model.rest.request.AddPermission;
import com.decimal.rbac.model.rest.response.ListResponse;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface PermissionService {

    PermissionDto create(AddPermission permission);

    ListResponse<PermissionDto> getAllPermissions(Pageable pageable);

    PermissionDto getPermissionById(UUID id);

    PermissionDto getPermissionByName(String name);

    ListResponse<PermissionDto> getPermissionByAccessTypes(List<PermissionType> permissionTypes, Pageable pageable);
}
