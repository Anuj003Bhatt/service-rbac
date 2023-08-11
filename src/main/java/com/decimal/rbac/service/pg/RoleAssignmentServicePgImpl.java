package com.decimal.rbac.service.pg;

import com.decimal.rbac.exceptions.BadRequestException;
import com.decimal.rbac.exceptions.NotFoundException;
import com.decimal.rbac.model.dtos.RoleDto;
import com.decimal.rbac.model.entities.Permission;
import com.decimal.rbac.model.entities.Role;
import com.decimal.rbac.model.entities.RoleGroup;
import com.decimal.rbac.model.entities.User;
import com.decimal.rbac.model.entities.UserGroup;
import com.decimal.rbac.repositories.PermissionRepository;
import com.decimal.rbac.repositories.RoleGroupRepository;
import com.decimal.rbac.repositories.RoleRepository;
import com.decimal.rbac.repositories.UserGroupRepository;
import com.decimal.rbac.repositories.UserRepository;
import com.decimal.rbac.service.RoleAssignmentService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
@Transactional
public class RoleAssignmentServicePgImpl implements RoleAssignmentService {

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final PermissionRepository permissionRepository;
    private final UserGroupRepository userGroupRepository;
    private final RoleGroupRepository roleGroupRepository;

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

    @Override
    public void assignUserGroupToUser(UUID userId, UUID userGroupId) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new NotFoundException("User with id %s not found",userId)
        );

        UserGroup group = userGroupRepository.findById(userGroupId).orElseThrow(
                () -> new NotFoundException("User Group with id %s not found",userGroupId)
        );

        if (user.getUserGroups().stream().anyMatch(g -> g.getId().equals(group.getId()))) {
            throw new BadRequestException("User is already a part of the user group.");
        }
        group.getUsers().add(user);
        userGroupRepository.save(group);
    }

    @Override
    public void assignRoleToUserGroup(UUID roleId, UUID userGroupId) {
        UserGroup group = userGroupRepository.findById(userGroupId).orElseThrow(
                () -> new NotFoundException("User Group with id %s not found",userGroupId)
        );

        Role role = roleRepository.findById(roleId).orElseThrow(
                () -> new NotFoundException("Role with id %s not found",roleId)
        );

        if (group.getRolesInGroup().stream().anyMatch(r -> r.getId().equals(role.getId()))) {
            throw new BadRequestException("Role is already assigned to the user group.");
        }
        group.getRolesInGroup().add(role);
        userGroupRepository.save(group);
    }

    @Override
    public void assignRoleGroupToUser(UUID userId, UUID roleGroupId) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new NotFoundException("User with id %s not found",userId)
        );

        RoleGroup group = roleGroupRepository.findById(roleGroupId).orElseThrow(
                () -> new NotFoundException("Role Group with id %s not found",roleGroupId)
        );

        if (user.getRoleGroups().stream().anyMatch(g -> g.getId().equals(group.getId()))) {
            throw new BadRequestException("User has already been assigned the role group.");
        }
        user.getRoleGroups().add(group);
        userRepository.save(user);
    }

    @Override
    public void addRoleToRoleGroup(UUID roleId, UUID roleGroupId) {
        Role role = roleRepository.findById(roleId).orElseThrow(
                () -> new NotFoundException("Role with id %s not found",roleId)
        );

        RoleGroup group = roleGroupRepository.findById(roleGroupId).orElseThrow(
                () -> new NotFoundException("Role Group with id %s not found",roleGroupId)
        );

        if (role.getRoleGroups().stream().anyMatch(g -> g.getId().equals(group.getId()))) {
            throw new BadRequestException("Role has already been added the role group.");
        }

        group.getRoles().add(role);
        roleGroupRepository.save(group);

    }

    @Override
    public void addUserToUserGroup(UUID userId, UUID userGroupId) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new NotFoundException("User with id %s not found",userId)
        );

        UserGroup group = userGroupRepository.findById(userGroupId).orElseThrow(
                () -> new NotFoundException("User Group with id %s not found",userId)
        );

        if (user.getUserGroups().stream().anyMatch(g-> g.getId().equals(group.getId()))) {
            throw new BadRequestException("User has already been added the user group.");
        }
        user.getUserGroups().add(group);
        userRepository.save(user);
    }
}
