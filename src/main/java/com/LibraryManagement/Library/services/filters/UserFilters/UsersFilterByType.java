package com.LibraryManagement.Library.services.filters.UserFilters;

import com.LibraryManagement.Library.constraint.FilterOperator;
import com.LibraryManagement.Library.constraint.UserType;
import com.LibraryManagement.Library.model.User;
import com.LibraryManagement.Library.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UsersFilterByType implements FilterUsers {
    @Autowired
    private UserRepository userRepository;
    @Override
    public List<User> filterAllUsers(FilterOperator operator, String value) {
        if(operator != FilterOperator.EQUALS) {
            throw new IllegalArgumentException("Operator doesn't match for this filter");
        }
        return userRepository.findByUserType(UserType.fromString(value));
    }
}
