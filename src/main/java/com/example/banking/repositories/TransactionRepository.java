package com.example.banking.repositories;

import com.example.banking.entities.AccountEntity;
import com.example.banking.entities.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<TransactionEntity, Integer> {
    @Query(value = "SELECT * FROM transactions t WHERE t.sender_id = ?1 OR t.receiver_id = ?1", nativeQuery = true)
    List<TransactionEntity> findAllByAccountId(Integer accountId);
}
