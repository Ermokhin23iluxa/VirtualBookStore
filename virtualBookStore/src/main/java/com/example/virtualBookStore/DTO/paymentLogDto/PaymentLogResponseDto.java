package com.example.virtualBookStore.DTO.paymentLogDto;

import com.example.virtualBookStore.enums.PaymentStatus;
import com.example.virtualBookStore.enums.PaymentType;

import java.math.BigDecimal;
import java.time.Instant;

public record PaymentLogResponseDto(
        Long id,
        PaymentType paymentType,
        BigDecimal amount,
        PaymentStatus status,
        Instant timestamp
) {}
