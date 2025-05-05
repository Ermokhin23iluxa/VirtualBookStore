package com.example.virtualBookStore.DTO.bookDto;

import java.math.BigDecimal;

public record BookDto (
        Long id,
        String tittle,
        String author,
        BigDecimal price,
        String description,
        double rating
){ }
