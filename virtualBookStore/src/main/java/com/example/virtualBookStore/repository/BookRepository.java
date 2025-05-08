package com.example.virtualBookStore.repository;

import com.example.virtualBookStore.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book,Long> {
    List<Book> findByAuthorContainingIgnoreCase(String author);
    List<Book> findByCategories_NameIgnoreCase(String nameCategory);
    Optional<Book> findByTitleIgnoreCase(String title);
}
