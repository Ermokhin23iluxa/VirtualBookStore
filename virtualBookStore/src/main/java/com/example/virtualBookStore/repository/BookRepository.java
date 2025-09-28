package com.example.virtualBookStore.repository;

import com.example.virtualBookStore.model.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book,Long> {
    List<Book> findByAuthorContainingIgnoreCase(String author);

    List<Book> findByCategories_NameIgnoreCase(String nameCategory);

    Optional<Book> findByTitleIgnoreCase(String title);

    Page<Book> findByAuthorContainingIgnoreCase(String author, Pageable pageable);

    Page<Book> findByCategories_NameIgnoreCase(String categoryName, Pageable pageable);

    Page<Book> findAll(Pageable pageable);
}
