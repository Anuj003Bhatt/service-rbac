package com.decimal.rbac.service.pg;

import com.decimal.rbac.exceptions.NotFoundException;
import com.decimal.rbac.model.dtos.ListUserResponse;
import com.decimal.rbac.model.dtos.UserDto;
import com.decimal.rbac.model.entities.User;
import com.decimal.rbac.model.enums.Status;
import com.decimal.rbac.model.projections.UserId;
import com.decimal.rbac.repositories.UserRepository;
import com.decimal.rbac.service.UserService;
import com.decimal.rbac.util.EncryptionUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
public class UserServiceTest {

    List<User> users;

    @BeforeEach
    void setup() {
        this.users = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            this.users.add(new User(
                    UUID.randomUUID(),
                    "Name " + i,
                    Status.ACTIVE,
                    EncryptionUtil.encryptWithSalt("Password " + i),
                    null,
                    null,
                    null
            ));
        }

    }

    @Mock
    private UserRepository userRepository;

    @Test
    void testUserGetsAll(){
        UserService userService = new UserServicePgImpl(userRepository);
        when(userRepository.findAll()).thenReturn(users);
        ListUserResponse users = userService.listAllUsers(Pageable.ofSize(20));
        verify(userRepository).findAll();
        assert users.getUsers().size() == 10;

    }

    @Test
    void testDisableUserNotFound(){
        UserService userService = new UserServicePgImpl(userRepository);
        Assertions.assertThrows(NotFoundException.class, () ->userService.disableUser(UUID.randomUUID()));
        verify(userRepository).findById(any(), any());
        verify(userRepository, never()).disableUser(any());
    }

    @Test
    void testDisableUser(){
        UserService userService = new UserServicePgImpl(userRepository);
        when(userRepository.findById(any(), any())).thenReturn(Optional.of(
                new UserId() {
                    @Override
                    public UUID getId() {
                        return users.get(0).getId();
                    }
                }
        ));
        userService.disableUser(UUID.randomUUID());
        verify(userRepository).findById(any(), any());
        verify(userRepository).disableUser(any());
    }

    @Test
    void testEnableUserNotFound(){
        UserService userService = new UserServicePgImpl(userRepository);
        Assertions.assertThrows(NotFoundException.class, () ->userService.disableUser(UUID.randomUUID()));
        verify(userRepository).findById(any(), any());
        verify(userRepository, never()).enableUser(any());
    }

    @Test
    void testEnableUser(){
        UserService userService = new UserServicePgImpl(userRepository);
        when(userRepository.findById(any(), any())).thenReturn(Optional.of(
                new UserId() {
                    @Override
                    public UUID getId() {
                        return users.get(0).getId();
                    }
                }
        ));
        userService.enableUser(UUID.randomUUID());
        verify(userRepository).findById(any(), any());
        verify(userRepository).enableUser(any());
    }

    @Test
    void findUserByIdTest() {
        UserService userService = new UserServicePgImpl(userRepository);
        when(userRepository.findById(any())).thenReturn(Optional.of(users.get(0)));
        UserDto user = userService.findUserById(users.get(0).getId());
        verify(userRepository).findById(any());
        UserDto expected = users.get(0).toDto();
        Assertions.assertEquals(expected.getUserName(), user.getUserName());
        Assertions.assertEquals(expected.getId(), user.getId());
        Assertions.assertEquals(expected.getStatus(), user.getStatus());
    }

    @Test
    void findUserByIdNotFoundTest() {
        UserService userService = new UserServicePgImpl(userRepository);
        Assertions.assertThrows(NotFoundException.class,
                () -> userService.findUserById(users.get(0).getId()));
        verify(userRepository).findById(any());
    }

    @Test
    void findUserByUsernameTest() {
        UserService userService = new UserServicePgImpl(userRepository);
        when(userRepository.findByUsername(any())).thenReturn(Optional.of(users.get(1)));
        UserDto user = userService.findUserByUsername(users.get(0).getUsername());
        verify(userRepository).findByUsername(any());
        UserDto expected = users.get(1).toDto();
        Assertions.assertEquals(expected.getUserName(), user.getUserName());
        Assertions.assertEquals(expected.getId(), user.getId());
        Assertions.assertEquals(expected.getStatus(), user.getStatus());
    }

    @Test
    void findUserByUsernameNotFoundTest() {
        UserService userService = new UserServicePgImpl(userRepository);
        Assertions.assertThrows(NotFoundException.class,
                () -> userService.findUserByUsername(users.get(0).getUsername()));
        verify(userRepository).findByUsername(any());
    }
}
