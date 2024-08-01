package com.example.PrimePicks.controllers;

import com.example.PrimePicks.models.ProductModel;
import com.example.PrimePicks.models.SoldProducts;
import com.example.PrimePicks.models.UserModel;
import com.example.PrimePicks.repository.ProductModelRepository;
import com.example.PrimePicks.repository.UserModelRepository;
import com.example.PrimePicks.services.*;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping
public class UserModelController {

    @Autowired
    private FavoritesService favoritesService;

    @Autowired
    private PurchasedService purchasedService;

    @Autowired
    private SoldService soldService;

    @GetMapping("/userProfile")
    public String getUserProfile(Model model, HttpSession session) {
        UserModel user = (UserModel) session.getAttribute("loggedInUser");
        if (user == null) {
            return "redirect:/LoginPage";
        }
        Long userId = user.getUserId();

        List<SoldProducts> soldProducts = soldService.getSalesBySeller(userId);

        model.addAttribute("user", user);
        model.addAttribute("favoriteProducts", favoritesService.getFavoriteProducts(userId));
        model.addAttribute("purchasedProducts", purchasedService.getPurchasedProducts(userId));
        model.addAttribute("soldProducts", soldService.getSoldProducts(userId));

        return "userProfile";
    }

}
