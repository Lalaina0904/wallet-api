package com.prog3.walletapp.entity;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.ToString;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@ToString
public class Transaction {
    private int id;
    private String label;
    private double amount;
   private LocalDateTime Date;
    private String type;
    private int category;
}
