package com.example.PrimePicks.controllers;

import com.example.PrimePicks.models.ProductModel;
import com.example.PrimePicks.models.ShoppingCartModel;
import com.example.PrimePicks.models.UserModel;
import com.example.PrimePicks.services.ProductModelImpl;
import com.example.PrimePicks.services.ProductModelService;
import com.example.PrimePicks.services.ShoppingCartModelServiceImpl;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@Controller
public class MainPageController {

    @Autowired
    ProductModelService productModelService;
    @Autowired
    private ProductModelImpl productModelImpl;
    @Autowired
    private ShoppingCartModelServiceImpl shoppingCartModelService;


    @GetMapping("/aboutPage")
    public String showAboutPage(Model model,HttpSession session) {
        UserModel loggedInUser = (UserModel) session.getAttribute("loggedInUser");
        model.addAttribute("user", loggedInUser);

        return "AboutPage";
    }


    @GetMapping("/")
    public String mainPage(Model model, @RequestParam(defaultValue = "dateDesc") String sort, HttpSession session) {
        List<ProductModel> products = productModelImpl.getProductsSorted(sort);
        model.addAttribute("products", products);

        UserModel loggedInUser = (UserModel) session.getAttribute("loggedInUser");
        model.addAttribute("user", loggedInUser);

        int cartSize = 0;
        if (loggedInUser != null) {
            ShoppingCartModel cart = shoppingCartModelService.getCartByUserId(loggedInUser.getUserId());
            if (cart != null) {
                cartSize = cart.getProducts().size();
            }
        }

        model.addAttribute("cartSize", cartSize);
        return "mainPage";
    }



}
