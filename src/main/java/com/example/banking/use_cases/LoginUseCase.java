package com.example.banking.use_cases;

import com.example.banking.controllers.dto.*;
import com.example.banking.entities.UserEntity;
import com.example.banking.repositories.UserRepository;
import com.example.banking.services.JwtService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
public class LoginUseCase {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public LoginUseCase(UserRepository userRepository, JwtService jwtService, AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    public LoginResponse call(LoginRequest loginRequest) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));

        UserEntity user = userRepository.findByEmail(loginRequest.getEmail()).orElseThrow();

        String accessToken = jwtService.generateToken(user);

        TokenResponse tokenResponse = TokenResponse.builder()
                .token(accessToken)
                .expiresIn(jwtService.getExpirationTime())
                .build();

        return LoginResponse.builder()
                .user(UserResponse.fromUser(user))
                .token(tokenResponse)
                .build();
    }
}
