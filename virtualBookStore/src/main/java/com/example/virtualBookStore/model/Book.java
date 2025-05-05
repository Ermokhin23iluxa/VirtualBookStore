package com.example.virtualBookStore.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "Books")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="tittle")
    private String tittle;
    @Column(name="author")
    private String author;
    @Column(name="price")
    private BigDecimal price;
    @Column(name="description")
    private String description;

    @Column(name="rating")
    private double rating;


    @ManyToOne(cascade = CascadeType.REFRESH,fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

}
