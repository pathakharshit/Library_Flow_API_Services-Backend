package com.LibraryManagement.Library.dto.response;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserResponseDTO {
    private String name;
    private String email;
    private String phoneNumber;
    private List<BookResponseDTO> books;
    @JsonIgnoreProperties({"user"})
    private List<TxnsResponseDTO> txns;
}
