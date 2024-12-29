package com.example.banking.use_cases;

import com.example.banking.controllers.dto.CreateTransactionRequest;
import com.example.banking.entities.AccountEntity;
import com.example.banking.entities.TransactionEntity;
import com.example.banking.entities.UserEntity;
import com.example.banking.repositories.AccountRepository;
import com.example.banking.repositories.TransactionRepository;
import com.example.banking.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class CreateTransactionUseCase {

    private final TransactionRepository transactionRepository;
    private final AccountRepository accountRepository;

    public CreateTransactionUseCase(TransactionRepository transactionRepository, AccountRepository accountRepository) {
        this.transactionRepository = transactionRepository;
        this.accountRepository = accountRepository;
    }

    @Transactional
    public void call(CreateTransactionRequest request) throws Exception {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserEntity user = (UserEntity) authentication.getPrincipal();

        AccountEntity account = accountRepository.findById(request.getAccountId()).orElseThrow();
        if (!account.getUser().getId().equals(user.getId())) {
            throw new Exception();
        }
        System.out.println(account);

        AccountEntity receiverAccount = accountRepository.findByAccountNumber(request.getReceiverAccount()).orElseThrow();
        System.out.println(receiverAccount);

        if (account.getBalance().compareTo(request.getAmount()) < 0) {
            System.out.println("error balance");
            throw new Exception();
        }

        TransactionEntity transaction = TransactionEntity.builder()
                .senderAccount(account)
                .receiverAccount(receiverAccount)
                .amount(request.getAmount())
                .build();

        transactionRepository.save(transaction);

        account.setBalance(account.getBalance().subtract(request.getAmount()));
        receiverAccount.setBalance(receiverAccount.getBalance().add(request.getAmount()));

        accountRepository.save(account);
        accountRepository.save(receiverAccount);
    }
}
