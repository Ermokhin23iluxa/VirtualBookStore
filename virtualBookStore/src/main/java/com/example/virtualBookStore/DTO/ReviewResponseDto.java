package com.example.virtualBookStore.DTO;

import lombok.Data;


public record ReviewResponseDto (
        Long bookId,
        double score,
        String comment){
}
