package com.LibraryManagement.Library.services;

import com.LibraryManagement.Library.constraint.AuthorFilter;
import com.LibraryManagement.Library.constraint.FilterOperator;
import com.LibraryManagement.Library.dto.response.AuthorResponseDTO;
import com.LibraryManagement.Library.dto.response.AuthorResponseSummaryDTO;
import com.LibraryManagement.Library.exception.AuthorException;
import com.LibraryManagement.Library.model.Author;
import com.LibraryManagement.Library.repositories.AuthorRepository;
import com.LibraryManagement.Library.services.factories.AuthorFilterFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AuthorService {
    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private AuthorFilterFactory authorFilterFactory;
    public Optional<Author> getAuthorByEmail(String email) {
        return authorRepository.findAuthorByEmail(email);
    }

    public AuthorResponseDTO getAuthor(String email) {
        Optional<Author> existingAuthor = getAuthorByEmail(email);
        Author author;
        if(existingAuthor.isPresent()) {
            author = existingAuthor.get();
            return AuthorResponseDTO.builder()
                    .name(author.getName())
                    .email(author.getEmail())
                    .books(new BookService().convertBookToBookResponseDTO(author.getBooks()))
                    .build();
        }
        else throw new AuthorException("Author doesn't exist");
    }


    public void createAuthor(Author author) {
        authorRepository.save(author);
    }

    public List<AuthorResponseSummaryDTO> getAllAuthors() {
        List<Author> authors = authorRepository.findAll();
        return authors.stream()
                .map(author -> AuthorResponseSummaryDTO.builder()
                        .name(author.getName())
                        .email(author.getEmail())
                        .build())
                .collect(Collectors.toList());
    }

    public List<AuthorResponseSummaryDTO> filterAuthors(AuthorFilter filterBy, FilterOperator operator,String value) {
        List<Author> authors = authorFilterFactory.getFilter(filterBy).filterAllAuthors(operator,value);
        return authors.stream()
                .map(author -> AuthorResponseSummaryDTO.builder()
                        .name(author.getName())
                        .email(author.getEmail())
                        .build())
                .collect(Collectors.toList());
    }
}
