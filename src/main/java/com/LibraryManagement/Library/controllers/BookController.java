package com.LibraryManagement.Library.controllers;

import com.LibraryManagement.Library.constraint.BookFilter;
import com.LibraryManagement.Library.constraint.FilterOperator;
import com.LibraryManagement.Library.dto.request.BookRequestDTO;
import com.LibraryManagement.Library.dto.response.BookResponseDTO;
import com.LibraryManagement.Library.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController {
    @Autowired
    private BookService bookService;
    @PostMapping
    public BookResponseDTO createBook(@RequestBody @Validated BookRequestDTO bookRequestDTO) {
        return bookService.createBook(bookRequestDTO);
    }

    @GetMapping("/filter")
    public List<BookResponseDTO> filterBooks(@RequestParam String filterBy, @RequestParam FilterOperator operator,
                                             @RequestParam String value) {
        return bookService.filterBooks(BookFilter.fromString(filterBy),operator,value);
    }

    @PostMapping("/updateSecurityAmount")
    public ResponseEntity<String> updateBookSecurityAmount(@RequestBody BookRequestDTO bookRequestDTO) {
        return bookService.updateBookSecurityAmount(bookRequestDTO);
    }

    @GetMapping
    public List<BookResponseDTO> getAllBooks() {
        return bookService.getAllBooks();
    }
}
