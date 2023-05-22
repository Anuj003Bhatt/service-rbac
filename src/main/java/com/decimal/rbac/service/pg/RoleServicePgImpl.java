package com.decimal.rbac.service.pg;

import com.decimal.rbac.exceptions.NotFoundException;
import com.decimal.rbac.model.dtos.RoleDto;
import com.decimal.rbac.model.entities.Role;
import com.decimal.rbac.model.rest.AddRole;
import com.decimal.rbac.repositories.RoleRepository;
import com.decimal.rbac.service.RoleService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.StreamSupport;

@Service
public class RoleServicePgImpl implements RoleService {

    private final RoleRepository roleRepository;

    public RoleServicePgImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }
    @Override
    public List<RoleDto> getAllRoles() {
        return StreamSupport.stream(roleRepository.findAll().spliterator(), false).map(Role::toDto).toList();
    }

    @Override
    public RoleDto getRoleById(UUID id) {
        return roleRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Role with id %s not found",id)
        ).toDto();
    }

    @Override
    public List<RoleDto> searchRoleByName(String name) {
        return StreamSupport.stream(roleRepository.findByNameContaining(name).spliterator(), false).map(Role::toDto).toList();
    }

    @Override
    public RoleDto createRole(AddRole role) {
        return roleRepository.save(role.toDataModelObject()).toDto();
    }


}
