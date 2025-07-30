package com.LibraryManagement.Library.services.filters.BookFilters;

import com.LibraryManagement.Library.constraint.FilterOperator;
import com.LibraryManagement.Library.model.Book;

import java.util.List;

public interface FilterBooks {
    List<Book> filterAllBooks(FilterOperator operator,String value);
}
