package com.example.virtualBookStore.DTO.orderDto;

import com.example.virtualBookStore.enums.PaymentType;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class CreateOrderRequestDto{
    @NotEmpty
    private List<OrderItemRequest> items;
    @NotNull
    private PaymentType paymentType;

    @Data
    public static class OrderItemRequest {
        @NotNull
        private Long bookId;
        @Min(1)
        private int quantity;

    }
}
