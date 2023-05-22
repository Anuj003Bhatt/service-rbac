package com.decimal.rbac.repositories;

import com.decimal.rbac.model.entities.Role;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface RoleRepository extends CrudRepository<Role, UUID> {
    Iterable<Role> findByNameContaining(String name);
}
