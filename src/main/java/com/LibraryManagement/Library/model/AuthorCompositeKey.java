package com.LibraryManagement.Library.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthorCompositeKey {
    private String name;
    private String email;
}
