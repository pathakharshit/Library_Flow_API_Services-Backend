package com.LibraryManagement.Library.services.factories;

import com.LibraryManagement.Library.constraint.BookFilter;
import com.LibraryManagement.Library.services.filters.BookFilters.*;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class BookFilterFactory {
    Map<BookFilter, FilterBooks> filterBooksMap = new HashMap<>();

    public BookFilterFactory(BooksFilterByAuthor booksFilterByAuthor, BooksFilterByTypes booksFilterByTypes,
                             BooksFilterByNumberOfCopies booksFilterByNumberOfCopies,
                             BooksFilterBySecurityAmount booksFilterBySecurityAmount,
                             BooksFilterByTitle booksFilterByTitle) {
        filterBooksMap.put(BookFilter.BOOK_AUTHOR,booksFilterByAuthor);
        filterBooksMap.put(BookFilter.BOOK_TYPE,booksFilterByTypes);
        filterBooksMap.put(BookFilter.NUMBER_OF_COPIES,booksFilterByNumberOfCopies);
        filterBooksMap.put(BookFilter.SECURITY_AMOUNT,booksFilterBySecurityAmount);
        filterBooksMap.put(BookFilter.TITLE,booksFilterByTitle);
    }

    public FilterBooks getFilter(BookFilter bookFilter) {
        return filterBooksMap.get(bookFilter);
    }
}
