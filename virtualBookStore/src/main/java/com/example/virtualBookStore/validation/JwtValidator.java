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

    public void accessTokenValid(String username, String token) {
        String tokenFromCache = jwtCacheService.getAccessToken(username);
        if(!token.equals(tokenFromCache)) {
            throw new InvalidAccessTokenException("Access токен невалидный");
        }
    }

    public void refreshTokenValid(String username, String token) {
        String tokenFromCache = jwtCacheService.getRefreshToken(username);
        if(!token.equals(tokenFromCache)) {
            throw new InvalidRefreshTokenException("Refresh токен невалидный");
        }
    }
}
