package com.decimal.rbac.constants;

import com.decimal.rbac.exceptions.BadRequestException;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.HashMap;
import java.util.Map;

public abstract class IntegrityErrorUtil {
    public static final Map<String, String> INTEGRITY_CONSTRAINT_VIOLATION = new HashMap<>(){{
       put("platform_users_username_key","Duplicate username");
       put("unique_name_in_role", "Duplicate name for role");
    }};

    public static RuntimeException formatIntegrityExceptions(DataIntegrityViolationException ex) {
        String constraintName = ((ConstraintViolationException) ex.getCause()).getConstraintName();
        if (IntegrityErrorUtil.INTEGRITY_CONSTRAINT_VIOLATION.containsKey(constraintName)) {
            return new BadRequestException(IntegrityErrorUtil.INTEGRITY_CONSTRAINT_VIOLATION.get(constraintName));
        } else {
            return ex;
        }
    }
}
