package com.decimal.rbac.model.entities;

import com.decimal.rbac.model.entities.constants.PermissionType;
import com.decimal.rbac.model.dtos.PermissionDto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "permissions")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Permission {
    @Id
    @Column(name = "permission_id")
    private UUID id;

    @Column(name = "permission_name")
    private String name;

    @Column(name = "access_type")
    private PermissionType accessType;

    @Column(name = "description")
    private String description;

    public PermissionDto toDto(){
        return new PermissionDto(
                this.id,
                this.name,
                this.accessType,
                this.description
        );
    }

}
