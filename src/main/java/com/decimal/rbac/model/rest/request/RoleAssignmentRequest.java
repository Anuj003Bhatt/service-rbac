package com.decimal.rbac.model.rest.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RoleAssignmentRequest {
    UUID roleId;
    UUID userId;
}
