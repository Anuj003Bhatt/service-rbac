package com.decimal.rbac.model.entities.constants;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

/**
 * This is converter that converts the enum received from the database for permissions into Java Enums and into
 * characters back again that the database recognizes.
 */
@Converter(autoApply = true)
public class PermissionTypeAttributeConverter implements AttributeConverter<PermissionType, Character> {

    /**
     * Java to DB
     *
     * @param permissionType : Convert the permission_type entity to the represented character in DB.
     * @return Character that corresponds to the DB enum
     */
    @Override
    public Character convertToDatabaseColumn(PermissionType permissionType) {
        return permissionType.getAccess();
    }

    /**
     * DB to Java
     *
     * @param character : The character received from the database.
     * @return PermissionType: Corresponding Java object for the character.
     */
    @Override
    public PermissionType convertToEntityAttribute(Character character) {
        return PermissionType.of(character);
    }
}
