package com.decimal.rbac.model.dtos;

import com.decimal.rbac.model.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private String userName;
    private UUID id;
    private Status status;
    private List<RoleDto> roles;
    private List<UserGroupDto> userGroups;
}