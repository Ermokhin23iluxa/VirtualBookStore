package com.example.virtualBookStore.DTO;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CreateReviewRequestDto {
    @NotBlank(message = "id книги не может быть пустым!")
    private Long bookId;
    @NotBlank(message = "Оценка не может быть пустой!")
    @DecimalMin(value = "1.00",inclusive = true,message = "Оценка от 1.00 до 5.00")
    @DecimalMax(value = "5.00",inclusive = true,message = "Оценка от 1.00 до 5.00")
    private double score;
    @NotBlank(message = "Комментарий не может быть пустым!")
    private String comment;
}
