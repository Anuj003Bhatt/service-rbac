package com.decimal.rbac.model.entities;

import com.decimal.rbac.model.dtos.UserDto;
import com.decimal.rbac.model.enums.Status;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "PLATFORM_USERS")
public class User {
    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    @UuidGenerator
    private UUID id;

    @Column(name = "username")
    private String username;

    @Column(name = "status")
    private Status status;

    @Column(name = "password")
    private String password;

    @ManyToMany
    @JoinTable(
            name = "USER_ROLES",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private List<Role> userRoles;

    @ManyToMany
    @JoinTable(
            name = "USER_GROUP_USERS",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "user_group_id")
    )
    private List<UserGroup> userGroups;

    @ManyToMany
    @JoinTable(
            name = "USER_ROLE_GROUP_ASSOCIATIONS",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_group_id")
    )
    private List<RoleGroup> roleGroups;

    public Boolean hasRole(Role role) {
        if (userRoles == null || userRoles.isEmpty()) {
            return false;
        }
        return userRoles.stream().anyMatch(u -> u.getId() == role.getId());
    }

    public void addRole(Role role) {
        if (userRoles == null) {
            this.userRoles = new ArrayList<>();
        }
        this.userRoles.add(role);
    }

    public UserDto toDto() {
        return new UserDto(
                username,
                id,
                status,
                (userRoles != null) ? userRoles.stream().map(Role::toDto).toList() : null,
                (userGroups != null) ? userGroups.stream().map(UserGroup::toDto).toList() : null,
                (roleGroups != null) ? roleGroups.stream().map(RoleGroup::toDto).toList() : null
        );
    }
}
