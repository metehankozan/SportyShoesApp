package com.sportyshoes.SportyShoesApp.controller;

import com.sportyshoes.SportyShoesApp.entity.Category;
import com.sportyshoes.SportyShoesApp.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping("category-management")
    public String addCategory(Category c, Model m) {
        m.addAttribute("category", c);
        List<Category> allCategories = categoryService.findAllCategories();
        m.addAttribute("categories", allCategories);
        m.addAttribute("b", "Save");
        m.addAttribute("operation", "Add New Category");
        return "category-management";
    }

    @PostMapping("add-category")
    public String saveCategory(Category category, Model m, @RequestParam("submitButton") String buttonValue) {
        String result;
        if(buttonValue.equals("Save")) {
            result = categoryService.save(category);
        }else {
            result = categoryService.updateCategory(category);
        }
        category.setId(0);
        category.setCategoryName("");
        m.addAttribute("category", category);
        m.addAttribute("msg", result);
        List<Category> allCategories = categoryService.findAllCategories();
        m.addAttribute("categories", allCategories);
        m.addAttribute("b", "Save");
        m.addAttribute("operation", "Add New Category");
        return "category-management";
    }

    @GetMapping("delete-category")
    public String deleteProduct(Category c, Model m, @RequestParam("id") int id) {
        String result = categoryService.deleteCategory(id);
        m.addAttribute("msg", result);
        List<Category> allCategories = categoryService.findAllCategories();
        m.addAttribute("categories", allCategories);
        m.addAttribute("b", "Save");
        m.addAttribute("operation", "Add New Category");
        return "category-management";
    }

    @GetMapping("update-category")
    public String updateProduct(Category c, Model m, @RequestParam("id") int id) {
        Category category = categoryService.findById(id);
        m.addAttribute("category", category);
        List<Category> allCategories = categoryService.findAllCategories();
        m.addAttribute("categories", allCategories);
        m.addAttribute("b", "Update");
        m.addAttribute("operation", "Update Category");
        return "category-management";
    }
}
