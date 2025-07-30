package com.LibraryManagement.Library.services.filters.TxnsFilter;

import com.LibraryManagement.Library.constraint.FilterOperator;
import com.LibraryManagement.Library.model.Txns;
import com.LibraryManagement.Library.repositories.TxnsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


@Component
public class TxnsFilterByExpectedReturnDate implements FilterTxns{
    @Autowired
    private TxnsRepository txnsRepository;
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
    @Override
    public List<Txns> FilterAllTxns(FilterOperator operator, String value) {
        Date filterDate;
        try {
            filterDate = DATE_FORMAT.parse(value);
        } catch (ParseException e) {
            throw new IllegalArgumentException("Invalid date format. Use yyyy-MM-dd.", e);
        }


        List<Txns> txns = txnsRepository.findAll();
        return txns.stream()
                .filter(txn -> filterByOperator(txn.getExpectedReturnDate(), filterDate, operator))
                .collect(Collectors.toList());
    }
    private boolean filterByOperator(Date txnDate, Date filterDate, FilterOperator operator) {
        return switch (operator) {
            case EQUALS -> txnDate.equals(filterDate);
            case LESS_THAN -> txnDate.before(filterDate);
            case GREATER_THAN -> txnDate.after(filterDate);
            default -> throw new IllegalArgumentException("Invalid filter operator: " + operator);
        };
    }
}
