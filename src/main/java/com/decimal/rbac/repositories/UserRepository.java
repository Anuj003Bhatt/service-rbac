package com.decimal.rbac.repositories;

import com.decimal.rbac.model.entities.User;
import com.decimal.rbac.model.enums.Status;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> findByUsername(String name);

    List<User> findAllByUserGroups_Id(UUID id);

    <T> Optional<T> findById(UUID id, Class<T> type);

    <T> Optional<T> findByIdAndStatus(UUID id, Status status, Class<T> type);

    @Modifying
    @Query("update User u set u.status=com.decimal.rbac.model.enums.Status.INACTIVE where u.id = :id")
    int disableUser(UUID id);

    @Modifying
    @Query("update User u set u.status=com.decimal.rbac.model.enums.Status.ACTIVE where u.id = :id")
    int enableUser(UUID id);
}
