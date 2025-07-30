package com.LibraryManagement.Library.services.factories;

import com.LibraryManagement.Library.constraint.AuthorFilter;
import com.LibraryManagement.Library.services.filters.AuthorFilters.AuthorsFilterByEmail;
import com.LibraryManagement.Library.services.filters.AuthorFilters.AuthorsFilterByName;
import com.LibraryManagement.Library.services.filters.AuthorFilters.FilterAuthors;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class AuthorFilterFactory {
    Map<AuthorFilter,FilterAuthors> filterAuthorsMap = new HashMap<>();
    public AuthorFilterFactory(AuthorsFilterByName authorsFilterByName, AuthorsFilterByEmail authorsFilterByEmail) {
        filterAuthorsMap.put(AuthorFilter.NAME,authorsFilterByName);
        filterAuthorsMap.put(AuthorFilter.EMAIL,authorsFilterByEmail);
    }

    public FilterAuthors getFilter(AuthorFilter authorFilter) {
        return filterAuthorsMap.get(authorFilter);
    }
}
