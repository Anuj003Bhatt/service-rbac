package com.decimal.rbac.model.dtos;

import com.decimal.rbac.model.entities.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RoleDto {
    private UUID id;
    private String name;
    private String description;
    private List<PermissionDto> rolePermissions;

    public Role toDataModelObject() {
        Role.RoleEntityBuilder builder = new Role.RoleEntityBuilder();
        return builder.withId(id)
                .withName(name)
                .description(description)
                .associatedEntityRolePermissionsFromDto(rolePermissions)
                .build();
    }
}
