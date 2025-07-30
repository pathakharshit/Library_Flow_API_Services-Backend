package com.LibraryManagement.Library.constraint;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum AuthorFilter {
    NAME,
    EMAIL;
    @JsonCreator
    public static AuthorFilter fromString(String value) {
        for (AuthorFilter type : values()) {
            if (type.name().equalsIgnoreCase(value)) {
                return type;
            }
        }
        throw new IllegalArgumentException("Invalid value for AuthorFilter");
    }

    @JsonValue
    public String toValue() {
        return this.name();
    }
}
