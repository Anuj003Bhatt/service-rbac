package com.decimal.rbac.model.entities.constants;

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
}
