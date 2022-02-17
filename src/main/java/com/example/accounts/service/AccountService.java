package com.example.accounts.service;

import com.example.accounts.model.Account;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AccountService {
    Account insertAccount(Account accountPayload);

    List<Account> findAllAccounts();
}
