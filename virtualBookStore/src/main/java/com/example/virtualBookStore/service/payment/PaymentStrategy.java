package com.example.virtualBookStore.service.payment;

import com.example.virtualBookStore.model.Order;
import com.example.virtualBookStore.model.PaymentLog;

public interface PaymentStrategy {
    PaymentLog pay(Order order);
}
