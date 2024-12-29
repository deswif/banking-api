package com.example.banking.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.util.Date;

@Table(name = "transactions")
@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TransactionEntity {
    @Id
    @Column(name = "transaction_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "sender_id")
    private AccountEntity senderAccount;

    @ManyToOne
    @JoinColumn(name = "receiver_id")
    private AccountEntity receiverAccount;

    private BigDecimal amount;

    @Column(name = "transaction_timestamp")
    @CreationTimestamp
    private Date transactionDate;
}
