package com.LibraryManagement.Library.repositories;

import com.LibraryManagement.Library.model.Book;
import com.LibraryManagement.Library.model.Txns;
import com.LibraryManagement.Library.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TxnsRepository extends JpaRepository<Txns,Integer> {
    Optional<Txns> findFirstByUserAndBookAndActualReturnDateIsNullOrderByIssueDateAsc(User user, Book book);
    List<Txns> findByBookTitleContainingIgnoreCase(String title);
    List<Txns> findByBookTitle(String title);
    List<Txns> findByUserEmail(String email);
    List<Txns> findByUserEmailContainingIgnoreCase(String email);
}
