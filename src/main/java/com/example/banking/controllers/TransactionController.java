package com.example.banking.controllers;

import com.example.banking.controllers.dto.CreateTransactionRequest;
import com.example.banking.use_cases.CreateTransactionUseCase;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/api/transactions")
public class TransactionController {

    private final CreateTransactionUseCase createTransactionUseCase;

    public TransactionController(CreateTransactionUseCase createTransactionUseCase) {
        this.createTransactionUseCase = createTransactionUseCase;
    }

    @PostMapping
    public ResponseEntity<String> createTransaction(@RequestBody CreateTransactionRequest request) throws Exception {
        System.out.println("Creating transaction");
        createTransactionUseCase.call(request);
        return ResponseEntity.ok("success");
    }
}
