package com.example.PrimePicks.controllers;

import com.example.PrimePicks.models.UserModel;
import com.example.PrimePicks.services.PurchasedService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PurchasedController {

    @Autowired
    private PurchasedService purchasedService;

    @PostMapping("/purchase")
    public String purchaseProduct(@RequestParam("productId") Long productId,
                                  @RequestParam(value = "redirect", defaultValue = "/userProfile") String redirect,
                                  HttpSession session) {
        UserModel loggedInUser = (UserModel) session.getAttribute("loggedInUser");
        if (loggedInUser == null) {
            return "redirect:/LoginPage";
        }

        purchasedService.purchaseProduct(loggedInUser, productId);
        return "redirect:" + redirect;
    }


}
