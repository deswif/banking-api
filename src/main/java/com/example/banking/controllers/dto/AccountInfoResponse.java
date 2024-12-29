package com.example.banking.controllers.dto;

import com.example.banking.entities.AccountEntity;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
@Builder
public class AccountInfoResponse {
    private Integer id;

    private BigDecimal balance;

    private String accountNumber;

    public static AccountInfoResponse fromAccount(AccountEntity account) {
        return new AccountInfoResponse(account.getId(), account.getBalance(), account.getAccountNumber());
    }
}
