package com.LibraryManagement.Library.services.filters.TxnsFilter;

import com.LibraryManagement.Library.constraint.FilterOperator;
import com.LibraryManagement.Library.exception.BookException;
import com.LibraryManagement.Library.model.Txns;
import com.LibraryManagement.Library.repositories.TxnsRepository;
import com.LibraryManagement.Library.services.AuthorService;
import com.LibraryManagement.Library.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TxnsFilterByBook implements FilterTxns {
    @Autowired
    private TxnsRepository txnsRepository;
    @Autowired
    private BookService bookService;
    @Autowired
    private AuthorService authorService;
    @Override
    public List<Txns> FilterAllTxns(FilterOperator operator, String value) {
        if(operator != FilterOperator.EQUALS && operator != FilterOperator.LIKE)
            throw new IllegalArgumentException("Operator doesn't match for this filter");
        List<Txns> txns;
        if(operator == FilterOperator.LIKE) {
            txns = txnsRepository.findByBookTitleContainingIgnoreCase(value);
        } else {
            txns = txnsRepository.findByBookTitle(value);
        }
        if(txns.isEmpty()) {
            throw new BookException("No transactions with this type of title");
        }
        return txns;
    }
}
