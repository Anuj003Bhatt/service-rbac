package com.decimal.rbac.service.pg;

import com.decimal.rbac.exceptions.NotFoundException;
import com.decimal.rbac.model.dtos.UserDto;
import com.decimal.rbac.model.entities.User;
import com.decimal.rbac.repositories.UserRepository;
import com.decimal.rbac.service.UserService;
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
