package com.prog3.walletapp.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
@Setter
@ToString
public class CurrencyValue {
    int idCurrencyValue;
    int idCurrencySource;
    int idCurrencyDestination;
    Double amount;
    LocalDateTime Date;

}
