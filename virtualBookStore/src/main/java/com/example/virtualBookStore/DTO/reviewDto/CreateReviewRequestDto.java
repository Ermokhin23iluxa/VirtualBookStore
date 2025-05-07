package com.example.virtualBookStore.DTO.reviewDto;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class CreateReviewRequestDto {
    @NotNull(message = "id книги не может быть пустым!")
    private Long bookId;

    @NotNull(message = "Оценка не может быть пустой!")
    @DecimalMin(value = "1.00",inclusive = true,message = "Оценка от 1.00 до 5.00")
    @DecimalMax(value = "5.00",inclusive = true,message = "Оценка от 1.00 до 5.00")
    private BigDecimal score;
    //@NotBlank(message = "Комментарий не может быть пустым!")
    private String comment;
}
