package com.sportyshoes.SportyShoesApp.controller;

import com.sportyshoes.SportyShoesApp.entity.Category;
import com.sportyshoes.SportyShoesApp.entity.Order;
import com.sportyshoes.SportyShoesApp.entity.Product;
import com.sportyshoes.SportyShoesApp.service.CategoryService;
import com.sportyshoes.SportyShoesApp.service.OrderService;
import com.sportyshoes.SportyShoesApp.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    private final CategoryService categoryService;

    private final ProductService productService;

    @GetMapping("order")
    public String order(Model m, @RequestParam("productId") int productId){
        String result = orderService.save(productId);
        m.addAttribute("msg", result);
        List<Category> categories = categoryService.findAllCategories();
        m.addAttribute("categories", categories);
        List<Product> products = productService.findAllProducts();
        m.addAttribute("products", products);
        return "home";
    }

    @GetMapping("orders")
    public String getAllOrders(Model m){
        List<Order> orders = orderService.getAllOrdersForUser();
        m.addAttribute("orders", orders);
        return "order-list";
    }

    @GetMapping("order-management")
    public String allOrders(Model m) {
        List<Order> orders = orderService.findAllOrders();
        m.addAttribute("orders", orders);
        List<Category> categories = categoryService.findAllCategories();
        m.addAttribute("categories", categories);
        return "order-management";
    }

    @GetMapping("approve-order")
    public String approveOrder(Model m, @RequestParam("id") int id) {
        String result = orderService.approveOrder(id);
        m.addAttribute("msg", result);
        List<Order> orders = orderService.findAllOrders();
        m.addAttribute("orders", orders);
        return "order-management";
    }

    @GetMapping("cancel-order")
    public String cancelOrder(Model m, @RequestParam("id") int id) {
        String result = orderService.cancelOrder(id);
        m.addAttribute("msg", result);
        List<Order> orders = orderService.findAllOrders();
        m.addAttribute("orders", orders);
        return "order-management";
    }

    @GetMapping("orders-by-category")
    public String getOrdersByCategory(Model m, @RequestParam("categoryId") int categoryId) {
        List<Order> orders = orderService.getOrdersByCategory(categoryId);
        m.addAttribute("orders", orders);
        List<Category> categories = categoryService.findAllCategories();
        m.addAttribute("categories", categories);
        return "order-management";
    }
}
