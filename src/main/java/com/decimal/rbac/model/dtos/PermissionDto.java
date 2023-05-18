package com.decimal.rbac.model.dtos;

import com.decimal.rbac.model.entities.constants.PermissionType;
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
    private PermissionType access_type;
    private String description;
}
