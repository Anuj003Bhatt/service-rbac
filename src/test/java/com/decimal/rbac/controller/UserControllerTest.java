package com.decimal.rbac.controller;

import com.decimal.rbac.exceptions.BadRequestException;
import com.decimal.rbac.exceptions.NotFoundException;
import com.decimal.rbac.model.dtos.UserDto;
import com.decimal.rbac.model.enums.Status;
import com.decimal.rbac.model.rest.request.AddUser;
import com.decimal.rbac.model.rest.response.ListUserResponse;
import com.decimal.rbac.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UsersController.class)
public class UserControllerTest {

    @MockBean
    private UserService userService;

    @Autowired
    private MockMvc mockMvc;

    List<UserDto> users;

    private final ObjectMapper mapper = new ObjectMapper();

    @BeforeEach
    void setup() {
        this.users = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            this.users.add(new UserDto(
                    "Name " + i,
                    UUID.randomUUID(),
                    Status.ACTIVE,
                    null,
                    null
            ));
        }
    }

    @Test
    void listAllUsersTest() throws Exception {
        ListUserResponse expected = new ListUserResponse();
        expected.setUsers(users);
        expected.setPagination(Map.of("total",10));
        when(userService.getPaginated(any())).thenReturn(expected);
        String response = mockMvc.perform(
                        get("/users")
                )
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        ListUserResponse listUsersResponse = mapper.readValue(response, ListUserResponse.class);
        verify(userService).getPaginated(any());
        assert listUsersResponse.getUsers().size() == users.size();
        assert listUsersResponse.getPagination().get("total") instanceof Integer;
        assert ((int)listUsersResponse.getPagination().get("total")) == users.size();
    }

    @Test
    void findUserByIdTest() throws Exception {
        UUID id = this.users.get(3).getId();
        when(userService.findUserById(any())).thenReturn(users.get(3));
        String response = mockMvc.perform(
                        get("/users/_byId/{id}",id)
                )
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        verify(userService).findUserById(id);
        UserDto expected = this.users.get(3);
        UserDto actual = mapper.readValue(response, UserDto.class);
        Assertions.assertEquals(expected.getUserName(), actual.getUserName());
        Assertions.assertEquals(expected.getId(), actual.getId());
        Assertions.assertEquals(expected.getStatus(), actual.getStatus());
    }

    @Test
    void findUserByIdNotFoundTest() throws Exception {
        UUID id = this.users.get(3).getId();
        when(userService.findUserById(any())).thenThrow(NotFoundException.class);
        mockMvc.perform(
                        get("/users/_byId/{id}",id)
                )
                .andExpect(status().isNotFound());
        verify(userService).findUserById(id);
    }

    @Test
    void findUserByUsernameTest() throws Exception {
        UUID id = this.users.get(3).getId();
        when(userService.findUserByUsername(any())).thenReturn(users.get(3));
        String response = mockMvc.perform(
                        get("/users/_byUsername/{id}",id)
                )
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        verify(userService).findUserByUsername(any());
        UserDto expected = this.users.get(3);
        UserDto actual = mapper.readValue(response, UserDto.class);
        Assertions.assertEquals(expected.getUserName(), actual.getUserName());
        Assertions.assertEquals(expected.getId(), actual.getId());
        Assertions.assertEquals(expected.getStatus(), actual.getStatus());
    }

    @Test
    void findUserByUsernameNotFoundTest() throws Exception {
        UUID id = this.users.get(3).getId();
        when(userService.findUserByUsername(any())).thenThrow(NotFoundException.class);
        mockMvc.perform(
                        get("/users/_byUsername/{id}",id)
                )
                .andExpect(status().isNotFound());
        verify(userService).findUserByUsername(any());
    }

    @Test
    void createUserTest() throws Exception {
        UserDto dto = this.users.get(3);
        AddUser addUserPayload = new AddUser(dto.getUserName(), "password", Status.ACTIVE);
        when(userService.addUser(any())).thenReturn(users.get(3));
        String response = mockMvc.perform(
                        post("/users").contentType(MediaType.APPLICATION_JSON)
                                .content(mapper.writeValueAsString(addUserPayload))
                )
                .andExpect(status().isCreated())
                .andReturn().getResponse().getContentAsString();
        verify(userService).addUser(any());
    }

    @Test
    void createUserInternalErrorTest() throws Exception {
        UserDto dto = this.users.get(3);
        AddUser addUserPayload = new AddUser(dto.getUserName(), "password", Status.ACTIVE);
        when(userService.addUser(any())).thenThrow(new BadRequestException("Integrity error"));
        mockMvc.perform(
                        post("/users").contentType(MediaType.APPLICATION_JSON)
                                .content(mapper.writeValueAsString(addUserPayload))
                )
                .andExpect(status().is4xxClientError());
        verify(userService).addUser(any());
    }

    @Test
    void disableUserTest() throws Exception {
        UUID id = this.users.get(3).getId();
        Map<String, String> expected = Map.of("message",String.format("User %s has been disabled", id));
        String response = mockMvc.perform(
                        patch("/users/{id}/disable",id)
                )
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        verify(userService).disableUser(any());
        Map actual = mapper.readValue(response, Map.class);
        assert actual.containsKey("message");
        assert expected.get("message").equals(actual.get("message"));
    }

    @Test
    void disableUserNotFoundTest() throws Exception {
        UUID id = this.users.get(3).getId();
        doThrow(new NotFoundException("No user found for id %s", id))
                .when(userService).disableUser(any());
        Map<String, String> expected = Map.of("message",String.format("User %s has been disabled", id));
        mockMvc.perform(
                        patch("/users/{id}/disable",id)
                )
                .andExpect(status().isNotFound());
        verify(userService).disableUser(any());
    }

    @Test
    void enableUserTest() throws Exception {
        UUID id = this.users.get(3).getId();
        Map<String, String> expected = Map.of("message",String.format("User %s has been enabled", id));
        String response = mockMvc.perform(
                        patch("/users/{id}/enable",id)
                )
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        verify(userService).enableUser(any());
        Map actual = mapper.readValue(response, Map.class);
        assert actual.containsKey("message");
        assert expected.get("message").equals(actual.get("message"));
    }

    @Test
    void enableUserNotFoundTest() throws Exception {
        UUID id = this.users.get(3).getId();
        doThrow(new NotFoundException("No user found for id %s", id))
                .when(userService).enableUser(any());
        Map<String, String> expected = Map.of("message",String.format("User %s has been disabled", id));
        mockMvc.perform(
                        patch("/users/{id}/enable",id)
                )
                .andExpect(status().isNotFound());
        verify(userService).enableUser(any());
    }
}
