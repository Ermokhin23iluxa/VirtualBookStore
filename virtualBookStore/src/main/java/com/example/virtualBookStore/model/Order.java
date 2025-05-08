package com.example.virtualBookStore.model;

import com.example.virtualBookStore.enums.Status;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "orders")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Order {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @ManyToOne(cascade = CascadeType.REFRESH,fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id",nullable = false)
    private User user;

    @Enumerated(EnumType.STRING)
    private Status status;

    private BigDecimal totalAmount;

    private Instant createdAt = Instant.now();

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER,mappedBy = "order")
    private List<OrderItem> items = new ArrayList<>();

    @OneToOne(mappedBy = "order", cascade = CascadeType.ALL, optional = true)
    private PaymentLog paymentLog;
}
