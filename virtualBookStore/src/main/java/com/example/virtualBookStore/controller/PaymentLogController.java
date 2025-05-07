package com.example.virtualBookStore.controller;

import com.example.virtualBookStore.DTO.paymentLogDto.PaymentLogResponseDto;
import com.example.virtualBookStore.exceptions.NoSuchPaymentException;
import com.example.virtualBookStore.mapping.PaymentLogMapper;
import com.example.virtualBookStore.model.PaymentLog;
import com.example.virtualBookStore.repository.PaymentLogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payments")
@RequiredArgsConstructor
public class PaymentLogController {
    private final PaymentLogRepository paymentLogRepository;
    private final PaymentLogMapper paymentLogMapper;

    @GetMapping("/{orderId}")
    public ResponseEntity<PaymentLogResponseDto> getByOrder(@PathVariable Long orderId) {
        PaymentLog log = paymentLogRepository.findByOrder_Id(orderId)
                .orElseThrow(() -> new NoSuchPaymentException(orderId));
        return ResponseEntity.ok(paymentLogMapper.toPaymentLogResponseDto(log));
    }
}
