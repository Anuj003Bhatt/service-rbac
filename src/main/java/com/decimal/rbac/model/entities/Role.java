package com.decimal.rbac.model.entities;

import com.decimal.rbac.model.dtos.PermissionDto;
import com.decimal.rbac.model.dtos.RoleDto;
import com.decimal.rbac.model.dtos.RoleGroupDto;
import com.decimal.rbac.model.dtos.UserDto;
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

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ROLES")
public class Role {
    @Id
    @Column(name = "role_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    @UuidGenerator
    private UUID id;

    @Column(name = "role_name")
    private String name;

    @Column(name = "description")
    private String description;

    @ManyToMany(mappedBy = "userRoles")
    private List<User> users;

    @ManyToMany(mappedBy = "roles")
    private List<RoleGroup> roleGroups;

    @ManyToMany
    @JoinTable(
            name = "ROLE_PERMISSION_ASSOCIATIONS",
            joinColumns = @JoinColumn(name = "role_id"),
            inverseJoinColumns = @JoinColumn(name = "permission_id")
    )
    private List<Permission> rolePermissions;

    public Boolean hasUser(User user) {
        if (users == null || users.isEmpty()) {
            return false;
        }
        return users.stream().anyMatch(u -> u.getId() == user.getId());
    }

    public RoleDto toDto() {
        return new RoleDto(
                id,
                name,
                description,
                (rolePermissions != null)?rolePermissions.stream().map(Permission::toDto).toList():null
        );
    }

    public static class RoleEntityBuilder{
        private UUID id;
        private String name;
        private String description;

        // not saved via role in DB
        private List<User> users;
        // not saved via role in DB
        private List<RoleGroup> roleGroups;
        private List<Permission> rolePermissions;

        /**
         * Set role ID
         * @return Current builder instance again
         */
        public RoleEntityBuilder withId(UUID id) {
            this.id = id;
            return this;
        }

        /**
         * Set role name
         * @return Current builder instance again
         */
        public RoleEntityBuilder withName(String name) {
            this.name = name;
            return this;
        }

        /**
         * Set role description
         * @return Current builder instance again
         */
        public RoleEntityBuilder description(String description) {
            this.description = description;
            return this;
        }

        /**
         * Set entity role users
         * Please note while using both entity and dto function together
         * The last use will override the previous one.
         *
         * @return Current builder instance again
         */
        public RoleEntityBuilder usersEntityWithRole(List<User> users) {
            this.users = users;
            return this;
        }

        /**
         * Set dto role users
         * Please note while using both entity and dto function together
         * The last use will override the previous one.
         *
         * @return Current builder instance again
         */
        public RoleEntityBuilder usersEntityWithRoleFromDto(List<UserDto> users) {
            this.users = (users!= null)
                    ?users.stream().map(UserDto::toDataModelObject).toList()
                    :null;
            return this;
        }

        /**
         * Set entity role groups
         * Please note while using both entity and dto function together
         * The last use will override the previous one.
         *
         * @return Current builder instance again
         */
        public RoleEntityBuilder associatedEntityRoleGroups(List<RoleGroup> groups) {
            this.roleGroups = groups;
            return this;
        }

        /**
         * Set dto role groups
         * Please note while using both entity and dto function together
         * The last use will override the previous one.
         *
         * @return Current builder instance again
         */
        public RoleEntityBuilder associatedEntityRoleGroupsFromDto(List<RoleGroupDto> groups) {
            this.roleGroups = (groups!= null)
                    ?groups.stream().map(RoleGroupDto::toDataModelObject).toList()
                    :null;
            return this;
        }

        /**
         * Set entity role permissions
         * Please note while using both entity and dto function together
         * The last use will override the previous one.
         *
         * @return Current builder instance again
         */
        public RoleEntityBuilder associatedEntityRolePermissions(List<Permission> permissions) {
            this.rolePermissions = permissions;
            return this;
        }

        /**
         * Set dto role permissions
         * Please note while using both entity and dto function together
         * The last use will override the previous one.
         *
         * @return Current builder instance again
         */
        public RoleEntityBuilder associatedEntityRolePermissionsFromDto(List<PermissionDto> permissions) {
            this.rolePermissions = (permissions!= null)
                    ?permissions.stream().map(PermissionDto::toDataModelObject).toList()
                    :null;
            return this;
        }

        public Role build() {
            return new Role(
                    id,
                    name,
                    description,
                    users,
                    roleGroups,
                    rolePermissions
            );
        }
    }
}
