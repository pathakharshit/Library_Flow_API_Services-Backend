package com.LibraryManagement.Library.services.filters.UserFilters;

import com.LibraryManagement.Library.constraint.FilterOperator;
import com.LibraryManagement.Library.model.User;
import com.LibraryManagement.Library.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UsersFilterByName implements FilterUsers {
    @Autowired
    private UserRepository userRepository;
    @Override
    public List<User> filterAllUsers(FilterOperator operator, String value) {
        if(operator != FilterOperator.LIKE) {
            throw new IllegalArgumentException("Operator not match for this filter");
        }
        return userRepository.findByNameContainingIgnoreCase(value);
    }
}
