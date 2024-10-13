package com.sportyshoes.SportyShoesApp.service.impl;

import com.sportyshoes.SportyShoesApp.entity.Product;
import com.sportyshoes.SportyShoesApp.repository.ProductRepository;
import com.sportyshoes.SportyShoesApp.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public String save(Product product) {
        try {
            productRepository.save(product);
            return "Successfully saved the product : " + product.getProductName();
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    @Override
    public Product findById(int id) {
        return productRepository.findById(id).get();
    }

    @Override
    public List<Product> findAllProducts() {
        List<Product> products = productRepository.findAll();
        return products;
    }

    @Override
    public String deleteById(int id) {
        try {
            productRepository.deleteById(id);
            return "Successfully deleted product.";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    @Override
    public String updateProduct(Product product) {
        Optional<Product> p = productRepository.findById(product.getId());
        if (p.isPresent()) {
            return save(product);
        } else {
            return "Product could not be updated.";
        }
    }

    @Override
    public List<Product> findByCategoryId(int categoryId) {
        return productRepository.findByCategoryId(categoryId);
    }
}
