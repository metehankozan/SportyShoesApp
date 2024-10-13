package com.sportyshoes.SportyShoesApp.controller;

import com.sportyshoes.SportyShoesApp.dto.response.SignUpResponse;
import com.sportyshoes.SportyShoesApp.entity.Category;
import com.sportyshoes.SportyShoesApp.entity.Product;
import com.sportyshoes.SportyShoesApp.entity.User;
import com.sportyshoes.SportyShoesApp.service.CategoryService;
import com.sportyshoes.SportyShoesApp.service.LoginService;
import com.sportyshoes.SportyShoesApp.service.ProductService;
import com.sportyshoes.SportyShoesApp.type.UserType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class LoginController {
    private final LoginService loginService;
    private final CategoryService categoryService;
    private final ProductService productService;

    @GetMapping
    public String loginPage(User u, Model m) {
        m.addAttribute("user", u);
        return "login";
    }

    @GetMapping("sign-up")
    public String signUpPage(User u, Model m) {
        m.addAttribute("user", u);
        return "signup";
    }

    @PostMapping("sign-in")
    public String signIn(User u, Model m) {
        User user = loginService.signIn(u);
        m.addAttribute("user", user);
        List<Category> categories = categoryService.findAllCategories();
        m.addAttribute("categories", categories);
        List<Product> products = productService.findAllProducts();
        m.addAttribute("products", products);
        if(user.getUserType().equals(UserType.ADMIN.value())) {
            return "admin-panel";
        }else {
            return "home";
        }
    }

    @PostMapping("sign-up")
    public String signUp(User u, Model m) {
        m.addAttribute("user", u);
        SignUpResponse result = loginService.signUp(u);
        m.addAttribute("msg", result.getMessage());
        if (result.isSuccess()) {
            return "login";
        } else {
            return "signup";
        }
    }

    @GetMapping("admin-panel")
    private String getAdminPanel() {
        return "admin-panel";
    }
}
