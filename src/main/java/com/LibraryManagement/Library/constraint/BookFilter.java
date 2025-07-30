package com.LibraryManagement.Library.constraint;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum BookFilter {
    BOOK_TYPE,
    BOOK_AUTHOR,
    NUMBER_OF_COPIES,
    SECURITY_AMOUNT,
    TITLE;
    @JsonCreator
    public static BookFilter fromString(String value) {
        for (BookFilter type : values()) {
            if (type.name().equalsIgnoreCase(value)) {
                return type;
            }
        }
        throw new IllegalArgumentException("Invalid value for BookFilter");
    }

    @JsonValue
    public String toValue() {
        return this.name();
    }
}
