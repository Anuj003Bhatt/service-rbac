package com.decimal.rbac.service.pg;

import com.decimal.rbac.constants.IntegrityErrorUtil;
import com.decimal.rbac.exceptions.NotFoundException;
import com.decimal.rbac.model.dtos.BridgeUtil;
import com.decimal.rbac.model.dtos.RoleDto;
import com.decimal.rbac.model.dtos.RoleGroupDto;
import com.decimal.rbac.model.entities.Role;
import com.decimal.rbac.model.rest.request.AddRole;
import com.decimal.rbac.model.rest.request.AddRoleGroup;
import com.decimal.rbac.model.rest.response.ListResponse;
import com.decimal.rbac.repositories.RoleGroupRepository;
import com.decimal.rbac.repositories.RoleRepository;
import com.decimal.rbac.service.RoleService;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class RoleServicePgImpl implements RoleService {

    private final RoleRepository roleRepository;
    private final RoleGroupRepository roleGroupRepository;

    @Override
    public ListResponse<RoleDto> getAllRoles(Pageable pageable) {
        Page<Role> roles = roleRepository.findAll(pageable);
        return BridgeUtil.buildPaginatedResponse(roles);
    }

    @Override
    public RoleDto getRoleById(UUID id) {
        return roleRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Role with id %s not found",id)
        ).toDto();
    }

    @Override
    public ListResponse<RoleDto> searchRoleByName(String name, Pageable pageable) {
        Page<Role> roles = roleRepository.findByNameContaining(name, pageable);
        return BridgeUtil.buildPaginatedResponse(roles);
    }

    @Override
    public RoleDto createRole(AddRole role) {
        try {
            return roleRepository.save(role.toDataModelObject()).toDto();
        } catch (DataIntegrityViolationException ex) {
            throw IntegrityErrorUtil.formatIntegrityExceptions(ex);
        }
    }

    @Override
    public RoleGroupDto addRoleGroup(AddRoleGroup roleGroup) {
        try{
            return roleGroupRepository.save(roleGroup.toDataModelObject()).toDto();
        } catch (DataIntegrityViolationException ex) {
            throw IntegrityErrorUtil.formatIntegrityExceptions(ex);
        }
    }

    @Override
    public RoleGroupDto getRoleGroupById(UUID id) {
        return roleGroupRepository.findById(id).orElseThrow(
                () -> new NotFoundException("No role group found for id: %s", id)
        ).toDto();
    }
}
