package com.example.virtualBookStore.DTO.bookDto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public class CreateBookRequestDto {
        @NotBlank(message = "Название книги не может быть пустым!")
        private String tittle;
        @NotBlank(message = "Автор не может быть пустым!")
        private String author;
        @NotNull(message = "Цена не может быть 0")
        @DecimalMin(value = "0.01", message = "Цена должна быть не менее 0.01")
        private BigDecimal price;
        private String description;
}
