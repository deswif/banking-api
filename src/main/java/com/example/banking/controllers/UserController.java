package com.example.banking.controllers;

import com.example.banking.controllers.dto.UserResponse;
import com.example.banking.use_cases.MeUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/api/users")
public class UserController {

    private final MeUseCase meUseCase;

    public UserController(MeUseCase meUseCase) {
        this.meUseCase = meUseCase;
    }

    @GetMapping("/me")
    public ResponseEntity<UserResponse> me() {
        return ResponseEntity.ok(meUseCase.call());
    }
}
