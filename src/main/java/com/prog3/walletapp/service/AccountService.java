package com.prog3.walletapp.service;

import org.springframework.stereotype.Service;
import com.prog3.walletapp.repository.AccountCrudOperation;
import com.prog3.walletapp.entity.Account;

import java.util.List;

@Service
public class AccountService {

    private AccountCrudOperation accountCrudOperations;

    public AccountService(AccountCrudOperation accountCrudOperations) {
        this.accountCrudOperations = accountCrudOperations;
    }

    public List<Account> getAccounts() {
        return accountCrudOperations.findAll();
    }
}
