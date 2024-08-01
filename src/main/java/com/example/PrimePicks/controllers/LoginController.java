package com.example.PrimePicks.controllers;

import com.example.PrimePicks.models.ProductModel;
import com.example.PrimePicks.models.ShoppingCartModel;
import com.example.PrimePicks.models.UserModel;
import com.example.PrimePicks.repository.ProductModelRepository;
import com.example.PrimePicks.repository.ShoppingCartModelRepository;
import com.example.PrimePicks.services.*;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping()
public class LoginController {

    @Autowired
    private HttpSession session;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private UserModelServiceImpl userModelServiceImpl;

    @Autowired
    private ProductModelImpl productModelImp;

    @Autowired
    private ProductModelService productModelService;

    @Autowired
    private ShoppingCartModelRepository cartRepository;
    @Autowired
    private ProductModelRepository productModelRepository;
    @Autowired
    private ShoppingCartModelServiceImpl shoppingCartModelServiceImpl;
    @Autowired
    private ProductModelImpl productModelImpl;

    @ModelAttribute("UserModel")
    public UserModel userModel() {
        return new UserModel();
    }

    @GetMapping("/LoginPage")
    public String login(Model model) {
        model.addAttribute("UserModel", new UserModel());
        return "LoginPage";
    }

    @PostMapping("/LoginPage")
    public String authenticate(@ModelAttribute("UserModel") UserModel userModel, BindingResult bindingResult, Model model, HttpSession session) {
        if (bindingResult.hasErrors()) {
            return "LoginPage";
        }

        UserModel existingUser = userModelServiceImpl.findByUsername(userModel.getUsername());
        if (existingUser == null) {
            model.addAttribute("error", "User does not exist");
            return "LoginPage";
        }

        boolean passwordMatches = passwordEncoder.matches(userModel.getPassword(), existingUser.getPassword());
        if (!passwordMatches) {
            model.addAttribute("error", "Invalid username or password");
            return "LoginPage";
        }

        session.setAttribute("loggedInUser", existingUser);
        return "redirect:/UserPage";
    }

    @GetMapping("/UserPage")
    public String showUserPage(Model model, @RequestParam(defaultValue = "dateDesc") String sort) {
        UserModel loggedInUser = (UserModel) session.getAttribute("loggedInUser");

        if (loggedInUser == null) {
            return "redirect:/LoginPage";
        }

        ShoppingCartModel cart = shoppingCartModelServiceImpl.getCartByUserId(loggedInUser.getUserId());



        if (cart == null) {
            cart = new ShoppingCartModel();
            cart.setUser(loggedInUser);
            loggedInUser.setShoppingCart(cart);
            shoppingCartModelServiceImpl.saveCart(cart);
        }

        int cartSize = (cart != null && cart.getProducts() != null) ? cart.getProducts().size() : 0;
        List<ProductModel> otherUsersProducts = productModelImp.getOtherUsersProductsSorted(loggedInUser.getUserId(), "UserPage");


        model.addAttribute("cart", cart);
        model.addAttribute("cartSize", cartSize);
        model.addAttribute("user", loggedInUser);
        model.addAttribute("otherUsersProducts", otherUsersProducts);

        return "UserPage";
    }


    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }

}
