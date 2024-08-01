package com.example.PrimePicks.controllers;

import com.example.PrimePicks.models.FavoriteProducts;
import com.example.PrimePicks.models.UserModel;
import com.example.PrimePicks.repository.FavoriteProductsRepository;
import com.example.PrimePicks.services.FavoritesService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class FavoritesController {

    @Autowired
    private FavoritesService favoritesService;


    @Autowired
    private FavoriteProductsRepository favoritesRepository;


    @PostMapping("/addFavorite")
    public String addFavorite(@RequestParam("productId") Long productId,
                              @RequestParam("redirect") String redirect,
                              HttpSession session) {
        UserModel loggedInUser = (UserModel) session.getAttribute("loggedInUser");
        if (loggedInUser == null) {
            return "redirect:/LoginPage";
        }
        Long userId = loggedInUser.getUserId();

        if (favoritesService.countFavoritesByUserIdAndProductId(userId, productId) >= 1) {
            System.out.println("You cannot add same items");
            return "redirect:" + redirect;
        }

        favoritesService.addToFavorites(loggedInUser, productId);
        System.out.println("Item added into favorites product id :" + productId + " by user,user id:" + userId);
        return "redirect:" + redirect;
    }

    public void removeFromFavorites(Long userId, Long productId) {
        FavoriteProducts favorite = favoritesRepository.findByUserUserIdAndProductProductID(userId, productId);
        if (favorite != null) {
            favoritesRepository.delete(favorite);
        }
    }

    @PostMapping("/removeFavorite")
    public String removeFavorite(@RequestParam("productId") Long productId,
                                 @RequestParam(value = "redirect", defaultValue = "/defaultPage") String redirect,
                                 HttpSession session) {
        UserModel loggedInUser = (UserModel) session.getAttribute("loggedInUser");
        if (loggedInUser == null) {
            return "redirect:/LoginPage";
        }

        Long userId = loggedInUser.getUserId();
        favoritesService.removeFromFavorites(userId, productId);
        return "redirect:" + redirect;
    }


}
