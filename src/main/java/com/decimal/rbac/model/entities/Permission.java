package com.decimal.rbac.model.entities;

import com.decimal.rbac.model.dtos.DtoBridge;
import com.decimal.rbac.model.enums.PermissionType;
import com.decimal.rbac.model.dtos.PermissionDto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@Entity
@Table(name = "permissions")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class Permission implements DtoBridge<PermissionDto> {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    @UuidGenerator
    private UUID id;

    @Column(name = "name")
    private String name;

    @Column(name = "access_type")
    private PermissionType accessType;

    @Column(name = "description")
    private String description;

    @Override
    public PermissionDto toDto(){
        return new PermissionDto(
                this.id,
                this.name,
                this.accessType,
                this.description
        );
    }

}
