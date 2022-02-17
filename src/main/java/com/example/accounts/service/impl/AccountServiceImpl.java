package com.example.accounts.service.impl;

import com.example.accounts.model.Account;
import com.example.accounts.repo.AccountRepo;
import com.example.accounts.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepo accountRepo;

    @Override
    public Account insertAccount(Account accountPayload) {
        return accountRepo.save(accountPayload);
    }

    @Override
    public List<Account> findAllAccounts() {
        return accountRepo.findAll();
    }
}
