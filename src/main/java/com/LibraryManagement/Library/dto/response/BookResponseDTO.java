package com.LibraryManagement.Library.dto.response;

import com.LibraryManagement.Library.constraint.BookType;
import com.LibraryManagement.Library.model.Author;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookResponseDTO {
    private String title;
    private BookType bookType;
    private Double securityAmount;
    @JsonIgnoreProperties(value = "books")
    private Author author;
}
