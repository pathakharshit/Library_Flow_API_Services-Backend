package com.LibraryManagement.Library.constraint;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum UserType {
    ADMIN,
    STUDENT;
    @JsonCreator
    public static UserType fromString(String value) {
        for (UserType type : values()) {
            if (type.name().equalsIgnoreCase(value)) {
                return type;
            }
        }
        throw new IllegalArgumentException("Invalid value for UserType");
    }

    @JsonValue
    public String toValue() {
        return this.name();
    }
}
