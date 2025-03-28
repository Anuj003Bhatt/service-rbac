package com.decimal.rbac.model.entities;

import com.decimal.rbac.model.dtos.DtoBridge;
import com.decimal.rbac.model.dtos.RoleGroupDto;
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
@Table(name = "ROLE_GROUPS")
@Builder
public class RoleGroup implements DtoBridge<RoleGroupDto> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @UuidGenerator
    @Column(name = "id")
    private UUID id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @ManyToMany
    @JoinTable(
            name = "role_group_assignments",
            joinColumns = @JoinColumn(name = "role_group_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private List<Role> roles;

    @Override
    public RoleGroupDto toDto() {
        return new RoleGroupDto(
                id,
                name,
                description,
                (roles != null) ? roles.stream().map(Role::toDto).toList() : null
        );
    }
}
