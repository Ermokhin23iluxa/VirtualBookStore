package com.example.virtualBookStore.DTO.orderDto;

import com.example.virtualBookStore.DTO.paymentLogDto.PaymentLogResponseDto;
import com.example.virtualBookStore.enums.Status;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

public record OrderResponseDto(
        Long id,
        Status status,
        BigDecimal totalAmount,
        Instant createdAt,
        List<OrderItemDto> items,
        PaymentLogResponseDto payment
) {
    public record OrderItemDto(
            Long bookId,
            String bookTitle,
            int quantity,
            BigDecimal priceAtPurchase
    ) {}
}
