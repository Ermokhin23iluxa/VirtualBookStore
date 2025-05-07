package com.example.virtualBookStore.service.payment;

import com.example.virtualBookStore.enums.PaymentType;
import com.example.virtualBookStore.model.Order;
import com.example.virtualBookStore.model.PaymentLog;
import com.example.virtualBookStore.repository.PaymentLogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class PaymentService {
    private final Map<String,PaymentStrategy> strategies;
    private final PaymentLogRepository logRepo;

    public PaymentLog pay(Order order, PaymentType type){
        PaymentStrategy strategy = strategies.get(type.name());
        if (strategy == null) throw new IllegalArgumentException("Unsupported " + type);
        PaymentLog log = strategy.pay(order);
        return logRepo.save(log);
    }
}

