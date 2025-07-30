package com.LibraryManagement.Library.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthorResponseDTO {
    String email;
    String name;
    @JsonIgnoreProperties("author")
    private List<BookResponseDTO> books;
}
