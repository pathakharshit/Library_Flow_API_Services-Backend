package com.LibraryManagement.Library.services.filters.AuthorFilters;

import com.LibraryManagement.Library.constraint.FilterOperator;
import com.LibraryManagement.Library.model.Author;

import java.util.List;

public interface FilterAuthors {
    public List<Author> filterAllAuthors(FilterOperator operator,String value);
}
