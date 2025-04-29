package com.example.virtualBookStore.repository;

import com.example.virtualBookStore.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book,Long> {

}
