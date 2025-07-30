package com.LibraryManagement.Library.dto.request;

import com.LibraryManagement.Library.constraint.BookType;
import com.LibraryManagement.Library.model.Author;
import com.LibraryManagement.Library.model.Book;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookRequestDTO {
    @NotBlank(message = "title can't be blank")
    private String title;
    @NotNull
    @Positive
    private Double securityAmount;
    @NotNull(message = "book type can't be blank")
    private BookType bookType;
    @NotNull
    private String authorEmail;
    @NotNull
    private String authorName;

    public Book createBook() {
        Author author = Author.builder().email(authorEmail).name(authorName).build();
        return Book.builder().title(title)
                .bookType(bookType)
                .securityAmount(securityAmount)
                .author(author).build();
    }
}
