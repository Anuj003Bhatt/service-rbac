package com.decimal.rbac.model.rest.request;

import com.decimal.rbac.exceptions.BadRequestException;
import com.decimal.rbac.model.entities.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AddRole {
    private String name;
    private String description;

    private void validate() {
        if (null == name || "".equals(name) || name.length() > 50) {
            throw new BadRequestException("Invalid name in request %s", name);
        }

        if (null == description || "".equals(description) || description.length() > 500) {
            throw new BadRequestException("Role description too long: %s", description);
        }
    }

    public Role toDataModelObject() {
        return new Role(null,name,description,null,null,null);
    }
}
