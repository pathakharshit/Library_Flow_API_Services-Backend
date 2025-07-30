package com.LibraryManagement.Library.dto.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthorResponseSummaryDTO {
    String email;
    String name;
}
