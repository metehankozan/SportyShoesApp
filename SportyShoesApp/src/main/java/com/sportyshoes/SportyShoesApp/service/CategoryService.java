package com.sportyshoes.SportyShoesApp.service;

import com.sportyshoes.SportyShoesApp.entity.Category;

import java.util.List;

public interface CategoryService {

    String save(Category category);

    Category findById(int id);

    List<Category> findAllCategories();

    String deleteCategory(int id);

    String updateCategory(Category category);
}
