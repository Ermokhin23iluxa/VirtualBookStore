package com.example.virtualBookStore.repository;

import com.example.virtualBookStore.model.Book;
import com.example.virtualBookStore.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review,Long> {
    List<Review> findByBook(Book book);
}
