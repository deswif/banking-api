package com.example.banking.controllers.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
@AllArgsConstructor
public class LoginRequest {
    @NotBlank
    @Email
    private String email;

    @NotBlank
    @Length(min = 6)
    private String password;
}
