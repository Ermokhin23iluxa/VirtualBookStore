package com.example.virtualBookStore.DTO.auth;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RefreshTokenResponseDto{
    private String accessToken;
    private String refreshToken;
}


