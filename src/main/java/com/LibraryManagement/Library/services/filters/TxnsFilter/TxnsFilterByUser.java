package com.LibraryManagement.Library.services.filters.TxnsFilter;

import com.LibraryManagement.Library.constraint.FilterOperator;
import com.LibraryManagement.Library.exception.UserException;
import com.LibraryManagement.Library.model.Txns;
import com.LibraryManagement.Library.repositories.TxnsRepository;
import com.LibraryManagement.Library.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TxnsFilterByUser implements FilterTxns{
    @Autowired
    private TxnsRepository txnsRepository;
    @Autowired
    private UserService userService;

    @Override
    public List<Txns> FilterAllTxns(FilterOperator operator,String value) {
        if(operator != FilterOperator.EQUALS && operator != FilterOperator.LIKE)
            throw new IllegalArgumentException("Operator doesn't match for this filter");
        List<Txns> txns;
        if(operator == FilterOperator.LIKE)
            txns = txnsRepository.findByUserEmailContainingIgnoreCase(value);
        else {
            txns = txnsRepository.findByUserEmail(value);
        }
        if(txns.isEmpty())
            throw new UserException("There is no any transaction for this user");
        return txns;
    }
}
