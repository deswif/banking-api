package com.example.banking.controllers.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class CreateTransactionRequest {
    @NotBlank
    private Integer accountId;

    @NotBlank
    private String receiverAccount;

    @NotBlank
    private BigDecimal amount;
}
