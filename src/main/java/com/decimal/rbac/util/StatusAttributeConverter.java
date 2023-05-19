package com.decimal.rbac.util;

import com.decimal.rbac.model.enums.Status;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

/**
 * This is converter that converts the enum received from the database for user status being active or in-active
 * into Java Enums and back from Java enum to boolean for the database recognizes.
 */
@Converter(autoApply = true)
public class StatusAttributeConverter implements AttributeConverter<Status, Boolean> {

    /**
     * Java to DB
     *
     * @param status : Java enum for user status
     * @return Boolean: For user status in database
     */
    @Override
    public Boolean convertToDatabaseColumn(Status status) {
        return status.isActive();
    }

    /**
     * DB to Java
     *
     * @param aBoolean : DB boolean for user status
     * @return Status Java enum representation of the boolean status
     */
    @Override
    public Status convertToEntityAttribute(Boolean aBoolean) {
        return Status.of(aBoolean);
    }
}
