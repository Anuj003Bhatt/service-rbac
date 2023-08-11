package com.decimal.rbac.model.rest.request;

import com.decimal.rbac.model.entities.Role;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AddRole {
    @NotEmpty(message = "Role name cannot be empty")
    @Size(min = 5,max = 255, message = "Role name must be between 5-255 in length")
    private String name;

    @Size(max = 255, message = "Role description must be less than 255 characters")
    private String description;

    public Role toDataModelObject() {
        return Role
                .builder()
                .name(name)
                .description(description)
                .build();
    }
}
