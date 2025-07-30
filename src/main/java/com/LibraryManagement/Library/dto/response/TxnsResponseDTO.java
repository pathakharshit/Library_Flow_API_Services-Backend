package com.LibraryManagement.Library.dto.response;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TxnsResponseDTO {
    private Integer id;
    private Date issueDate;
    private Date expectedReturnDate;
    private Date actualReturnDate;
    private Integer fine;
    private BookResponseDTO book;
}
