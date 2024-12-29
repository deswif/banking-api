package com.example.banking.use_cases;

import com.example.banking.controllers.dto.AccountResponse;
import com.example.banking.entities.AccountEntity;
import com.example.banking.entities.UserEntity;
import com.example.banking.repositories.AccountRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class GetAccountUseCase {
    private final AccountRepository accountRepository;

    public GetAccountUseCase(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public AccountResponse call(Integer accountId) throws Exception {
        AccountEntity account = accountRepository.findById(accountId).orElseThrow();

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserEntity user = (UserEntity) authentication.getPrincipal();

        if (!account.getUser().getId().equals(user.getId())) {
            throw new Exception();
        }
        return AccountResponse.fromAccount(account);
    }
}
