package com.decimal.rbac.model.entities;

import com.decimal.rbac.model.dtos.UserGroupDto;
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
@Table(name = "USER_GROUPS")
public class UserGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @UuidGenerator
    @Column(name = "user_group_id")
    private UUID id;

    @Column(name = "user_group_name")
    private String name;

    @Column(name = "description")
    private String description;

    @ManyToMany
    @JoinTable(
            name = "USER_GROUP_ROLE_ASSOCIATIONS",
            joinColumns = @JoinColumn(name = "user_group_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private List<Role> groupRoles;

    public UserGroupDto toDto() {
        return new UserGroupDto(
                id,
                name,
                description,
                (groupRoles != null) ? groupRoles.stream().map(Role::toDto).toList() : null
        );
    }


}
