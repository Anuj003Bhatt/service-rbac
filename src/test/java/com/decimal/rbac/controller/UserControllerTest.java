package com.decimal.rbac.controller;

import com.decimal.rbac.model.dtos.UserDto;
import com.decimal.rbac.model.entities.User;
import com.decimal.rbac.model.enums.Status;
import com.decimal.rbac.repositories.UserRepository;
import com.decimal.rbac.service.UserService;
import com.decimal.rbac.service.pg.UserServicePgImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class UserControllerTest {

    @Mock
    private UserRepository userRepository;

    List<User> users;

    @BeforeEach
    void setup() {
        this.users = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            this.users.add(new User(
                    UUID.randomUUID(),
                    "Name " + i,
                    Status.ACTIVE,
                    "Password " + i,
                    null,
                    null
            ));
        }
    }

    @Test
    void listAllUsersTest() {
        UserService userService = new UserServicePgImpl(userRepository);
        UsersController controller = new UsersController(userService);
        when(userRepository.findAll()).thenReturn(users);
        List<UserDto> users = controller.listAllUsers();
        verify(userRepository).findAll();
        assert users.size() == 10;
    }

    @Test
    void listAllUsersNoDataTest() {
        UserService userService = new UserServicePgImpl(userRepository);
        UsersController controller = new UsersController(userService);
        List<UserDto> users = controller.listAllUsers();
        verify(userRepository).findAll();
        assert users.size() == 0;
    }

    @Test
    void findUserByIdTest() {
        UUID id = this.users.get(3).getId();
        UserService userService = new UserServicePgImpl(userRepository);
        UsersController controller = new UsersController(userService);
        when(userRepository.findById(id)).thenReturn(Optional.of(users.get(3)));
        UserDto user = controller.findUserById(id);
        verify(userRepository).findById(id);
        UserDto expected = this.users.get(3).toDto();
        Assertions.assertEquals(expected.getUserName(), user.getUserName());
        Assertions.assertEquals(expected.getId(), user.getId());
        Assertions.assertEquals(expected.getStatus(), user.getStatus());
    }

    @Test
    void findUserByUsernameTest() {}

    @Test
    void createUserTest() {}

    @Test
    void disableUserTest() {}

    @Test
    void enableUserTest() {}
}
