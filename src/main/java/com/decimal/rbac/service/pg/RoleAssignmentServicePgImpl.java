package com.decimal.rbac.service.pg;

import com.decimal.rbac.exceptions.BadRequestException;
import com.decimal.rbac.exceptions.NotFoundException;
import com.decimal.rbac.model.dtos.RoleDto;
import com.decimal.rbac.model.entities.Permission;
import com.decimal.rbac.model.entities.Role;
import com.decimal.rbac.model.entities.User;
import com.decimal.rbac.repositories.PermissionRepository;
import com.decimal.rbac.repositories.RoleRepository;
import com.decimal.rbac.repositories.UserRepository;
import com.decimal.rbac.service.RoleAssignmentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class RoleAssignmentServicePgImpl implements RoleAssignmentService {

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final PermissionRepository permissionRepository;

    @Override
    public void assignRoleToUser(UUID roleId, UUID userId) {
        Role role = roleRepository.findById(roleId).orElseThrow(
                () -> new NotFoundException("Role with id %s not found",roleId)
        );

        User user = userRepository.findById(userId).orElseThrow(
                () -> new NotFoundException("User with id %s not found",userId)
        );
        if (!user.hasRole(role)) {
            user.addRole(role);
            userRepository.save(user);
        }
    }

    @Override
    public void addPermissionToRole(UUID roleId, UUID permissionId) {
        Role role = roleRepository.findById(roleId).orElseThrow(
                () -> new NotFoundException("Role with id %s not found",roleId)
        );
        Permission permission = permissionRepository.findById(permissionId).orElseThrow(
                () -> new NotFoundException("User with id %s not found",permissionId)
        );
        if(role.getRolePermissions().stream().anyMatch(p -> p.getId().equals(permission.getId()))) {
            throw new BadRequestException("Permission already added");
        }
        role.getRolePermissions().add(permission);
        roleRepository.save(role).toDto();
    }
}
