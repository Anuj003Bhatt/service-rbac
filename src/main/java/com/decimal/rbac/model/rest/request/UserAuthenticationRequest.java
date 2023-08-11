package com.decimal.rbac.model.rest.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class UserAuthenticationRequest {
    @NotEmpty(message = "Username cannot be empty")
    private String userName;
    @NotEmpty(message = "Password cannot be empty")
    private String password;
}
