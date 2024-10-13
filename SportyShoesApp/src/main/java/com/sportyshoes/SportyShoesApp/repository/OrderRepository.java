package com.sportyshoes.SportyShoesApp.repository;

import com.sportyshoes.SportyShoesApp.entity.Order;
import com.sportyshoes.SportyShoesApp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Integer> {
    List<Order> findAllByUser(User user);
    List<Order> findAllByProduct_Category_Id(int id);
}
