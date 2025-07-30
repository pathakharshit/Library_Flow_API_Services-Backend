package com.LibraryManagement.Library.model;
import com.LibraryManagement.Library.constraint.UserType;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(unique = true,nullable = false)
    private String email;
    private String name;
    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    private UserType userType;

    @ManyToMany
    @JoinTable(
            name = "users_books",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "book_id")
    )
    private List<Book> books;
    @OneToMany(mappedBy = "user")
    private List<Txns> txns;
}