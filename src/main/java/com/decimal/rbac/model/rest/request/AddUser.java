package com.decimal.rbac.model.rest.request;

import com.decimal.rbac.model.entities.User;
import com.decimal.rbac.model.enums.Status;
import com.decimal.rbac.util.EncryptionUtil;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AddUser {
    @NotEmpty(message = "Username cannot be empty")
    @Size(max = 50, message = "Username must be less than 50 characters")
    private String userName;

    @NotEmpty(message = "password cannot be empty")
    @Size(min = 8, max = 50, message = "Password must be between 8-50 characters")
    private String password;

    @Email(message = "Email is not valid", regexp = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")
    @NotEmpty(message = "Email cannot be empty")
    private String email;

    @NotEmpty(message = "Phone cannot be empty")
    @Pattern(regexp="(^$|[0-9]{10})", message = "Invalid Phone number")
    private String phone;

    private Status status = Status.ACTIVE;

    public User toDataModelObject() {
        return User.builder()
                .username(userName)
                .email(email)
                .phone(phone)
                .password(EncryptionUtil.encryptWithSalt(password))
                .status(status)
                .build();
    }
}
