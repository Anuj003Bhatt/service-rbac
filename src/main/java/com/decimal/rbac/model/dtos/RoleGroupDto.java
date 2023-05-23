package com.decimal.rbac.model.dtos;

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
public class RoleGroupDto {
    private UUID id;
    private String name;
    private String description;
    private List<RoleDto> roles;
}
