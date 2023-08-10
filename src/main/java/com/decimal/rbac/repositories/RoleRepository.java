package com.decimal.rbac.repositories;

import com.decimal.rbac.model.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RoleRepository extends JpaRepository<Role, UUID> {
    Iterable<Role> findByNameContaining(String name);
}
