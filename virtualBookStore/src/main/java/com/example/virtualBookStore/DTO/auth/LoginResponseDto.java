package com.example.virtualBookStore.DTO.auth;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class LoginResponseDto{
    private String accessToken;
    private String refreshToken;
}
