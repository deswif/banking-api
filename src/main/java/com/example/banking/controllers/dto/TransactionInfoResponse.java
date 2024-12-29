package com.example.banking.controllers.dto;

import com.example.banking.entities.TransactionEntity;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
@Builder
public class TransactionInfoResponse {
    private Integer id;
    private String sourceAccount;
    private BigDecimal amount;
    private Date transactionDate;

    public static TransactionInfoResponse fromTransaction(TransactionEntity transaction, Integer accountId) {
        BigDecimal amount;
        String sourceAccount;
        if (transaction.getReceiverAccount().getId().equals(accountId)) {
            amount = transaction.getAmount();
            sourceAccount = transaction.getSenderAccount().getAccountNumber();
        } else {
            amount = transaction.getAmount().multiply(BigDecimal.valueOf(-1));
            sourceAccount = transaction.getReceiverAccount().getAccountNumber();
        }
        return new TransactionInfoResponse(transaction.getId(), sourceAccount, amount, transaction.getTransactionDate());
    }
}
