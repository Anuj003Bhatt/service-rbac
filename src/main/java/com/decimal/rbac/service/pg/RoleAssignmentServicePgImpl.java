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

import java.util.ArrayList;
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
    public void revokeRoleFromUser(UUID roleId, UUID userId) {
        Role role = roleRepository.findById(roleId).orElseThrow(
                () -> new NotFoundException("Role with id %s not found",roleId)
        );

        User user = userRepository.findById(userId).orElseThrow(
                () -> new NotFoundException("User with id %s not found",userId)
        );
        if (user.hasRole(role)) {
            user.removeRole(role);
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
        if (role.getRolePermissions() == null)
            role.setRolePermissions(new ArrayList<>());

        if(role.getRolePermissions().stream().anyMatch(p -> p.getId().equals(permission.getId()))) {
            throw new BadRequestException("Permission already added");
        }
        role.getRolePermissions().add(permission);
        roleRepository.save(role).toDto();
    }

    @Override
    public void removePermissionFromRole(UUID roleId, UUID permissionId) {
        Role role = roleRepository.findById(roleId).orElseThrow(
                () -> new NotFoundException("Role with id %s not found",roleId)
        );
        Permission permission = permissionRepository.findById(permissionId).orElseThrow(
                () -> new NotFoundException("User with id %s not found",permissionId)
        );
        if (role.getRolePermissions() == null) {
            return;
        }

        if(role.getRolePermissions().stream().anyMatch(p -> p.getId().equals(permission.getId()))) {
            throw new BadRequestException("Permission already added");
        }
        role.setRolePermissions(
                role.getRolePermissions().stream().filter(p -> !p.getId().equals(permissionId)).toList()
        );
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
        if (user.getUserGroups() == null) {
            user.setUserGroups(new ArrayList<>());
        }

        if (user.getUserGroups().stream().anyMatch(g -> g.getId().equals(group.getId()))) {
            throw new BadRequestException("User is already a part of the user group.");
        }
        group.getUsers().add(user);
        userGroupRepository.save(group);
    }

    @Override
    public void removeUserFromUserGroup(UUID userId, UUID userGroupId) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new NotFoundException("User with id %s not found",userId)
        );

        UserGroup group = userGroupRepository.findById(userGroupId).orElseThrow(
                () -> new NotFoundException("User Group with id %s not found",userGroupId)
        );
        if (user.getUserGroups() == null) {
            return;
        }
        group.setUsers(group.getUsers().stream().filter(u -> !u.getId().equals(userId)).toList());
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
        if (group.getRoles() == null) {
            group.setRoles(new ArrayList<>());
        }
        if (group.getRoles().stream().anyMatch(r -> r.getId().equals(role.getId()))) {
            throw new BadRequestException("Role is already assigned to the user group.");
        }
        group.getRoles().add(role);
        userGroupRepository.save(group);
    }

    @Override
    public void revokeRoleFromUserGroup(UUID roleId, UUID userGroupId) {
        UserGroup group = userGroupRepository.findById(userGroupId).orElseThrow(
                () -> new NotFoundException("User Group with id %s not found", userGroupId)
        );

        if (!roleRepository.existsById(roleId)){
            throw new NotFoundException("Role with id %s not found", roleId);
        }
        if (group.getRoles() == null) {
            return;
        }
        group.setRoles(
                group.getRoles().stream().filter(r -> !r.getId().equals(roleId)).toList()
        );
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
        if (user.getRoleGroups() == null) {
            user.setRoleGroups(new ArrayList<>());
        }
        if (user.getRoleGroups().stream().anyMatch(g -> g.getId().equals(group.getId()))) {
            throw new BadRequestException("User has already been assigned the role group.");
        }
        user.getRoleGroups().add(group);
        userRepository.save(user);
    }

    @Override
    public void revokeRoleGroupFromUser(UUID userId, UUID roleGroupId) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new NotFoundException("User with id %s not found",userId)
        );

        RoleGroup group = roleGroupRepository.findById(roleGroupId).orElseThrow(
                () -> new NotFoundException("Role Group with id %s not found",roleGroupId)
        );
        if (user.getRoleGroups() == null) {
            return;
        }
        user.setRoleGroups(
                user.getRoleGroups().stream().filter(g -> !g.getId().equals(group.getId())).toList()
        );
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
        if (group.getRoles() == null) {
            group.setRoles(new ArrayList<>());
        }
        group.getRoles().add(role);
        roleGroupRepository.save(group);

    }

    @Override
    public void removeRoleFromRoleGroup(UUID roleId, UUID roleGroupId) {
        Role role = roleRepository.findById(roleId).orElseThrow(
                () -> new NotFoundException("Role with id %s not found",roleId)
        );

        RoleGroup group = roleGroupRepository.findById(roleGroupId).orElseThrow(
                () -> new NotFoundException("Role Group with id %s not found",roleGroupId)
        );
        if (group.getRoles() == null) {
            return;
        }
        group.setRoles(
                group.getRoles().stream().filter(r -> !r.getId().equals(roleId)).toList()
        );
        roleGroupRepository.save(group);
    }

    @Override
    public void assignRoleGroupToUserGroup(UUID roleGroupId, UUID userGroupId) {
        UserGroup userGroup = userGroupRepository.findById(userGroupId).orElseThrow(
                () -> new NotFoundException("User Group with id %s not found",userGroupId)
        );

        RoleGroup roleGroup = roleGroupRepository.findById(roleGroupId).orElseThrow(
                () -> new NotFoundException("Role Group with id %s not found",roleGroupId)
        );

        if (userGroup.getRoleGroups() == null) {
            userGroup.setRoleGroups(new ArrayList<>());
        }

        if (userGroup.getRoleGroups().stream().anyMatch(rg -> rg.getId().equals(roleGroupId))) {
            throw new BadRequestException("Role Group is already assigned to the User Group");
        }

        userGroup.getRoleGroups().add(roleGroup);
        userGroupRepository.save(userGroup);
    }

    @Override
    public void revokeRoleGroupFromUserGroup(UUID roleGroupId, UUID userGroupId) {
        UserGroup userGroup = userGroupRepository.findById(userGroupId).orElseThrow(
                () -> new NotFoundException("User Group with id %s not found",userGroupId)
        );

        if(!roleGroupRepository.existsById(roleGroupId)) {
            throw new NotFoundException("Role Group with id %s not found", roleGroupId);
        }

        if (userGroup.getRoleGroups() == null) {
            return;
        }
        userGroup.setRoleGroups(
                userGroup.getRoleGroups().stream().filter(rg -> !rg.getId().equals(roleGroupId)).toList()
        );
        userGroupRepository.save(userGroup);
    }
}
