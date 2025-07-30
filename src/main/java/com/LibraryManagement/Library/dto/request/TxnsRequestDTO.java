package com.LibraryManagement.Library.dto.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TxnsRequestDTO {
    private String userEmail;
    private String bookTitle;
    private String authorName;
    private String authorEmail;

}
