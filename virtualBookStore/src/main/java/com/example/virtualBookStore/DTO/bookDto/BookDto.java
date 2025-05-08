package com.example.virtualBookStore.DTO.bookDto;

import java.math.BigDecimal;

public record BookDto (
        Long id,
        String title,
        String author,
        BigDecimal price,
        String description,
        BigDecimal rating
){ }
