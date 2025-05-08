package com.example.virtualBookStore.DTO.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class RefreshTokenResponseDto{
    private String accessToken;
    private String refreshToken;
}


