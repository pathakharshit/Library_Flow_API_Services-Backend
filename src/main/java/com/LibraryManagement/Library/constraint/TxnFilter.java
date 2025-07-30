package com.LibraryManagement.Library.constraint;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum TxnFilter {
    BOOK,
    USER,
    ISSUE_DATE,
    ACTUAL_RETURN_DATE,
    EXPECTED_RETURN_DATE;

    @JsonCreator
    public static TxnFilter fromString(String value) {
        for (TxnFilter type : values()) {
            if (type.name().equalsIgnoreCase(value)) {
                return type;
            }
        }
        throw new IllegalArgumentException("Invalid value for TxnFilter: " + value);
    }

    @JsonValue
    public String toValue() {
        return this.name();
    }
}
