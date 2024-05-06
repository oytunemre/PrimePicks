package com.example.PrimePicks.controllers;

import com.example.PrimePicks.dto.ProductModelDTO;
import com.example.PrimePicks.models.ProductModel;
import com.example.PrimePicks.repository.ProductModelRepository;
import com.example.PrimePicks.services.ProductModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import java.util.List;

@Controller
public class ProductModelController {


    private ProductModelService productModelService;


    @Autowired
    public ProductModelController(ProductModelService productModelService) {
        this.productModelService = productModelService;
    }


public  String listProducts(Model model){

    List<ProductModelDTO> productModels = productModelService.listAllProducts();
    model.addAttribute("Products:",productModels);
    return "product-list";


}



}
