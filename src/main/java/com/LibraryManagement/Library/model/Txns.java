package com.LibraryManagement.Library.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Calendar;
import java.util.Date;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Entity
public class Txns {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @CreationTimestamp
    @Temporal(TemporalType.DATE)
    private Date issueDate;
    @Temporal(TemporalType.DATE)
    private Date expectedReturnDate;
    @Temporal(TemporalType.DATE)
    private Date actualReturnDate;

    private Integer fine;

    @ManyToOne
    @JoinColumn
    @JsonIgnoreProperties({"txns","books"})
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn
    @JsonIgnoreProperties({"txns","users"})
    private Book book;

    @PrePersist
    public void setExpectedReturnDate() {
        if (issueDate == null) {
            issueDate = new Date();
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(issueDate);
        calendar.add(Calendar.DAY_OF_MONTH, 15);
        this.expectedReturnDate = calendar.getTime();
    }
}
