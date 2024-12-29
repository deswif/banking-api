package com.example.banking.controllers;

import com.example.banking.controllers.dto.AccountInfoResponse;
import com.example.banking.controllers.dto.AccountResponse;
import com.example.banking.controllers.dto.TransactionInfoResponse;
import com.example.banking.entities.TransactionEntity;
import com.example.banking.use_cases.CreateAccountUseCase;
import com.example.banking.use_cases.GetAccountUseCase;
import com.example.banking.use_cases.GetAccountsUseCase;
import com.example.banking.use_cases.GetTransactionsUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping(path = "/api/accounts")
public class AccountController {

    private final CreateAccountUseCase createAccountUseCase;
    private final GetAccountsUseCase getAccountsUseCase;
    private final GetAccountUseCase getAccountUseCase;
    private final GetTransactionsUseCase getTransactionsUseCase;

    public AccountController(CreateAccountUseCase createAccountUseCase, GetAccountsUseCase getAccountsUseCase, GetAccountUseCase getAccountUseCase, GetTransactionsUseCase getTransactionsUseCase) {
        this.createAccountUseCase = createAccountUseCase;
        this.getAccountsUseCase = getAccountsUseCase;
        this.getAccountUseCase = getAccountUseCase;
        this.getTransactionsUseCase = getTransactionsUseCase;
    }

    @PostMapping
    public ResponseEntity<AccountResponse> createAccount() {
        return ResponseEntity.ok(createAccountUseCase.call());
    }

    @GetMapping
    public ResponseEntity<List<AccountInfoResponse>> getAccounts() {
        return ResponseEntity.ok(getAccountsUseCase.call());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AccountResponse> getAccountById(@PathVariable("id") Integer id) throws Exception {
        return ResponseEntity.ok(getAccountUseCase.call(id));
    }

    @GetMapping("/{id}/transactions")
    public ResponseEntity<List<TransactionInfoResponse>> getTransactionsForCard(@PathVariable("id") Integer id) throws Exception {
        return ResponseEntity.ok(getTransactionsUseCase.call(id));
    }
}
