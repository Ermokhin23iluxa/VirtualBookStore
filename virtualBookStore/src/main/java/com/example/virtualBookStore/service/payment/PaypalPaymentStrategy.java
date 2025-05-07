package com.example.virtualBookStore.service.payment;

import com.example.virtualBookStore.enums.PaymentStatus;
import com.example.virtualBookStore.enums.PaymentType;
import com.example.virtualBookStore.model.Order;
import com.example.virtualBookStore.model.PaymentLog;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component("PAYPAL")
@RequiredArgsConstructor
public class PaypalPaymentStrategy implements PaymentStrategy{
    @Override
    public PaymentLog pay(Order order) {
        PaymentLog paymentLog = new PaymentLog();
        paymentLog.setOrder(order);
        paymentLog.setPaymentType(PaymentType.PAYPAL);
        paymentLog.setAmount(order.getTotalAmount());
        paymentLog.setStatus(PaymentStatus.SUCCESS);
        paymentLog.setTimestamp(Instant.now());
        return paymentLog;
    }
}
