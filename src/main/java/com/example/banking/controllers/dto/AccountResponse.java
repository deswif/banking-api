package com.example.banking.controllers.dto;

import com.example.banking.entities.AccountEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
public class AccountResponse {
    private Integer id;

    private BigDecimal balance;

    private String accountNumber;

    private Date validUntil;

    private Integer cvv;

    public static AccountResponse fromAccount(AccountEntity account) {
        return new AccountResponse(account.getId(), account.getBalance(), account.getAccountNumber(), account.getValidUntil(), account.getCvv());
    }
}
