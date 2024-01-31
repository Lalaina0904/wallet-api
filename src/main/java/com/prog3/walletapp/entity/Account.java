package com.prog3.walletapp.entity;

import  lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
@ToString
public class Account {
    private int id;
    private String Name;
    private Double sold;
    private List<Transaction> transactions;
    private Currency currency;
    private String type;

    public Account(int id, String accountName, double sold, Currency currency, String accounttype) {
        this.id = id;
        this.Name = accountName;
        this.sold = sold;
        this.currency = currency;
        this.type = accounttype;
    }
}
