package com.example.virtualBookStore.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Review {//отзыв
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(
    //cascade = {CascadeType.PERSIST,CascadeType.MERGE},
            fetch = FetchType.LAZY
    )
    @JoinColumn(name = "user_id",nullable = false)
    private User user;

    @ManyToOne(
            cascade = {CascadeType.PERSIST,CascadeType.MERGE},
            fetch = FetchType.LAZY
    )
    @JoinColumn(name = "book_id",nullable = false)
    private Book book;
    @DecimalMin(value = "1.00",inclusive = true)
    @DecimalMax(value = "5.00",inclusive = true)
    private BigDecimal score;
    private String comment;
    @Column(nullable = false)
    private Instant createdAt = Instant.now();

}
