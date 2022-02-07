package com.example.accounts.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long account_id;
    private String accountType;
    private String accountNumber;
    private String IBAN;
    private String balance;
    private String currency;
    @OneToOne(mappedBy="account_id")
    @PrimaryKeyJoinColumn
    private User user;

    @OneToMany(mappedBy = "account_id", cascade = CascadeType.ALL)
    private List<Statement> statements;

}
