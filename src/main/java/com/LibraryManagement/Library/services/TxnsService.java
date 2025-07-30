package com.LibraryManagement.Library.services;

import com.LibraryManagement.Library.constraint.FilterOperator;
import com.LibraryManagement.Library.constraint.TxnFilter;
import com.LibraryManagement.Library.dto.request.TxnsRequestDTO;
import com.LibraryManagement.Library.dto.response.TxnsResponseDTO;
import com.LibraryManagement.Library.exception.AuthorException;
import com.LibraryManagement.Library.exception.BookException;
import com.LibraryManagement.Library.exception.TxnException;
import com.LibraryManagement.Library.model.Author;
import com.LibraryManagement.Library.model.Book;
import com.LibraryManagement.Library.model.Txns;
import com.LibraryManagement.Library.model.User;
import com.LibraryManagement.Library.repositories.TxnsRepository;
import com.LibraryManagement.Library.services.factories.TxnsFilterFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TxnsService {

    @Autowired
    private UserService userService;
    @Autowired
    private BookService bookService;
    @Autowired
    private AuthorService authorService;
    @Autowired
    private TxnsRepository txnsRepository;
    @Autowired
    private TxnsFilterFactory txnsFilterFactory;

    private static int finePerDay = 50;

    public ResponseEntity<String> setFine(int newFine) {
        finePerDay = newFine;
        return ResponseEntity.ok("Fine updated successfully");
    }


    public List<TxnsResponseDTO> convert(List<Txns> txns) {
        return  txns.stream()
                    .map(txn -> TxnsResponseDTO.builder()
                    .id(txn.getId())
                    .actualReturnDate(txn.getActualReturnDate())
                    .issueDate(txn.getIssueDate())
                    .expectedReturnDate(txn.getExpectedReturnDate())
                    .fine(txn.getFine())
                    .book(new BookService().convertBookToBookResponseDTO(txn.getBook()))
                    .build())
                .collect(Collectors.toList());
    }
    public Integer calcFine(Date expectedReturnDate, Date actualReturnDate) {
        if(actualReturnDate.before(expectedReturnDate)) return 0;
        long expectedTime = expectedReturnDate.getTime();
        long actualTime = actualReturnDate.getTime();

        long differenceInMillis = actualTime - expectedTime;
        long differenceInDays = differenceInMillis / (1000 * 60 * 60 * 24);

        return (int) differenceInDays * finePerDay;
    }

    public ResponseEntity<String> returnBook(TxnsRequestDTO txnsRequestDTO) {
        User user = userService.getUserFullDetailByEmail(txnsRequestDTO.getUserEmail());
        Optional<Author> existingAuthor = authorService.getAuthorByEmail(txnsRequestDTO.getAuthorEmail());
        Author author;
        if(existingAuthor.isEmpty())
            throw new AuthorException("Author not found");
        author = existingAuthor.get();
        String bookTitle = txnsRequestDTO.getBookTitle();
        Book book = bookService.getBookByTitleAndAuthor(bookTitle,author);
        Optional<Txns> txnsToUpdate = txnsRepository.findFirstByUserAndBookAndActualReturnDateIsNullOrderByIssueDateAsc(user,book);
        if(txnsToUpdate.isEmpty())
            throw new TxnException("No transaction is found");
        Txns txns = txnsToUpdate.get();
        txns.setActualReturnDate(new Date());
        int fine = calcFine(txns.getExpectedReturnDate(),new Date());
        txns.setFine(fine);
        book.setNumberOfCopies(book.getNumberOfCopies() + 1);
        boolean removed = user.getBooks().remove(book);
        if(!removed)
            throw new TxnException("Failed to remove book from the user list");
        txnsRepository.save(txns);
        userService.saveUser(user);
        bookService.saveBook(book);
        return ResponseEntity.ok("Book returned Successfully");
    }
    public ResponseEntity<String> createTxns(TxnsRequestDTO txnsRequestDTO) {
        User user = userService.getUserFullDetailByEmail(txnsRequestDTO.getUserEmail());
        Optional<Author> existingAuthor = authorService.getAuthorByEmail(txnsRequestDTO.getAuthorEmail());
        Author author;
        if(existingAuthor.isEmpty())
            throw new AuthorException("Author not found");
        author = existingAuthor.get();
        String bookTitle = txnsRequestDTO.getBookTitle();
        Book book = bookService.getBookByTitleAndAuthor(bookTitle,author);
        if(book.getNumberOfCopies() == 0)
            throw new BookException("No copies are left");
        Txns txns = Txns.builder()
                .book(book)
                .user(user)
                .build();
        book.setNumberOfCopies(book.getNumberOfCopies()-1);
        txnsRepository.save(txns);
        user.getBooks().add(book);
        book.getUsers().add(user);
        userService.saveUser(user);
        return ResponseEntity.ok("Transaction successful");
    }

    public List<Txns> filterTxns(TxnFilter filterBy, FilterOperator operator,String value) {
        List<Txns> txns = txnsFilterFactory.getFilter(filterBy).FilterAllTxns(operator,value);
        return txns;
    }

    public List<Txns> getAllTxns() {
        return txnsRepository.findAll();
    }
}
