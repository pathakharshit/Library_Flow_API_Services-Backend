package com.LibraryManagement.Library.controllers;

import com.LibraryManagement.Library.constraint.AuthorFilter;
import com.LibraryManagement.Library.constraint.FilterOperator;
import com.LibraryManagement.Library.dto.response.AuthorResponseDTO;
import com.LibraryManagement.Library.dto.response.AuthorResponseSummaryDTO;
import com.LibraryManagement.Library.services.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/authors")
public class AuthorController {
    @Autowired
    private AuthorService authorService;
    @GetMapping
    public List<AuthorResponseSummaryDTO> getAllAuthors() {
        return authorService.getAllAuthors();
    }

    @GetMapping("/{email}")
    public AuthorResponseDTO getAuthorByEmail(@PathVariable String email) {
        return authorService.getAuthor(email);
    }

    @GetMapping("/filter")
    public List<AuthorResponseSummaryDTO> filterAuthors(@RequestParam String filterBy
            , @RequestParam FilterOperator operator,@RequestParam String value) {
        return authorService.filterAuthors(AuthorFilter.fromString(filterBy),operator,value);
    }

}
