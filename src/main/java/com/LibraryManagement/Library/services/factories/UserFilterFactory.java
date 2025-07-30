package com.LibraryManagement.Library.services.factories;

import com.LibraryManagement.Library.constraint.UserFilter;
import com.LibraryManagement.Library.services.filters.UserFilters.FilterUsers;
import com.LibraryManagement.Library.services.filters.UserFilters.UsersFilterByName;
import com.LibraryManagement.Library.services.filters.UserFilters.UsersFilterByType;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class UserFilterFactory {
    Map<UserFilter, FilterUsers> filterUsersMap = new HashMap<>();
    public UserFilterFactory(UsersFilterByName usersFilterByName, UsersFilterByType usersFilterByType) {
        filterUsersMap.put(UserFilter.NAME,usersFilterByName);
        filterUsersMap.put(UserFilter.USER_TYPE,usersFilterByType);
    }

    public FilterUsers getFilter(UserFilter userFilter) {
        return filterUsersMap.get(userFilter);
    }
}
