package com.example.virtualBookStore.DTO.bookDto;

import com.example.virtualBookStore.DTO.categoryDto.CategoryResponseDto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

public record BookDto(
        Long id,
        String title,
        String author,
        BigDecimal price,
        String description,
        BigDecimal rating,
        List<CategoryResponseDto> categories
) implements Serializable { }
