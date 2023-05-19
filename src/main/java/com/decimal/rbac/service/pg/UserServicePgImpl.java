package com.decimal.rbac.service.pg;

import com.decimal.rbac.constants.ErrorMessage;
import com.decimal.rbac.exceptions.BadRequestException;
import com.decimal.rbac.exceptions.NotFoundException;
import com.decimal.rbac.model.dtos.UserDto;
import com.decimal.rbac.model.entities.User;
import com.decimal.rbac.model.projections.UserId;
import com.decimal.rbac.model.rest.AddUser;
import com.decimal.rbac.repositories.UserRepository;
import com.decimal.rbac.service.UserService;
import jakarta.transaction.Transactional;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.StreamSupport;

@Service
public class UserServicePgImpl implements UserService {

    private final UserRepository userRepository;

    public UserServicePgImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public UserDto addUser(AddUser user) {
        try {
            return userRepository.save(user.toDataModelObject()).toDto();
        } catch (DataIntegrityViolationException ex) {
            String constraintName = ((ConstraintViolationException) ex.getCause()).getConstraintName();
            if (ErrorMessage.INTEGRITY_CONSTRAINT_VIOLATION.containsKey(constraintName)) {
                throw new BadRequestException(ErrorMessage.INTEGRITY_CONSTRAINT_VIOLATION.get(constraintName));
            } else {
                throw ex;
            }

        }

    }

    @Override
    @Transactional
    public void disableUser(UUID id) {
        UserId user = userRepository.findById(id, UserId.class).orElseThrow(
                () -> new BadRequestException("No user found for id %s", id)
        );
        userRepository.disableUser(user.getId());
    }

    @Override
    @Transactional
    public void enableUser(UUID id) {
        UserId user = userRepository.findById(id, UserId.class).orElseThrow(
                () -> new BadRequestException("No user found for id %s", id)
        );
        userRepository.enableUser(user.getId());
    }

    @Override
    public List<UserDto> listAllUsers() {
        return StreamSupport
                .stream(userRepository.findAll().spliterator(), false)
                .map(User::toDto)
                .toList();
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
}
