package com.example.virtualBookStore.repository;

import com.example.virtualBookStore.model.Book;
import com.example.virtualBookStore.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book,Long> {
    List<Book> findByAuthorContainingIgnoreCase(String author);
    List<Book> findByCategories_NameIgnoreCase(String nameCategory);
    Book getBookById(Long id);
}
