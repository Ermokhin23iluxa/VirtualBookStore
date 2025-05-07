package com.example.virtualBookStore.repository;

import com.example.virtualBookStore.model.PaymentLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface PaymentLogRepository extends JpaRepository<PaymentLog,Long> {
    Optional<PaymentLog> findByOrder_Id(Long orderId);
}
