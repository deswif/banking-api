package com.example.banking.use_cases;

import com.example.banking.controllers.dto.AccountResponse;
import com.example.banking.entities.AccountEntity;
import com.example.banking.entities.UserEntity;
import com.example.banking.repositories.AccountRepository;
import com.example.banking.services.CardAccountGenerator;
import com.example.banking.services.models.GeneratedAccount;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class CreateAccountUseCase {

    private final AccountRepository accountRepository;
    private final CardAccountGenerator cardAccountGenerator;

    public CreateAccountUseCase(AccountRepository accountRepository, CardAccountGenerator cardAccountGenerator) {
        this.accountRepository = accountRepository;
        this.cardAccountGenerator = cardAccountGenerator;
    }

    public AccountResponse call() {
        GeneratedAccount generatedAccount = cardAccountGenerator.generate();

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserEntity user = (UserEntity) authentication.getPrincipal();

        AccountEntity accountEntity = AccountEntity.builder()
                .user(user)
                .balance(BigDecimal.ZERO)
                .accountNumber(generatedAccount.getAccountNumber())
                .validUntil(generatedAccount.getValidUntil())
                .cvv(generatedAccount.getCvv())
                .build();

        System.out.println(accountEntity);
        AccountEntity savedAccount = accountRepository.save(accountEntity);
        System.out.println(savedAccount);

        return AccountResponse.fromAccount(savedAccount);
    }
}
