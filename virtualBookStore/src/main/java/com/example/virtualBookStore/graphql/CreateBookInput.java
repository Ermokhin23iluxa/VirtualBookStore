package com.example.virtualBookStore.graphql;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;
@Getter
@Setter
public class CreateBookInput {
    private String title;
    private String author;
    private String description;
    private BigDecimal price;
    private Integer stock;
    private List<Long> categoryIds;
}
