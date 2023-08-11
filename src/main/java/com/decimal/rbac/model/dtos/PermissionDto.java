package com.decimal.rbac.model.dtos;

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
}
