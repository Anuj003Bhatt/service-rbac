package com.decimal.rbac.repositories;

import com.decimal.rbac.model.entities.RoleGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface RoleGroupRepository extends JpaRepository<RoleGroup, UUID> {
}
