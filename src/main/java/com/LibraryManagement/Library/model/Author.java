package com.LibraryManagement.Library.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@IdClass(AuthorCompositeKey.class)
public class Author {
    @Id
    private String name;
    @Id
    @Column(unique = true,nullable = false)
    private String email;
    @OneToMany(mappedBy = "author")
    @JsonManagedReference
    private List<Book> books;
}
