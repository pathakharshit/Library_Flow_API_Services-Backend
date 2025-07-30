package com.LibraryManagement.Library.repositories;

import com.LibraryManagement.Library.model.Author;
import com.LibraryManagement.Library.model.AuthorCompositeKey;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AuthorRepository extends JpaRepository<Author, AuthorCompositeKey> {
    Optional<Author> findAuthorByEmail(String email);
    List<Author> findByNameContainingIgnoreCase(String name);
    List<Author> findByEmailContainingIgnoreCase(String email);
}
