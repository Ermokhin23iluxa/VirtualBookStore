package com.example.virtualBookStore.DTO.bookDto;

public record CreateBookRequestDto(
        String tittle,
        String author,
        Double price,
        String description
) { }
