package com.example.banking.use_cases;

import com.example.banking.controllers.dto.AccountInfoResponse;
import com.example.banking.controllers.dto.AccountResponse;
import com.example.banking.entities.AccountEntity;
import com.example.banking.entities.UserEntity;
import com.example.banking.repositories.AccountRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetAccountsUseCase {
    private final AccountRepository accountRepository;

    public GetAccountsUseCase(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public List<AccountInfoResponse> call() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserEntity user = (UserEntity) authentication.getPrincipal();

        List<AccountEntity> accounts = accountRepository.findByUser_Id(user.getId());
        return accounts.stream().map(AccountInfoResponse::fromAccount).toList();
    }
}
