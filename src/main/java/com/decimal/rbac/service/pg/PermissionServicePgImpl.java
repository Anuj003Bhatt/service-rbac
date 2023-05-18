package com.decimal.rbac.service.pg;

import com.decimal.rbac.model.entities.constants.PermissionType;
import com.decimal.rbac.service.PermissionService;
import com.decimal.rbac.exceptions.NotFoundException;
import com.decimal.rbac.model.dtos.PermissionDto;
import com.decimal.rbac.model.entities.Permission;
import com.decimal.rbac.repositories.PermissionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.StreamSupport;

@Service
public class PermissionServicePgImpl implements PermissionService {
    private final PermissionRepository permissionRepository;

    public PermissionServicePgImpl(PermissionRepository repository) {
        this.permissionRepository = repository;
    }

    @Override
    public List<PermissionDto> getAllPermissions() {
        return permissionRepository.findAll().stream()
                .map(Permission::toDto)
                .toList();
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
    public List<PermissionDto> getPermissionByAccessTypes(List<PermissionType> permissionTypes) {
        return StreamSupport.stream(
                permissionRepository.findByAccessTypeIn(permissionTypes/*.stream().map(it -> it.getAccess()).toList()*/).spliterator(),
                false
        ).map(Permission::toDto).toList();
    }
}
