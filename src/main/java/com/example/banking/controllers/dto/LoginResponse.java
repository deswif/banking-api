package com.example.banking.controllers.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginResponse {
    private UserResponse user;
    private TokenResponse token;
}
