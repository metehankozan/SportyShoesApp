package com.sportyshoes.SportyShoesApp.service.impl;

import com.sportyshoes.SportyShoesApp.entity.Category;
import com.sportyshoes.SportyShoesApp.repository.CategoryRepository;
import com.sportyshoes.SportyShoesApp.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public String save(Category category) {
        String capitalizedCategoryName = capitalizeCategoryName(category.getCategoryName());
        category.setCategoryName(capitalizedCategoryName);

        Optional<Category> c = categoryRepository.findByCategoryName(category.getCategoryName());
        if (c.isPresent()) {
            return "Category name " + category.getCategoryName() + " is already present.";
        }
        try {
            categoryRepository.save(category);
            return "Category " + category.getCategoryName() + " is successfully saved.";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    @Override
    public Category findById(int id) {
        return categoryRepository.findById(id).get();
    }

    @Override
    public List<Category> findAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public String deleteCategory(int id) {
        try {
            categoryRepository.deleteById(id);
            return "Successfully deleted category.";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    @Override
    public String updateCategory(Category category) {
        Optional<Category> c = categoryRepository.findById(category.getId());
        if (c.isPresent()) {
            return save(category);
        } else {
            return "Category could not be updated.";
        }
    }

    private String capitalizeCategoryName(String categoryName){
        return categoryName.substring(0,1).toUpperCase() +
                categoryName.substring(1).toLowerCase();
    };
}
