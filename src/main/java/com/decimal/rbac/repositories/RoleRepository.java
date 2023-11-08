package com.decimal.rbac.repositories;

import com.decimal.rbac.model.entities.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface RoleRepository extends JpaRepository<Role, UUID> {
    Page<Role> findByNameContaining(String name, Pageable pageable);

    List<Role> findAllByRoleGroups_Id(UUID id);
}
