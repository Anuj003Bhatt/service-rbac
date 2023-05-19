package com.decimal.rbac.model.dtos;

import com.decimal.rbac.exceptions.BadRequestException;
import com.decimal.rbac.model.entities.Permission;
import com.decimal.rbac.model.enums.PermissionType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PermissionDto {
    private UUID id;
    private String name;
    private PermissionType accessType;
    private String description;

    private void validate() {
        if (null == name || "".equals(name) || name.length() > 50) {
            throw new BadRequestException("Invalid permission name %s", name);
        }
        if (null != description && description.length() > 500) {
            throw new BadRequestException("Description too long for permission");
        }
    }

    public Permission toDataModelObject(){
        validate();
        Permission permission = new Permission();
        permission.setName(name);
        permission.setDescription(description);
        permission.setAccessType(accessType);
        return permission;
    }
}
