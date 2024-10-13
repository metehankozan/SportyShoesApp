package com.sportyshoes.SportyShoesApp.service;

import com.sportyshoes.SportyShoesApp.entity.Product;

import java.util.List;

public interface ProductService {
    String save(Product product);

    Product findById(int id);

    List<Product> findAllProducts();

    String deleteById(int id);

    String updateProduct(Product product);

    List<Product> findByCategoryId(int categoryId);
}
