package com.sportyshoes.SportyShoesApp.service.impl;

import com.sportyshoes.SportyShoesApp.entity.Order;
import com.sportyshoes.SportyShoesApp.entity.Product;
import com.sportyshoes.SportyShoesApp.entity.User;
import com.sportyshoes.SportyShoesApp.repository.OrderRepository;
import com.sportyshoes.SportyShoesApp.service.LoginService;
import com.sportyshoes.SportyShoesApp.service.OrderService;
import com.sportyshoes.SportyShoesApp.service.ProductService;
import com.sportyshoes.SportyShoesApp.type.OrderStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    private final ProductService productService;

    private final LoginService loginService;

    @Override
    public String save(int productId) {
        try {
            Product product = productService.findById(productId);
            User user = loginService.findLastLoginCustomer();
            Order order = new Order();
            order.setUser(user);
            order.setProduct(product);
            order.setStatus(OrderStatus.PENDING.value());
            orderRepository.save(order);
            return "Successfully ordered product.";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    @Override
    public List<Order> findAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    public List<Order> getAllOrdersForUser() {
        User user = loginService.findLastLoginCustomer();
        return orderRepository.findAllByUser(user);
    }

    @Override
    public String approveOrder(int id) {
        Optional<Order> order = orderRepository.findById(id);
        if (order.isPresent()) {
            Order o = order.get();
            o.setStatus(OrderStatus.APPROVED.value());
            orderRepository.save(o);
            return "Successfully approved the order.";
        } else {
            return "Order approval is not successful.";
        }
    }

    @Override
    public String cancelOrder(int id) {
        Optional<Order> order = orderRepository.findById(id);
        if (order.isPresent()) {
            Order o = order.get();
            o.setStatus(OrderStatus.CANCELLED.value());
            orderRepository.save(o);
            return "Successfully cancelled the order.";
        } else {
            return "Order cancellation is not successful.";
        }
    }

    @Override
    public List<Order> getOrdersByCategory(int categoryId) {
        return orderRepository.findAllByProduct_Category_Id(categoryId);
    }
}
