package com.example.virtualBookStore.service;

import com.example.virtualBookStore.DTO.auth.*;
import com.example.virtualBookStore.enums.Role;
import com.example.virtualBookStore.exceptions.UserUnauthenticatedException;
import com.example.virtualBookStore.mapping.UserMapper;
import com.example.virtualBookStore.model.User;
import com.example.virtualBookStore.service.Jwt.JwtCacheService;
import com.example.virtualBookStore.service.Jwt.JwtService;
import com.example.virtualBookStore.validation.JwtValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserService userService;

    private final JwtService jwtService;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    private final UserMapper userMapper;

    private final JwtCacheService jwtCacheService;

    private final JwtValidator jwtValidator;

    public RegisterUserResponseDto registration(RegisterUserRequestDto registrationRequest) {

        User user = userMapper.toUser(registrationRequest);

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(Role.USER);
        userService.createUser(user);

        return new RegisterUserResponseDto(user.getUsername());
    }

    public LoginResponseDto login(LoginRequestDto loginRequestDto) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequestDto.getUsername(), loginRequestDto.getPassword())
            );
            User user = (User) authentication.getPrincipal();

            String accessToken = jwtService.generateAccessToken(user);
            String refreshToken = jwtService.generateRefreshToken(user);

            jwtCacheService.putAccessToken(user.getUsername(), accessToken);
            jwtCacheService.putRefreshToken(user.getUsername(), refreshToken);

            return new LoginResponseDto(accessToken, refreshToken);
        } catch (AuthenticationException ex) {
            throw new UserUnauthenticatedException("Пользователь не аутентифицирован");
        }
    }

    public RefreshTokenResponseDto refreshToken(RefreshTokenRequestDto refreshTokenRequestDto) {
        String refreshToken = refreshTokenRequestDto.getRefreshToken();
        String userName = jwtService.extractUserName(refreshToken);

        jwtValidator.refreshTokenValid(userName, refreshToken);

        UserDetails userDetails = userService.getUserByUsername(userName);

        String newAccessToken = jwtService.generateAccessToken(userDetails);
        String newRefreshToken = jwtService.generateRefreshToken(userDetails);

        jwtCacheService.putAccessToken(userName, newAccessToken);
        jwtCacheService.putRefreshToken(userName, newRefreshToken);

        return RefreshTokenResponseDto.builder()
                .accessToken(newAccessToken)
                .refreshToken(newRefreshToken)
                .build();
    }

}
