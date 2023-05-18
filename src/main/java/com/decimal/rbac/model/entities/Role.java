package com.decimal.rbac.model.entities;

import com.decimal.rbac.model.dtos.RoleDto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    private UUID id;

    @Column(name = "role_name")
    private String name;

    @Column(name = "description")
    private String description;

    @ManyToMany(mappedBy = "userRoles")
    private List<User> users;

    public RoleDto toDto() {
        return new RoleDto(
                id,
                name,
                description
        );
    }
}
