package com.LibraryManagement.Library.repositories;

import com.LibraryManagement.Library.constraint.UserType;
import com.LibraryManagement.Library.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(String email);
    List<User> findByNameContainingIgnoreCase(String name);
    List<User> findByUserType(UserType userType);
}
