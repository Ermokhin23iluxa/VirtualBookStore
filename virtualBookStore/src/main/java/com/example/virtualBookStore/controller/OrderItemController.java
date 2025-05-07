package com.example.virtualBookStore.controller;

import com.example.virtualBookStore.DTO.orderDto.OrderResponseDto;
import com.example.virtualBookStore.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/orders/{orderId}/items")
@RequiredArgsConstructor
public class OrderItemController {
    private final OrderService orderService;

    @GetMapping
    public ResponseEntity<List<OrderResponseDto.OrderItemDto>> list(@PathVariable Long orderId,
                                                                    @AuthenticationPrincipal UserDetails ud) {
        OrderResponseDto dto = orderService.getOneOrder(orderId, ud.getUsername());
        return ResponseEntity.ok(dto.items());
    }
}

