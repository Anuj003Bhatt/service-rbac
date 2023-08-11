package com.decimal.rbac.model.entities;

import com.decimal.rbac.model.dtos.DtoBridge;
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
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Builder;
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
@Table(name = "ROLES", uniqueConstraints = {
        @UniqueConstraint(name = "unique_name_in_role", columnNames = {"name"})
})
@Builder
public class Role implements DtoBridge<RoleDto> {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    @UuidGenerator
    private UUID id;

    @Column(name = "name")
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

    @Override
    public RoleDto toDto() {
        return new RoleDto(
                id,
                name,
                description,
                (rolePermissions != null)?rolePermissions.stream().map(Permission::toDto).toList():null
        );
    }
}
