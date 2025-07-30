package com.LibraryManagement.Library.services.filters.UserFilters;

import com.LibraryManagement.Library.constraint.FilterOperator;
import com.LibraryManagement.Library.model.User;

import java.util.List;

public interface FilterUsers {
    public List<User> filterAllUsers(FilterOperator operator, String value);
}
