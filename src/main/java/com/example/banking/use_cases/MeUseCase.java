package com.example.banking.use_cases;

import com.example.banking.controllers.dto.UserResponse;
import com.example.banking.entities.UserEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class MeUseCase {
    public UserResponse call() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserEntity user = (UserEntity) authentication.getPrincipal();

        return UserResponse.fromUser(user);
    }
}
