package com.example.virtualBookStore.validation;

import com.example.virtualBookStore.exceptions.InvalidAccessTokenException;
import com.example.virtualBookStore.exceptions.InvalidRefreshTokenException;
import com.example.virtualBookStore.service.Jwt.JwtCacheService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class JwtValidator {

    private final JwtCacheService jwtCacheService;

    public void accessTokenValid(String email, String token) {
        String tokenFromCache = jwtCacheService.getAccessToken(email);
        if(!token.equals(tokenFromCache)) {
            throw new InvalidAccessTokenException("Access токен невалидный");
        }
    }

    public void refreshTokenValid(String email, String token) {
        String tokenFromCache = jwtCacheService.getRefreshToken(email);
        if(!token.equals(tokenFromCache)) {
            throw new InvalidRefreshTokenException("Refresh токен невалидный");
        }
    }
}
