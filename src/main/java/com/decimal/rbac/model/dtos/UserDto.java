package com.decimal.rbac.model.dtos;

import com.decimal.rbac.model.entities.Role;
import com.decimal.rbac.model.entities.RoleGroup;
import com.decimal.rbac.model.entities.User;
import com.decimal.rbac.model.entities.UserGroup;
import com.decimal.rbac.model.enums.Status;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "User")
public class UserDto {
    private String userName;
    private UUID id;
    private Status status;
    private List<RoleDto> roles;
    private List<Map<String, String>> userGroups;
    private List<Map<String, String>> roleGroups;

    public User toDataModelObject() {
        User.UserEntityBuilder builder = new User.UserEntityBuilder();
        return builder
                .withId(id)
                .withUserName(userName)
                .withStatus(status)
                .havingDtoRoles(roles)
                .build();
    }

    /**
     * Builder for UserDto to validate and create the resource at one place with all passed parameters.
     */
    public static class UserDtoBuilder {
        private String userName = null;
        private UUID id = null;
        private Status status = null;
        private List<RoleDto> roles = null;
        private List<Map<String, String>> userGroups = null;
        private List<Map<String, String>> roleGroups = null;

        /**
         * Set username
         *
         * @return Current builder instance again
         */
        public UserDtoBuilder withUserName(String username) {
            this.userName = username;
            return this;
        }

        /**
         * Set id
         *
         * @return Current builder instance again
         */
        public UserDtoBuilder withId(UUID id) {
            this.id = id;
            return this;
        }

        /**
         * Set status
         *
         * @return Current builder instance again
         */
        public UserDtoBuilder withStatus(Status status) {
            this.status = status;
            return this;
        }

        /**
         * Set roles
         * Please note while using both entity and dto function together
         * The last use will override the previous one.
         *
         * @return Current builder instance again
         */
        public UserDtoBuilder havingRoles(List<RoleDto> roles) {
            this.roles = roles;
            return this;
        }

        /**
         * Set entity roles
         * Please note while using both entity and dto function together
         * The last use will override the previous one.
         *
         * @return Current builder instance again
         */
        public UserDtoBuilder havingEntityRoles(List<Role> roles) {
            this.roles = (roles != null)?roles.stream().map(Role::toDto).toList():null;
            return this;
        }

        /**
         * Set user groups
         * Please note while using both entity and dto function together
         * The last use will override the previous one.
         *
         * @return Current builder instance again
         */
        public UserDtoBuilder associatedUserGroups(List<UserGroup> groups) {
            if (groups == null || groups.isEmpty()) {
                return this;
            }
            this.userGroups = groups.stream()
                    .map(
                            g -> Map.of(
                                    "id", g.getId().toString(),
                                    "name", g.getName()
                            )
                    )
                    .collect(Collectors.toList());
            return this;
        }

        /**
         * Set role groups
         * Please note while using both entity and dto function together
         * The last use will override the previous one.
         *
         * @return Current builder instance again
         */
        public UserDtoBuilder associatedRoleGroups(List<RoleGroup> groups) {
            if (groups == null || groups.isEmpty()) {
                return this;
            }
            this.roleGroups = groups.stream()
                    .map(
                            g -> Map.of(
                                    "id", g.getId().toString(),
                                    "name", g.getName()
                            )
                    )
                    .collect(Collectors.toList());;
            return this;
        }

        /**
         * Build DTO instance
         *
         * @return UserDto object
         */
        public UserDto build() {
            return new UserDto(
                    userName,
                    id,
                    status,
                    roles,
                    userGroups,
                    roleGroups
            );
        }
    }
}
