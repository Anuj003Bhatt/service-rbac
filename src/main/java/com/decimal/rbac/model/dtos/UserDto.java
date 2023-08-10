package com.decimal.rbac.model.dtos;

import com.decimal.rbac.model.entities.User;
import com.decimal.rbac.model.enums.Status;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "User")
@Builder
public class UserDto {
    private String userName;
    private UUID id;
    private Status status;
    private List<RoleDto> roles;
    private List<Map<String, String>> userGroups;
    private List<Map<String, String>> roleGroups;

    public User toDataModelObject() {

        return User.builder().id(id)
                .username(userName)
                .status(status)
                .userRoles(roles.stream().map(RoleDto::toDataModelObject).toList())
                .build();
    }
}
