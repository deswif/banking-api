package com.example.banking.controllers.dto;

import com.example.banking.entities.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
public class UserResponse {
    private Integer id;
    private String email;
    private Date createdAt;

    public static UserResponse fromUser(UserEntity user) {
        return new UserResponse(user.getId(), user.getEmail(), user.getCreatedAt());
    }
}
