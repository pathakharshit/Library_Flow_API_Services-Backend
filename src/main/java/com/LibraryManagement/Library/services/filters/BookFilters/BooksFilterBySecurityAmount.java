package com.LibraryManagement.Library.services.filters.BookFilters;

import com.LibraryManagement.Library.constraint.FilterOperator;
import com.LibraryManagement.Library.model.Book;
import com.LibraryManagement.Library.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class BooksFilterBySecurityAmount implements FilterBooks {
    @Autowired
    private BookRepository bookRepository;
    @Override
    public List<Book> filterAllBooks(FilterOperator operator, String value) {
        if(operator == FilterOperator.LIKE || operator == FilterOperator.NOT_EQUALS) {
            throw new IllegalArgumentException("Operator is not matching");
        }
        List<Book> books = bookRepository.findAll();
        List<Book> booksEqualsToValue = new ArrayList<>();
        List<Book> booksLessThanValue = new ArrayList<>();
        List<Book> bookGreaterThanValue = new ArrayList<>();
        int val = Integer.parseInt(value);
        for(Book book : books) {
            if(book.getSecurityAmount() == val) booksEqualsToValue.add(book);
            else if(book.getSecurityAmount() < val) booksLessThanValue.add(book);
            else bookGreaterThanValue.add(book);
        }

        if(operator == FilterOperator.EQUALS) {
            return booksEqualsToValue;
        }
        else if(operator == FilterOperator.LESS_THAN) {
            return booksLessThanValue;
        }
        else return bookGreaterThanValue;
    }
}
