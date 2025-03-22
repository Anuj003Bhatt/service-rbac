package com.decimal.rbac.model.security;

import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
@Builder
public class UserContext {
    private UUID userId;
    private List<String> roles;
}
