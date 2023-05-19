package com.decimal.rbac.constants;

import java.util.HashMap;
import java.util.Map;

public interface ErrorMessage {
    public static final Map<String, String> INTEGRITY_CONSTRAINT_VIOLATION = new HashMap<>(){{
       put("platform_users_username_key","Duplicate username");
    }};
}
