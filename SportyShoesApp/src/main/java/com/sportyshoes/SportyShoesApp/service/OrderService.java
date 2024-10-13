package com.sportyshoes.SportyShoesApp.service;

import com.sportyshoes.SportyShoesApp.entity.Order;

import java.util.List;

public interface OrderService {
    String save(int productId);
    List<Order> findAllOrders();
    List<Order> getAllOrdersForUser();
    String approveOrder(int id);
    String cancelOrder(int id);
    List<Order> getOrdersByCategory(int categoryId);
}
