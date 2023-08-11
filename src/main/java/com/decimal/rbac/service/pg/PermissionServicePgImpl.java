package com.decimal.rbac.service.pg;

import com.decimal.rbac.model.dtos.BridgeUtil;
import com.decimal.rbac.model.enums.PermissionType;
import com.decimal.rbac.model.rest.request.AddPermission;
import com.decimal.rbac.model.rest.response.ListResponse;
import com.decimal.rbac.service.PermissionService;
import com.decimal.rbac.exceptions.NotFoundException;
import com.decimal.rbac.model.dtos.PermissionDto;
import com.decimal.rbac.model.entities.Permission;
import com.decimal.rbac.repositories.PermissionRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class PermissionServicePgImpl implements PermissionService {
    private final PermissionRepository permissionRepository;

    @Override
    public PermissionDto create(AddPermission permission) {
        return permissionRepository.save(permission.toDataModelObject()).toDto();
    }

    @Override
    public ListResponse<PermissionDto> getAllPermissions(Pageable pageable) {
        Page<Permission> permissions = permissionRepository.findAll(pageable);
        return BridgeUtil.buildPaginatedResponse(permissions);
    }

    @Override
    public PermissionDto getPermissionById(UUID id) {
        return permissionRepository.findById(id).orElseThrow(
                () -> new NotFoundException(String.format("No permission found with id %s",id))
        ).toDto();
    }

    @Override
    public PermissionDto getPermissionByName(String name) {
        return permissionRepository.findByName(name).orElseThrow(
                () -> new NotFoundException(String.format("No permissions exist by the name %s",name))
        ).toDto();
    }

    @Override
    public ListResponse<PermissionDto> getPermissionByAccessTypes(List<PermissionType> permissionTypes, Pageable pageable) {
        Page<Permission> permissions = permissionRepository.findByAccessTypeIn(permissionTypes, pageable);
        return BridgeUtil.buildPaginatedResponse(permissions);
    }
}
