package com.example.virtualBookStore.repository;

import com.example.virtualBookStore.model.PaymentLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<PaymentLog,Long> {
}
