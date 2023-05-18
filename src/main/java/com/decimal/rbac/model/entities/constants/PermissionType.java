package com.decimal.rbac.model.entities.constants;

/**
 * The ENUM replicates the enum entity in the database for the types of permissions that can be given.
 * <p>
 * C -> CREATE
 * <p>
 * R -> READ
 * <p>
 * U -> UPDATE
 * <p>
 * D -> DELETE
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

}
