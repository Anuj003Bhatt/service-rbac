package com.decimal.rbac.model.enums;

import com.fasterxml.jackson.annotation.JsonCreator;

/**
 * The ENUM replicates the enum entity in the database for the types of permissions that can be given.
 * <p> C -> CREATE
 * <p> R -> READ
 * <p> U -> UPDATE
 * <p> D -> DELETE
 */
public enum PermissionType {
    CREATE('C'),
    READ('R'),
    UPDATE('U'),
    DELETE('D');
    private final Character access;

    public Character getAccess() {
        return access;
    }

    PermissionType(char access) {
        this.access = access;
    }

    public static PermissionType of(char c) {
        return switch (c) {
            case 'C' -> CREATE;
            case 'R' -> READ;
            case 'U' -> UPDATE;
            case 'D' -> DELETE;
            default -> throw new IllegalArgumentException("Invalid access requested");
        };
    }

    @JsonCreator
    public static PermissionType fromText(String source) {
        if (null == source || "".equals(source)) {
            throw new IllegalArgumentException("Access type cannot be blank");
        }
        if (source.length() == 1) {
            return of(source.toUpperCase().charAt(0));
        } else {
            return valueOf(source.toUpperCase());
        }

    }
}
