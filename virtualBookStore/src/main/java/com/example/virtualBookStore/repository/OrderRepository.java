package com.example.virtualBookStore.repository;

import com.example.virtualBookStore.model.Order;
import com.example.virtualBookStore.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order,Long> {
    List<Order> findByUser(User user);
}
