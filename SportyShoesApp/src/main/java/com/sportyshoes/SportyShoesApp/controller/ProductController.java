package com.sportyshoes.SportyShoesApp.controller;

import com.sportyshoes.SportyShoesApp.entity.Category;
import com.sportyshoes.SportyShoesApp.entity.Order;
import com.sportyshoes.SportyShoesApp.entity.Product;
import com.sportyshoes.SportyShoesApp.entity.User;
import com.sportyshoes.SportyShoesApp.service.CategoryService;
import com.sportyshoes.SportyShoesApp.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    private final CategoryService categoryService;

    @GetMapping("product-management")
    public String addProduct(Product p, Model m) {
        m.addAttribute("product", p);
        List<Category> allCategories = categoryService.findAllCategories();
        m.addAttribute("categories", allCategories);
        List<Product> products = productService.findAllProducts();
        m.addAttribute("products", products);
        m.addAttribute("b", "Save");
        m.addAttribute("operation", "Add New Product");
        return "product-management";
    }

    @PostMapping("add-product")
    public String saveProduct(Product product, Model m, @RequestParam("submitButton") String buttonValue) {
        String result;
        if(buttonValue.equals("Save")) {
            result = productService.save(product);
        }else {
            result = productService.updateProduct(product);
        }
        m.addAttribute("msg", result);
        product.setId(0);
        product.setProductName("");
        product.setBrand("");
        product.setPrice(0d);
        product.setCategory(new Category());
        List<Category> allCategories = categoryService.findAllCategories();
        m.addAttribute("categories", allCategories);
        List<Product> products = productService.findAllProducts();
        m.addAttribute("products", products);
        m.addAttribute("b", "Save");
        m.addAttribute("operation", "Add New Product");
        return "product-management";
    }

    @GetMapping("delete-product")
    public String deleteProduct(Product p, Model m, @RequestParam("id") int id) {
        String result = productService.deleteById(id);
        m.addAttribute("msg", result);
        List<Category> allCategories = categoryService.findAllCategories();
        m.addAttribute("categories", allCategories);
        List<Product> products = productService.findAllProducts();
        m.addAttribute("products", products);
        m.addAttribute("b", "Save");
        m.addAttribute("operation", "Add New Product");
        return "product-management";
    }

    @GetMapping("update-product")
    public String updateProduct(Product p, Model m, @RequestParam("id") int id) {
        Product product = productService.findById(id);
        m.addAttribute("product", product);
        List<Category> allCategories = categoryService.findAllCategories();
        m.addAttribute("categories", allCategories);
        List<Product> products = productService.findAllProducts();
        m.addAttribute("products", products);
        m.addAttribute("b", "Update");
        m.addAttribute("operation", "Update Product");
        return "product-management";
    }

    @GetMapping("products")
    public String getProductsByCategory(User u, Order o, Model m, @RequestParam("categoryId") int categoryId) {
        List<Product> products = productService.findByCategoryId(categoryId);
        m.addAttribute("products", products);
        List<Category> categories = categoryService.findAllCategories();
        m.addAttribute("categories", categories);
        m.addAttribute("order", o);
        m.addAttribute("user", u);
        return "home";
    }
}
