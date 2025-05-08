package com.example.virtualBookStore.model;

import com.example.virtualBookStore.enums.PaymentStatus;
import com.example.virtualBookStore.enums.PaymentType;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Date;
@Entity
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaymentLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id",nullable = false)
    private Order order;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PaymentType paymentType;

    private BigDecimal amount;//сумма платежа

    @Enumerated(EnumType.STRING)
    private PaymentStatus status;// провалена или проведена

    private Instant timestamp = Instant.now();// время платежа

}
