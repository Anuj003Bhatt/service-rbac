package com.decimal.rbac.service.pg;

import com.decimal.rbac.exceptions.NotFoundException;
import com.decimal.rbac.model.entities.Role;
import com.decimal.rbac.model.entities.User;
import com.decimal.rbac.repositories.RoleRepository;
import com.decimal.rbac.repositories.UserRepository;
import com.decimal.rbac.service.RoleAssignmentService;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class RoleAssignmentServicePgImpl implements RoleAssignmentService {

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;

    public RoleAssignmentServicePgImpl(RoleRepository roleRepository, UserRepository userRepository) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void addUserToRole(UUID role_id, UUID user_id) {
        Role role = roleRepository.findById(role_id).orElseThrow(
                () -> new NotFoundException("Role with id %s not found",role_id)
        );

        User user = userRepository.findById(user_id).orElseThrow(
                () -> new NotFoundException("User with id %s not found",user_id)
        );
        if (!user.hasRole(role)) {
            user.addRole(role);
            userRepository.save(user);
        }
    }
}
