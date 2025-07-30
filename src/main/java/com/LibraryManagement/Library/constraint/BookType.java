package com.LibraryManagement.Library.constraint;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum BookType {
    HISTORY,
    FICTIONAL,
    PHYSICS,
    CHEMISTRY,
    MATHS;
    @JsonCreator
    public static BookType fromString(String value) {
        for (BookType type : values()) {
            if (type.name().equalsIgnoreCase(value)) {
                return type;
            }
        }
        throw new IllegalArgumentException("Invalid value for BookType: " + value);
    }

    @JsonValue
    public String toValue() {
        return this.name();
    }
}
