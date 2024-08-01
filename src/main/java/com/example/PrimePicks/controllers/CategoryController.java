package com.example.PrimePicks.controllers;

import com.example.PrimePicks.models.ProductModel;
import com.example.PrimePicks.services.ProductModelService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private HttpSession session;

    @Autowired
    private ProductModelService productModelService;

    @GetMapping("/{categoryName}")
    public String showCategory(@PathVariable String categoryName, Model model) {
        List<ProductModel> products = productModelService.getProductsByCategory(categoryName);

        model.addAttribute("products", products);
        model.addAttribute("categoryName", categoryName);

        return "mainPage";
    }
}
