package com.decimal.rbac.service;

import com.decimal.rbac.model.dtos.UserDto;
import com.decimal.rbac.model.rest.AddUser;
import com.decimal.rbac.model.dtos.ListUserResponse;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

/**
 * Service interface to list all functionalities that any user service implementation must provide.
 *
 */
public interface UserService {

    UserDto addUser(AddUser user);

    void disableUser(UUID id);

    void enableUser(UUID id);

    List<UserDto> listAllUsers();

    ListUserResponse getPaginated(Pageable pageable);

    UserDto findUserById(UUID id);

    UserDto findUserByUsername(String name);

}
