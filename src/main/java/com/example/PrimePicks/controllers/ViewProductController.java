package com.example.PrimePicks.controllers;

import com.example.PrimePicks.models.ProductModel;
import com.example.PrimePicks.repository.ProductModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Base64;


@RestController
public class ViewProductController {

    @Autowired
    private ProductModelRepository productModelRepository;

    @GetMapping("/product/{id}")
    public String viewProduct(@PathVariable Long id, Model model) {
        ProductModel product = productModelRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid product Id:" + id));
        String base64Image = Base64.getEncoder().encodeToString(product.getImage());
        model.addAttribute("product", product);
        model.addAttribute("image", base64Image);
        return "ProductView";
    }





}
