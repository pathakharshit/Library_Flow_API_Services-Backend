package com.LibraryManagement.Library.services;

import com.LibraryManagement.Library.constraint.BookFilter;
import com.LibraryManagement.Library.constraint.FilterOperator;
import com.LibraryManagement.Library.dto.request.BookRequestDTO;
import com.LibraryManagement.Library.dto.response.BookResponseDTO;
import com.LibraryManagement.Library.exception.BookException;
import com.LibraryManagement.Library.model.Author;
import com.LibraryManagement.Library.model.Book;
import com.LibraryManagement.Library.repositories.BookRepository;
import com.LibraryManagement.Library.services.factories.BookFilterFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private AuthorService authorService;
    @Autowired
    private BookFilterFactory bookFilterFactory;
    public BookResponseDTO createBook(BookRequestDTO bookRequestDTO) {
        Optional<Author> existingAuthor = authorService.getAuthorByEmail(bookRequestDTO.getAuthorEmail());
        Author author = existingAuthor.orElseGet(() -> {
            Author newAuthor = Author.builder()
                    .name(bookRequestDTO.getAuthorName())
                    .email(bookRequestDTO.getAuthorEmail())
                    .build();
            authorService.createAuthor(newAuthor);
            return newAuthor;
        });

        Optional<Book> existingBook = bookRepository.findByTitleAndAuthor(bookRequestDTO.getTitle(),author);
        Book book;
        if(existingBook.isPresent()) {
            book = existingBook.get();
            book.setNumberOfCopies(book.getNumberOfCopies() + 1);
        } else {
            book = bookRequestDTO.createBook();
            book.setAuthor(author);
            book.setNumberOfCopies(1);
        }
        book = bookRepository.save(book);
        return BookResponseDTO.builder().title(book.getTitle())
                .bookType(book.getBookType())
                .securityAmount(book.getSecurityAmount())
                .author(Author.builder()
                        .name(book.getAuthor().getName())
                        .email(book.getAuthor().getEmail())
                        .books(book.getAuthor().getBooks())
                        .build())
                .build();
    }

    public List<BookResponseDTO> filterBooks(BookFilter filterBy, FilterOperator operator,String value) {
        List<Book> books = bookFilterFactory.getFilter(filterBy).filterAllBooks(operator,value);
        if(books.isEmpty()) {
            throw new BookException("Book not exist");
        } else {
            return books.stream()
                    .map(book -> BookResponseDTO.builder()
                            .title(book.getTitle())
                            .bookType(book.getBookType())
                            .securityAmount(book.getSecurityAmount())
                            .author(Author.builder()
                                    .name(book.getAuthor().getName())
                                    .email(book.getAuthor().getEmail())
                                    .books(book.getAuthor().getBooks())
                                    .build())
                            .build())
                    .collect(Collectors.toList());
        }
    }

    public ResponseEntity<String> updateBookSecurityAmount(BookRequestDTO bookRequestDTO) {
        Author author = Author.builder().email(bookRequestDTO.getAuthorEmail()).name(bookRequestDTO.getAuthorName()).build();
        Optional<Book> existingBook = bookRepository.findByTitleAndAuthor(bookRequestDTO.getTitle(),author);
        Book book;
        if(existingBook.isPresent()) {
            book = existingBook.get();
            book.setSecurityAmount(bookRequestDTO.getSecurityAmount());
            bookRepository.save(book);
            return ResponseEntity.ok("Book successfully updated.");
        } else throw new BookException("Book doesn't exist");
    }

    public List<BookResponseDTO> getAllBooks() {
        List<Book> books = bookRepository.findAll();
        return books.stream()
                .map(book -> BookResponseDTO.builder()
                        .title(book.getTitle())
                        .bookType(book.getBookType())
                        .securityAmount(book.getSecurityAmount())
                        .author(book.getAuthor())
                        .build())
                .collect(Collectors.toList());
    }

    public List<BookResponseDTO> convertBookToBookResponseDTO(List<Book> books) {
        return books.stream()
                .map(book -> BookResponseDTO.builder()
                        .title(book.getTitle())
                        .bookType(book.getBookType())
                        .securityAmount(book.getSecurityAmount())
                        .author(book.getAuthor())
                        .build())
                .collect(Collectors.toList());
    }

    public BookResponseDTO convertBookToBookResponseDTO(Book book) {
        return BookResponseDTO.builder()
                        .title(book.getTitle())
                        .bookType(book.getBookType())
                        .securityAmount(book.getSecurityAmount())
                        .author(book.getAuthor())
                        .build();
    }

    public Book getBookByTitleAndAuthor(String title,Author author) {
        Optional<Book> existingBook = bookRepository.findByTitleAndAuthor(title,author);
        if(existingBook.isEmpty())
            throw new BookException("Book not found");
        return existingBook.get();
    }

    public void saveBook(Book book) {
        bookRepository.save(book);
    }
}
