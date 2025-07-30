package com.LibraryManagement.Library.services;

import com.LibraryManagement.Library.constraint.FilterOperator;
import com.LibraryManagement.Library.constraint.UserFilter;
import com.LibraryManagement.Library.constraint.UserType;
import com.LibraryManagement.Library.dto.request.UserRequestDTO;
import com.LibraryManagement.Library.dto.response.UserResponseDTO;
import com.LibraryManagement.Library.exception.UserException;
import com.LibraryManagement.Library.model.User;
import com.LibraryManagement.Library.repositories.UserRepository;
import com.LibraryManagement.Library.services.factories.UserFilterFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserFilterFactory userFilterFactory;
    @Autowired
    private BookService bookService;
    public UserResponseDTO createUser(UserRequestDTO userRequestDTO) {
        User user = userRequestDTO.createUser();
        user = userRepository.save(user);
        UserResponseDTO userResponseDTO;
        userResponseDTO = UserResponseDTO.builder().email(user.getEmail())
                .name(user.getName())
                .phoneNumber(user.getPhoneNumber())
                .build();
        return userResponseDTO;
    }

    public List<UserResponseDTO> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(user -> UserResponseDTO.builder()
                        .email(user.getEmail())
                        .name(user.getName())
                        .phoneNumber(user.getPhoneNumber())
                        .books(new BookService().convertBookToBookResponseDTO(user.getBooks()))
                        .txns(new TxnsService().convert(user.getTxns()))
                        .build())
                .collect(Collectors.toList());
    }

    public ResponseEntity<String> deleteUserByEmail(String email) {
        Optional<User> userOptional = userRepository.findByEmail(email);
        if (userOptional.isPresent()) {
            userRepository.delete(userOptional.get());
            return ResponseEntity.ok("User with email " + email + " has been successfully deleted.");
        }
        else throw new UserException("User with email " + email + " not found");
    }

    public UserResponseDTO getUserByEmail(String email) {
        Optional<User> userOptional = userRepository.findByEmail(email);
        UserResponseDTO userResponseDTO;
        if(userOptional.isPresent()) {
            User user = userOptional.get();
            userResponseDTO = UserResponseDTO.builder()
                    .email(user.getEmail())
                    .name(user.getName())
                    .phoneNumber(user.getPhoneNumber())
                    .books(bookService.convertBookToBookResponseDTO(user.getBooks()))
                    .txns(new TxnsService().convert(user.getTxns()))
                    .build();
        } else throw new UserException("User with email " + email + " not found");
        return userResponseDTO;
    }

    public ResponseEntity<String> updateUserByEmail(UserRequestDTO userRequestDTO) {
        Optional<User> userOptional = userRepository.findByEmail(userRequestDTO.getEmail());
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            user.setName(userRequestDTO.getName());
            user.setPhoneNumber(userRequestDTO.getPhoneNumber());
            user.setUserType(UserType.STUDENT);
            userRepository.save(user);
            return ResponseEntity.ok("User with email " + userRequestDTO.getEmail() + " has been updated successfully");
        } else throw new UserException("User with email " + userRequestDTO.getEmail() + " not found");
    }

    public List<UserResponseDTO> filterUsers(UserFilter filterBy, FilterOperator operator,String value) {
        List<User> users = userFilterFactory.getFilter(filterBy).filterAllUsers(operator,value);
        if(users.isEmpty()) {
            throw new UserException("User doesn't exist ");
        }
        return users.stream()
                .map(user -> UserResponseDTO.builder()
                        .name(user.getName())
                        .phoneNumber(user.getPhoneNumber())
                        .email(user.getEmail())
                                .books(new BookService().convertBookToBookResponseDTO(user.getBooks()))
                                .txns(new TxnsService().convert(user.getTxns()))
                        .build()
                        )
                .collect(Collectors.toList());
    }

    public User getUserFullDetailByEmail(String email) {
        Optional<User> existingUser = userRepository.findByEmail(email);
        if(existingUser.isPresent()) {
            return existingUser.get();
        } else {
            throw new UserException("User not found");
        }
    }

    public void saveUser(User user) {
        userRepository.save(user);
    }
}
