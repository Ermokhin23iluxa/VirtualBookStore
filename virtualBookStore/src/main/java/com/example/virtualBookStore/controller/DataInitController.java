package com.example.virtualBookStore.controller;

import com.example.virtualBookStore.model.Book;
import com.example.virtualBookStore.model.Category;
import com.example.virtualBookStore.repository.BookRepository;
import com.example.virtualBookStore.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/init")
@RequiredArgsConstructor
public class DataInitController {
    private final CategoryRepository catRepo;
    private final BookRepository bookRepo;

    @PostMapping("/demo")
    public ResponseEntity<String> loadDemo() {
        if (catRepo.count() > 0) {
            return ResponseEntity.badRequest().body("Данные уже загружены");
        }
        // пример создания
        Category fantasy = catRepo.save(new Category(null, "Fantasy", List.of()));
        Category sciFi   = catRepo.save(new Category(null, "Sci-Fi", List.of()));

        bookRepo.save(new Book(null, "Dune", "Frank Herbert", "...", BigDecimal.valueOf(9.99), 10, BigDecimal.ZERO, List.of(sciFi), List.of()));
        bookRepo.save(new Book(null, "Hobbit", "J.R.R. Tolkien", "...", BigDecimal.valueOf(7.99), 15, BigDecimal.ZERO, List.of(fantasy), List.of()));

        return ResponseEntity.ok("Demo data loaded");
    }
}
