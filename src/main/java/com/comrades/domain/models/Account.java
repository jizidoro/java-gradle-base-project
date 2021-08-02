package com.comrades.domain.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class Account {

    private Long id;
    private String iban;
    private BigDecimal balance;
    

    public Account(Long id, String iban, BigDecimal balance) {
        this.id = id;
        this.iban = iban;
        this.balance = balance;
    }

    public Account(Long id, String iban, Double balance) {
        this.id = id;
        this.iban = iban;
        this.balance = new BigDecimal(balance);
    }

}
