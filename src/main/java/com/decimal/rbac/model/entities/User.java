package com.decimal.rbac.model.entities;

import com.decimal.rbac.model.dtos.DtoBridge;
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
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.UuidGenerator;
import org.hibernate.type.SqlTypes;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(
        name = "PLATFORM_USERS",
        uniqueConstraints = {
                @UniqueConstraint(name = "platform_users_username_key",  columnNames = {"username"})
        }
)
@Builder
public class User implements DtoBridge<UserDto> {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    @UuidGenerator
    private UUID id;

    @Column(name = "username")
    private String username;

    @Column(name = "status")
    private Status status;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "password", nullable = false)
    private Map<String, String> password;

    @ManyToMany
    @JoinTable(
            name = "USER_ROLES",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private List<Role> userRoles;

    @ManyToMany(mappedBy = "users")
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

    @Override
    public UserDto toDto() {

        UserDto.UserDtoBuilder builder = UserDto.builder()
                .userName(username)
                .id(id)
                .status(status);
        if (null != userRoles) {
            builder.roles(userRoles.stream().map(Role::toDto).toList());
        }
        if (null != userGroups) {
            builder.userGroups(userGroups.stream().map(g -> Map.of("id", g.getId().toString(), "name", g.getName())).toList());
        }
        if (null != roleGroups) {
            builder.roleGroups(roleGroups.stream().map(g -> Map.of("id", g.getId().toString(), "name", g.getName())).toList());
        }
        return builder.build();
    }
}
