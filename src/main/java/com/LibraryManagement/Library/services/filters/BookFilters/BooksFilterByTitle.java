package com.LibraryManagement.Library.services.filters.BookFilters;

import com.LibraryManagement.Library.constraint.FilterOperator;
import com.LibraryManagement.Library.model.Book;
import com.LibraryManagement.Library.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BooksFilterByTitle implements FilterBooks {
    @Autowired
    private BookRepository bookRepository;
    @Override
    public List<Book> filterAllBooks(FilterOperator operator, String value) {
        if(operator != FilterOperator.LIKE) {
            throw new IllegalArgumentException("Invalid operator for this filter");
        }
        return bookRepository.findByTitleContainingIgnoreCase(value);
    }
}
