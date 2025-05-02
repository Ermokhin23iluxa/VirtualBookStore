package com.example.virtualBookStore.DTO.auth;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginRequestDto {
        @NotBlank(message = "Имя пользователя не может быть пустым")
        private String username;
        @NotBlank(message = "Пароль не может быть пустым")
        private String password;
}

