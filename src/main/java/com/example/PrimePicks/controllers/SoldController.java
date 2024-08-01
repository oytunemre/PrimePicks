package com.example.PrimePicks.controllers;

import com.example.PrimePicks.models.ProductModel;
import com.example.PrimePicks.models.UserModel;
import com.example.PrimePicks.services.SoldService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class SoldController {

    @Autowired
    private SoldService soldService;

    @PostMapping("/sell")
    public String sell(@RequestParam("productId") Long productId, HttpSession session, Model model) {
        UserModel loggedInUser = (UserModel) session.getAttribute("loggedInUser");
        if (loggedInUser == null) {
            return "redirect:/LoginPage";
        }

        try {
            ProductModel soldProduct = soldService.addSale(loggedInUser.getUserId(), productId);
            if (soldProduct == null) {
                model.addAttribute("error", "Product not found or unable to process sale.");
                return "redirect:/errorPage";
            }

            model.addAttribute("product", soldProduct);
            model.addAttribute("seller", loggedInUser);

            System.out.println("Item sold product id: " + productId + " logged in user: " + loggedInUser.getUserId());
            return "orderConfirmation";
        } catch (Exception e) {

            e.printStackTrace();
            model.addAttribute("error", "An error occurred while processing the sale.");
            return "redirect:/errorPage";
        }
    }


    @GetMapping("/soldProducts")
    public String getSoldProducts(HttpSession session, Model model) {
        UserModel loggedInUser = (UserModel) session.getAttribute("loggedInUser");
        if (loggedInUser == null) {
            return "redirect:/LoginPage";
        }

        List<ProductModel> soldProducts = soldService.getSoldProducts(loggedInUser.getUserId());
        model.addAttribute("soldProducts", soldProducts);

        return "soldProducts";
    }

}