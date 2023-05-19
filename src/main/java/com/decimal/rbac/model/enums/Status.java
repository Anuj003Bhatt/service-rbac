package com.decimal.rbac.model.enums;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum Status {
    ACTIVE(true), INACTIVE(false);

    private final boolean isActive;

    Status(boolean isActive){
        this.isActive = isActive;
    }

    public boolean isActive() {
        return isActive;
    }

    public static Status of(boolean isActive) {
        return isActive?ACTIVE:INACTIVE;
    }

    @JsonCreator
    public static Status fromText(String source) {
        if (null == source || "".equals(source)) {
            throw new IllegalArgumentException("Status cannot be blank");
        }
        if (source.length() == 1) {
            return switch (source.charAt(0)) {
                case '0', 'I', 'i' -> INACTIVE;
                case '1', 'A', 'a' -> ACTIVE;
                default -> throw new IllegalArgumentException("Invalid user status");
            };
        } else {
            source = source.toUpperCase();
            if ("TRUE".equals(source) || "FALSE".equals(source)) {
                return of(Boolean.parseBoolean(source));
            }
            return valueOf(source);
        }
    }
}
