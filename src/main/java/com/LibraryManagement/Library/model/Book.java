package com.LibraryManagement.Library.model;

import com.LibraryManagement.Library.constraint.BookType;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Positive;
import lombok.*;

import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private String title;
    @Enumerated(value = EnumType.STRING)
    private BookType bookType;
    private Integer numberOfCopies;

    @Positive
    private Double securityAmount;
    @ManyToMany(mappedBy = "books")
    private List<User> users;

    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "author_name",referencedColumnName = "name"),
            @JoinColumn(name = "author_email",referencedColumnName = "email")
    })
    @JsonBackReference
    private Author author;

    @OneToMany(mappedBy = "book")
    @JsonManagedReference
    private List<Txns> txns;
}
