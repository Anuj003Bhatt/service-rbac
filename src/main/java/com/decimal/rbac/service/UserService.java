package com.decimal.rbac.service;

import com.decimal.rbac.model.dtos.UserDto;

import java.util.List;
import java.util.UUID;

public interface UserService {

    List<UserDto> listAllUsers();

    UserDto findUserById(UUID id);

    UserDto findUserByUsername(String name);

}
