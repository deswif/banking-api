package com.example.banking.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Table(name = "accounts")
@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccountEntity {
    @Id
    @Column(name = "account_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private UserEntity user;

    @Column(nullable = false)
    private BigDecimal balance;

    @Column(nullable = false, unique = true)
    private String accountNumber;

    @Column(nullable = false, name = "valid_until")
    private Date validUntil;

    @Column(nullable = false)
    private Integer cvv;
}
