package com.example.PrimePicks.controllers;

import com.example.PrimePicks.models.ProductModel;
import com.example.PrimePicks.models.UserModel;
import com.example.PrimePicks.services.ProductModelService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class SearchController {

    @Autowired
    private ProductModelService productService;

    @GetMapping("/search")
    public String search(@RequestParam("query") String query, Model model, HttpSession session) {
        UserModel loggedInUser = (UserModel) session.getAttribute("loggedInUser");
        model.addAttribute("user", loggedInUser);
        List<ProductModel> products = productService.searchProducts(query);
        model.addAttribute("products", products);
        return "mainPage";
    }
}
