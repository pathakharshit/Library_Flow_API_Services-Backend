package com.LibraryManagement.Library.controllers;

import com.LibraryManagement.Library.constraint.FilterOperator;
import com.LibraryManagement.Library.constraint.UserFilter;
import com.LibraryManagement.Library.dto.request.UserRequestDTO;
import com.LibraryManagement.Library.dto.response.UserResponseDTO;
import com.LibraryManagement.Library.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<UserResponseDTO> createUser(@RequestBody @Validated UserRequestDTO userRequestDTO) {
        UserResponseDTO userResponseDTO = userService.createUser(userRequestDTO);
        return ResponseEntity.ok(userResponseDTO);
    }

    @GetMapping
    public List<UserResponseDTO> getALlUsers() {
        return userService.getAllUsers();
    }

    @DeleteMapping
    public ResponseEntity<String> deleteUserByEmail(@RequestParam(value = "email") String email) {
        return userService.deleteUserByEmail(email);
    }

    @GetMapping("/{email}")
    public UserResponseDTO getUserByEmail(@PathVariable String email) {
        return userService.getUserByEmail(email);
    }

    @PutMapping("/update")
    public ResponseEntity<String> updateUserByEmail(@RequestBody UserRequestDTO userRequestDTO) {
        return userService.updateUserByEmail(userRequestDTO);
    }

    @GetMapping("/filter")
    public List<UserResponseDTO> filterUsers(@RequestParam String filterBy, @RequestParam FilterOperator operator,
                                             @RequestParam String value) {
        return userService.filterUsers(UserFilter.fromString(filterBy),operator,value);
    }
}
