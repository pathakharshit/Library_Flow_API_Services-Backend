package com.LibraryManagement.Library.repositories;

import com.LibraryManagement.Library.constraint.BookType;
import com.LibraryManagement.Library.model.Author;
import com.LibraryManagement.Library.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book,Integer> {
    Optional<Book> findByTitleAndAuthor(String title, Author author);
    List<Book> findByAuthorName(String name);
    List<Book> findByBookType(BookType bookType);
    List<Book> findByTitleContainingIgnoreCase(String keyword);

}
