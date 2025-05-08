package com.example.virtualBookStore.DTO.reviewDto;

import java.math.BigDecimal;
import java.time.Instant;

public record ReviewResponseDto (
        Long id,
        Long bookId,
        String bookTitle,
        String userName,
        BigDecimal score,
        String comment,
        Instant createdAt
){}
