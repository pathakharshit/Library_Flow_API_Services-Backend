package com.LibraryManagement.Library.dto.request;

import com.LibraryManagement.Library.constraint.UserType;
import com.LibraryManagement.Library.model.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserRequestDTO {
    @NotBlank(message = "name can't be blank")
    private String name;
    @NotNull
    private String email;
    @NotNull
    private String phoneNumber;
    @NotNull
    private String userType;

    public User createUser() {
        return User.builder().name(name)
                .email(email)
                .phoneNumber(phoneNumber)
                .userType(UserType.fromString(userType))
                .build();
    }
}
