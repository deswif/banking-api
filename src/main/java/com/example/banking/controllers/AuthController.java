package com.example.banking.controllers;

import com.example.banking.controllers.dto.LoginRequest;
import com.example.banking.controllers.dto.LoginResponse;
import com.example.banking.controllers.dto.RegisterRequest;
import com.example.banking.controllers.dto.RegisterResponse;
import com.example.banking.use_cases.LoginUseCase;
import com.example.banking.use_cases.RegisterUseCase;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/api/auth")
public class AuthController {

    private final LoginUseCase loginUseCase;
    private final RegisterUseCase registerUseCase;

    public AuthController(LoginUseCase loginUseCase, RegisterUseCase registerUseCase) {
        this.loginUseCase = loginUseCase;
        this.registerUseCase = registerUseCase;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest loginRequest) {
        return ResponseEntity.ok(loginUseCase.call(loginRequest));
    }

    @PostMapping("/register")
    public ResponseEntity<RegisterResponse> register(@Valid @RequestBody RegisterRequest registerRequest) {
        return ResponseEntity.ok(registerUseCase.call(registerRequest));
    }
}
