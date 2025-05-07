package com.example.virtualBookStore.controller;

import com.example.virtualBookStore.DTO.orderDto.CreateOrderRequestDto;
import com.example.virtualBookStore.DTO.orderDto.OrderResponseDto;
import com.example.virtualBookStore.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;

    @GetMapping
    public ResponseEntity<List<OrderResponseDto>> myOrders(@AuthenticationPrincipal UserDetails userDetails){
        return ResponseEntity.ok(orderService.listUserOrders(userDetails.getUsername()));
    }
    @GetMapping("/{orderId}")
    public ResponseEntity<OrderResponseDto> getOneOrder(@PathVariable Long orderId,
                                        @AuthenticationPrincipal UserDetails ud) {
        return ResponseEntity.ok(orderService.getOneOrder(orderId, ud.getUsername()));
    }

    @PostMapping
    public ResponseEntity<OrderResponseDto> placeOrder(
            @RequestBody @Valid CreateOrderRequestDto dto,
            @AuthenticationPrincipal UserDetails ud
    ) {
        OrderResponseDto od = orderService.placeOrder(dto, ud.getUsername());
        return ResponseEntity.status(HttpStatus.CREATED).body(od);
    }

}
