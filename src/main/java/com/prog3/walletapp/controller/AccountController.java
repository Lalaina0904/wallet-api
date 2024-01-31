package com.prog3.walletapp.controller;

import com.prog3.walletapp.service.AccountService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.prog3.walletapp.entity.Account;

import java.util.List;

@RestController
@AllArgsConstructor
public class AccountController {
    private final AccountService accountService;

    @GetMapping("/accounts")
    public List<Account> getAccounts() {
        return accountService.getAccounts();
    }
}
