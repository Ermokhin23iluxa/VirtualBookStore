package com.example.virtualBookStore.DTO.bookDto;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;
@Data
public class CreateBookRequestDto {
        @NotBlank(message = "Название книги не может быть пустым!")
        private String title;

        @NotBlank(message = "Автор не может быть пустым!")
        private String author;

        @NotNull(message = "Цена не может быть пустой!")
        @DecimalMin(value = "0.01", message = "Цена должна быть не менее 0.01")
        private BigDecimal price;

        @NotNull(message = "Количество на складе не может быть пустым!")
        @Min(value = 0, message = "Количество на складе не может быть отрицательным")
        private Integer stock;

        private String description;

        @NotEmpty(message = "Необходимо указать хотя бы одну категорию")
        private List<@NotNull(message = "categoryId не может быть null") Long> categoryIds;
}
