package com.decimal.rbac.repositories;

import com.decimal.rbac.model.entities.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends CrudRepository<User, UUID> {
    Optional<User> findByUsername(String name);

    <T> Optional<T> findById(UUID id, Class<T> type);

    @Modifying
    @Query("update User u set u.status=com.decimal.rbac.model.enums.Status.INACTIVE where u.id = :id")
    int disableUser(UUID id);

    @Modifying
    @Query("update User u set u.status=com.decimal.rbac.model.enums.Status.ACTIVE where u.id = :id")
    int enableUser(UUID id);
}
