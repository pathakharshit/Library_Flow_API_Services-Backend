package com.LibraryManagement.Library.services.filters.AuthorFilters;

import com.LibraryManagement.Library.constraint.FilterOperator;
import com.LibraryManagement.Library.model.Author;
import com.LibraryManagement.Library.repositories.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AuthorsFilterByName implements FilterAuthors {
    @Autowired
    private AuthorRepository authorRepository;
    @Override
    public List<Author> filterAllAuthors(FilterOperator operator, String value) {
        if(operator != FilterOperator.EQUALS) {
            throw new IllegalArgumentException("Operator doesn't match for this filter type");
        } else {
            return authorRepository.findByNameContainingIgnoreCase(value);
        }
    }
}
