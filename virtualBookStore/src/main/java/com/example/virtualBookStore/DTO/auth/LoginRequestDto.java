package com.example.virtualBookStore.DTO.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginRequestDto {
        @NotBlank(message = "Email пользователя не может быть пустым")
        @Email(message = "Неправильный email")
        private String email;
        @NotBlank(message = "Пароль не может быть пустым")
        private String password;
}

