package com.example.banking.repositories;

import com.example.banking.entities.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AccountRepository extends JpaRepository<AccountEntity, Integer> {
    List<AccountEntity> findByUser_Id(Integer id);

    Optional<AccountEntity> findByAccountNumber(String accountNumber);
}
