package com.decimal.rbac.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
@Getter
public class NotFoundException extends RuntimeException {
    private String message;

    public NotFoundException() {
        super();
    }

    public NotFoundException(String message) {
        super(message);
        this.message = message;
    }

    public NotFoundException(String message, Object...args) {
        super(message.formatted(args));
        this.message = message.formatted(args);
    }
}
