package com.LibraryManagement.Library.services.filters.TxnsFilter;

import com.LibraryManagement.Library.constraint.FilterOperator;
import com.LibraryManagement.Library.model.Txns;

import java.util.List;

public interface FilterTxns {
    List<Txns> FilterAllTxns(FilterOperator operator, String value);
}
