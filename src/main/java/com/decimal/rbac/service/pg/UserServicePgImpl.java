package com.decimal.rbac.service.pg;

import com.decimal.rbac.constants.IntegrityErrorUtil;
import com.decimal.rbac.exceptions.AuthenticationFailedException;
import com.decimal.rbac.exceptions.NotFoundException;
import com.decimal.rbac.model.dtos.BridgeUtil;
import com.decimal.rbac.model.dtos.UserDto;
import com.decimal.rbac.model.entities.User;
import com.decimal.rbac.model.enums.Status;
import com.decimal.rbac.model.projections.UserId;
import com.decimal.rbac.model.rest.request.AddUser;
import com.decimal.rbac.model.rest.response.ListResponse;
import com.decimal.rbac.repositories.UserRepository;
import com.decimal.rbac.service.UserService;
import com.decimal.rbac.util.EncryptionUtil;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class UserServicePgImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public UserDto addUser(AddUser user) {
        try {
            return userRepository.save(user.toDataModelObject()).toDto();
        } catch (DataIntegrityViolationException ex) {
            throw IntegrityErrorUtil.formatIntegrityExceptions(ex);
        }
    }

    @Override
    @Transactional
    public void disableUser(UUID id) {
        UserId user = userRepository.findByIdAndStatus(id, Status.ACTIVE, UserId.class).orElseThrow(
                () -> new NotFoundException(String.format("User with id %s not found or already disabled", id))
        );
        userRepository.disableUser(user.getId());
    }

    @Override
    @Transactional
    public void enableUser(UUID id) {
        UserId user = userRepository.findByIdAndStatus(id, Status.INACTIVE, UserId.class).orElseThrow(
                () -> new NotFoundException(String.format("User with id %s not found or already enabled", id))
        );
        userRepository.enableUser(user.getId());
    }

    @Override
    public ListResponse<UserDto> listAllUsers(Pageable pageable) {
        Page<User> users = userRepository.findAll(pageable);
        return BridgeUtil.buildPaginatedResponse(users);
    }

    @Override
    public ListResponse<UserDto> getPaginated(Pageable pageable) {
        Page<User> page = userRepository.findAll(pageable);
        return BridgeUtil.buildPaginatedResponse(page);
    }

    @Override
    public UserDto findUserById(UUID id) {
        return userRepository
                .findById(id)
                .orElseThrow(
                        () -> new NotFoundException(String.format("No user found for id %s", id))
                )
                .toDto();
    }

    @Override
    public UserDto findUserByUsername(String name) {
        return userRepository
                .findByUsername(name)
                .orElseThrow(
                        () -> new NotFoundException(String.format("User name %s does not exist", name))
                )
                .toDto();
    }

    @Override
    public UserDto authenticate(String username, String password) {
        User user = userRepository.findByUsername(username).orElseThrow(
                () -> new NotFoundException("No user found for the username: %s", username)
        );
        if(EncryptionUtil.verifyPassword(password, user.getPassword())){
            return user.toDto();
        } else {
            throw new AuthenticationFailedException("Invalid Username/password");
        }
    }
}
