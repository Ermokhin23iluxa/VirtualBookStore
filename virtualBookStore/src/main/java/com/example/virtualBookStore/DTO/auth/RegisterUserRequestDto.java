package com.example.virtualBookStore.DTO.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RegisterUserRequestDto{
        @NotBlank(message = "Имя пользователя не может быть пустым") @Size(min = 2)
        private String name;
        @NotBlank(message = "Email не может быть пустым") @Email(message = "Неправильный email")
        private String email;
        @NotBlank(message = "Пароль не может быть пустым") @Size(min = 6)
        private String password;
}
