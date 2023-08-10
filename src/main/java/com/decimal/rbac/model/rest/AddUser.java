package com.decimal.rbac.model.rest;

import com.decimal.rbac.exceptions.BadRequestException;
import com.decimal.rbac.model.entities.User;
import com.decimal.rbac.model.enums.Status;
import com.decimal.rbac.util.EncryptionUtil;
import com.fasterxml.jackson.annotation.JsonInclude;
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
    private String userName;
    private String password;
    private Status status = Status.ACTIVE;

    private void validate() {
        if (null == userName || "".equals(userName)) {
            throw new BadRequestException("Username cannot be blank");
        }
        if (userName.length() > 50) {
            throw new BadRequestException("Username too long");
        }

        if (null == password || "".equals(password)) {
            throw new BadRequestException("Password cannot be blank");
        }
        if (userName.length() > 30) {
            throw new BadRequestException("Password too long");
        }
    }

    public User toDataModelObject() {
        validate();
        User user = new User();
        user.setUsername(userName);
        user.setPassword(EncryptionUtil.encryptWithSalt(password));
        user.setStatus(status);
        return user;
    }
}
