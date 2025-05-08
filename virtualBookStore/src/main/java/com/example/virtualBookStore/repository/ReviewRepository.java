package com.example.virtualBookStore.repository;

import com.example.virtualBookStore.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review,Long> {
    List<Review> findByBook_Id(Long bookId);
    List<Review> findByBook_TitleIgnoreCase(String title);
}
