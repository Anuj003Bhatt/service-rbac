package com.decimal.rbac.repositories;

import com.decimal.rbac.model.enums.PermissionType;
import com.decimal.rbac.model.entities.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

/**
 * Spring Data Repository dealing with all actions related to permissions table.
 */
public interface PermissionRepository extends JpaRepository<Permission, UUID> {
    Optional<Permission> findByName(String name);

    /**
     * <a href="https://docs.spring.io/spring-framework/docs/4.3.14.RELEASE/spring-framework-reference/html/expressions.html">SpEL</a>
     * <p>
     * Collection Projection: .!
     * <p>
     * Collection Selection (filter): .?
     * @param accessTypes : The list of access types to query for.
     * @return permissions: List of all permission that have the access type of the input
     */
    @Query("SELECT p FROM Permission p WHERE p.accessType IN :accessTypes")
    Iterable<Permission> findByAccessTypeIn(@Param("accessTypes") Collection<PermissionType> accessTypes);
}
