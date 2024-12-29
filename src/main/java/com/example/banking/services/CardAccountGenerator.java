package com.example.banking.services;

import com.example.banking.services.models.GeneratedAccount;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.Random;

@Service
public class CardAccountGenerator {
    public GeneratedAccount generate() {
        StringBuilder generatedString = new StringBuilder();
        for (int i = 0; i < 8; i++) {
            generatedString.append(new Random().nextInt(10));
        }

        String cardNumber = "44411144%s".formatted(generatedString);

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.YEAR, 4);

        Date validUntil = calendar.getTime();

        int cvv = new Random().nextInt(100, 1000);

        return GeneratedAccount.builder()
                .accountNumber(cardNumber)
                .validUntil(validUntil)
                .cvv(cvv)
                .build();
    }
}
