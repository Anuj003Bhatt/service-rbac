package com.decimal.rbac.model.entities;

import com.decimal.rbac.model.dtos.RoleDto;
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

    @ManyToMany
    @JoinTable(
            name = "ROLE_PERMISSION_ASSOCIATIONS",
            joinColumns = @JoinColumn(name = "role_id"),
            inverseJoinColumns = @JoinColumn(name = "permission_id")
    )
    private List<Permission> rolePermissions;

    public RoleDto toDto() {
        return new RoleDto(
                id,
                name,
                description,
                rolePermissions.stream().map(Permission::toDto).toList()
        );
    }
}