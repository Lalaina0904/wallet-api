package com.prog3.walletapp.entity;

import  lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@Setter
@ToString
public class Currency {
    int id;
    String name;
    String code;
}
