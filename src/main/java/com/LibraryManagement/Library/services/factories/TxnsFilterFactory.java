package com.LibraryManagement.Library.services.factories;

import com.LibraryManagement.Library.constraint.TxnFilter;
import com.LibraryManagement.Library.services.filters.TxnsFilter.*;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class TxnsFilterFactory {
    Map<TxnFilter,FilterTxns> filterTxnsMap = new HashMap<>();
    public TxnsFilterFactory(TxnsFilterByUser txnsFilterByUser, TxnsFilterByBook txnsFilterByBook
                            , TxnsFilterByIssueDate txnsFilterByIssueDate,
                             TxnsFilterByActualReturnDate txnsFilterByActualReturnDate,
                             TxnsFilterByExpectedReturnDate txnsFilterByExpectedReturnDate) {
        filterTxnsMap.put(TxnFilter.BOOK,txnsFilterByBook);
        filterTxnsMap.put(TxnFilter.USER,txnsFilterByUser);
        filterTxnsMap.put(TxnFilter.ISSUE_DATE,txnsFilterByIssueDate);
        filterTxnsMap.put(TxnFilter.ACTUAL_RETURN_DATE,txnsFilterByActualReturnDate);
        filterTxnsMap.put(TxnFilter.EXPECTED_RETURN_DATE,txnsFilterByExpectedReturnDate);
    }
    public FilterTxns getFilter(TxnFilter txnFilter) {
        return filterTxnsMap.get(txnFilter);
    }
}
