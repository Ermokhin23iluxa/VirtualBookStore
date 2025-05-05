package com.example.virtualBookStore.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Category {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    private String name;

    @ManyToMany(cascade = {CascadeType.REFRESH,CascadeType.MERGE}, fetch = FetchType.LAZY, mappedBy="categories")
    private List<Book> books = new ArrayList<>();

}
