package com.example.virtualBookStore.DTO.auth;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class RefreshTokenRequestDto{
        @NotBlank(message = "RefreshToken не может быть пустым")
        private String refreshToken;
}

