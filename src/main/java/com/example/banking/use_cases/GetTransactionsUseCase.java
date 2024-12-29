package com.example.banking.use_cases;

import com.example.banking.controllers.dto.TransactionInfoResponse;
import com.example.banking.entities.AccountEntity;
import com.example.banking.entities.TransactionEntity;
import com.example.banking.entities.UserEntity;
import com.example.banking.repositories.AccountRepository;
import com.example.banking.repositories.TransactionRepository;
import com.example.banking.repositories.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetTransactionsUseCase {
    private final TransactionRepository transactionRepository;
    private final AccountRepository accountRepository;

    public GetTransactionsUseCase(TransactionRepository transactionRepository, AccountRepository accountRepository) {
        this.transactionRepository = transactionRepository;
        this.accountRepository = accountRepository;
    }

    public List<TransactionInfoResponse> call(Integer accountId) throws Exception {
        AccountEntity account = accountRepository.findById(accountId).orElseThrow();
        System.out.println(account);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserEntity user = (UserEntity) authentication.getPrincipal();
        System.out.println(user);

        if (!account.getUser().getId().equals(user.getId())) {
            throw new Exception();
        }
        List<TransactionEntity> transactions = transactionRepository.findAllByAccountId(account.getId());

        return transactions.stream().map(t -> TransactionInfoResponse.fromTransaction(t, accountId)).toList();
    }
}
