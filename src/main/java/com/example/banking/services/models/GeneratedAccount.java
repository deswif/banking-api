package com.example.banking.services.models;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class GeneratedAccount {
    private String accountNumber;
    private Date validUntil;
    private int cvv;
}
