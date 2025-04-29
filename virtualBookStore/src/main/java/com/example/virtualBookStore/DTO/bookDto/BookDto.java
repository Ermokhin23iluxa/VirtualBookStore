package com.example.virtualBookStore.DTO.bookDto;

public record BookDto (
        Long id,
        String tittle,
        String author,
        Double price,
        String description,
        Double rating
){ }
