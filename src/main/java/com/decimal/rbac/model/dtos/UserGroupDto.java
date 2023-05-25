package com.decimal.rbac.model.dtos;

import com.decimal.rbac.model.entities.UserGroup;
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
public class UserGroupDto {
    private UUID id;
    private String name;
    private String description;
    private List<RoleDto> groupRoles;

    public UserGroup toDataModelObject() {
        return new UserGroup(
                id,
                name,
                description,
                null,
                (groupRoles!=null)?groupRoles.stream().map(RoleDto::toDataModelObject).toList():null
        );
    }
}
