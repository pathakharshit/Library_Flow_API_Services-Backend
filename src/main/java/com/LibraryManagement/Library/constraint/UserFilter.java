package com.LibraryManagement.Library.constraint;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum UserFilter {
    NAME,
    USER_TYPE;
    @JsonCreator
    public static UserFilter fromString(String value) {
        for (UserFilter type : values()) {
            if (type.name().equalsIgnoreCase(value)) {
                return type;
            }
        }
        throw new IllegalArgumentException("Invalid value for UserFilter");
    }

    @JsonValue
    public String toValue() {
        return this.name();
    }
}
