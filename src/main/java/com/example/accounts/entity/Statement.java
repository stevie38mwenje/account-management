package com.example.accounts.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
public class Statement {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    @ManyToOne
    @Column(name = "account_id")
    private Account account_id;
    private String description;
    private Double amount;
    private LocalDate date;

}
