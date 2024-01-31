package com.prog3.walletapp.entity;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@Setter
@ToString
public class TransfertHistory {
   private int id;
   private int idTransactionDebitor;

   private int idTransactionCreditor;
    private double amount;
   private LocalDateTime dateTime;
}
