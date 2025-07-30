package com.LibraryManagement.Library.constraint;

import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum FilterOperator {
    EQUALS("="),
    NOT_EQUALS("!="),
    LIKE("like"),
    GREATER_THAN(">"),
    LESS_THAN("<");

    private String value;
}
