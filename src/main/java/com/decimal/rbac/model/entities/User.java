package com.decimal.rbac.model.entities;

import com.decimal.rbac.model.dtos.RoleDto;
import com.decimal.rbac.model.dtos.RoleGroupDto;
import com.decimal.rbac.model.dtos.UserDto;
import com.decimal.rbac.model.dtos.UserGroupDto;
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
@Table(name = "PLATFORM_USERS")
public class User {
    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    @UuidGenerator
    private UUID id;

    @Column(name = "username", unique = true)
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

    public UserDto toDto() {
        UserDto.UserDtoBuilder builder = new UserDto.UserDtoBuilder();
        return builder.withUserName(username)
                .withId(id)
                .withStatus(status)
                .havingEntityRoles(userRoles)
                .associatedUserGroups(userGroups)
                .associatedRoleGroups(roleGroups)
                .build();
    }

    public static class UserEntityBuilder {
        private UUID id;
        private String username;
        private Status status;
        private String password;
        private List<Role> userRoles;
        private List<UserGroup> userGroups;
        private List<RoleGroup> roleGroups;

        /**
         * Set username
         *
         * @return Current builder instance again
         */
        public UserEntityBuilder withUserName(String username) {
            this.username = username;
            return this;
        }

        /**
         * Set id
         *
         * @return Current builder instance again
         */
        public UserEntityBuilder withId(UUID id) {
            this.id = id;
            return this;
        }

        /**
         * Set status
         *
         * @return Current builder instance again
         */
        public UserEntityBuilder withStatus(Status status) {
            this.status = status;
            return this;
        }

        /**
         * Set password
         *
         * @return Current builder instance again
         */
        public UserEntityBuilder withPassword(String password) {
            this.password = password;
            return this;
        }

        /**
         * Set user roles
         * Please note while using both entity and dto function together
         * The last use will override the previous one.
         *
         * @return Current builder instance again
         */
        public UserEntityBuilder havingRoles(List<Role> roles) {
            this.userRoles = roles;
            return this;
        }

        /**
         * Set dto roles
         * Please note while using both entity and dto function together
         * The last use will override the previous one.
         *
         * @return Current builder instance again
         */
        public UserEntityBuilder havingDtoRoles(List<RoleDto> roles) {
            this.userRoles = (roles!=null)
                    ?roles.stream().map(RoleDto::toDataModelObject).toList()
                    :null;
            return this;
        }

        /**
         * Set user groups
         * Please note while using both entity and dto function together
         * The last use will override the previous one.
         *
         * @return Current builder instance again
         */
        public UserEntityBuilder associatedUserGroups(List<UserGroup> groups) {
            this.userGroups = groups;
            return this;
        }

        /**
         * Set role groups
         * Please note while using both entity and dto function together
         * The last use will override the previous one.
         *
         * @return Current builder instance again
         */
        public UserEntityBuilder associatedRoleGroups(List<RoleGroup> groups) {
            this.roleGroups = groups;
            return this;
        }

        public User build() {
            return new User(
              id,
              username,
              status,
              password,
              userRoles,
              userGroups,
              roleGroups
            );
        }
    }
}
