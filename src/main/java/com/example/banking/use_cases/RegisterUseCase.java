package com.example.banking.use_cases;

import com.example.banking.controllers.dto.RegisterRequest;
import com.example.banking.controllers.dto.RegisterResponse;
import com.example.banking.controllers.dto.TokenResponse;
import com.example.banking.controllers.dto.UserResponse;
import com.example.banking.entities.UserEntity;
import com.example.banking.repositories.UserRepository;
import com.example.banking.services.JwtService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class RegisterUseCase {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public RegisterUseCase(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtService jwtService, AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    public RegisterResponse call(RegisterRequest registerRequest) {
        UserEntity userEntity = UserEntity.builder()
                .email(registerRequest.getEmail())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .build();

        UserEntity savedUser = userRepository.save(userEntity);

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(registerRequest.getEmail(), registerRequest.getPassword()));

        String accessToken = jwtService.generateToken(savedUser);

        TokenResponse tokenResponse = TokenResponse.builder()
                .token(accessToken)
                .expiresIn(jwtService.getExpirationTime())
                .build();

        return RegisterResponse.builder()
                .user(UserResponse.fromUser(savedUser))
                .token(tokenResponse)
                .build();
    }
}
