package com.decimal.rbac.model.rest.request;

import com.decimal.rbac.model.entities.UserGroup;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class AddUserGroup {
    @NotEmpty(message = "User group name cannot be empty")
    @Size(min = 5,max = 255, message = "User group name must be between 5-255 in length")
    private String name;
    @Size(max = 255, message = "User group description must be less than 255 characters")
    private String description;

    public UserGroup toDataModelObject() {
        return UserGroup
                .builder()
                .name(name)
                .description(description)
                .build();
    }
}
