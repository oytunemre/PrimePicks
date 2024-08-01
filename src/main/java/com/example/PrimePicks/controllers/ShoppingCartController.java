package com.example.PrimePicks.controllers;

import com.example.PrimePicks.models.*;
import com.example.PrimePicks.services.PurchasedService;
import com.example.PrimePicks.services.ShoppingCartModelServiceImpl;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
@RequestMapping
public class ShoppingCartController {

    @Autowired
    private ShoppingCartModelServiceImpl shoppingCartModelServiceImpl;

    @Autowired
    private PurchasedService purchasedService;

    @PostMapping("/addToCart")
    public String addToCart(HttpSession session, @RequestParam("id") Long productId) {
        UserModel loggedInUser = (UserModel) session.getAttribute("loggedInUser");

        if (loggedInUser == null) {
            return "redirect:/LoginPage";
        }

        ShoppingCartModel cart = shoppingCartModelServiceImpl.addProductToCart(loggedInUser.getUserId(), productId);

        shoppingCartModelServiceImpl.saveCart(cart);

        return "redirect:/UserPage";
    }

    @GetMapping("/ShoppingCart")
    public String viewCart(HttpSession session, Model model) {
        UserModel loggedInUser = (UserModel) session.getAttribute("loggedInUser");

        if (loggedInUser == null) {
            return "redirect:/LoginPage";
        }

        ShoppingCartModel cart = shoppingCartModelServiceImpl.getCartByUserId(loggedInUser.getUserId());
        if (cart == null) {
            cart = new ShoppingCartModel();
            cart.setProductIds(new ArrayList<>());
            cart.setProducts(new ArrayList<>());
            cart.setProductQuantities(new HashMap<>());
            loggedInUser.setShoppingCart(cart);
        } else {
            if (cart.getProductQuantities() == null) {
                cart.setProductQuantities(new HashMap<>());
            }
        }

        double totalPrice = shoppingCartModelServiceImpl.calculateTotalPrice(cart);
        int cartSize = (cart != null && cart.getProducts() != null) ? cart.getProducts().size() : 0;

        model.addAttribute("cart", cart);
        model.addAttribute("cartSize", cartSize);
        model.addAttribute("totalPrice", totalPrice);
        model.addAttribute("user", loggedInUser);

        return "ShoppingCart";
    }

    @PostMapping("/updateQuantity")
    @ResponseBody
    public Map<String, Object> updateQuantity(HttpSession session, @RequestParam("productId") Long productId, @RequestParam("quantity") int quantity) {
        UserModel loggedInUser = (UserModel) session.getAttribute("loggedInUser");

        if (loggedInUser == null) {
            return Map.of("redirect", "LoginPage");
        }

        shoppingCartModelServiceImpl.updateQuantity(loggedInUser.getUserId(), productId, quantity);


        ShoppingCartModel cart = shoppingCartModelServiceImpl.getCartByUserId(loggedInUser.getUserId());
        double totalPrice = shoppingCartModelServiceImpl.calculateTotalPrice(cart);


        Map<String, Object> response = new HashMap<>();
        response.put("totalPrice", totalPrice);
        return response;
    }

    @PostMapping("/removeFromCart")
    public String removeFromCart(@RequestParam Long productId, HttpSession session) {
        UserModel loggedInUser = (UserModel) session.getAttribute("loggedInUser");

        if (loggedInUser == null) {
            return "redirect:/LoginPage";
        }

        shoppingCartModelServiceImpl.removeProductFromCart(loggedInUser.getUserId(), productId);

        return "redirect:/ShoppingCart";
    }

    @PostMapping("/checkout")
    public String checkout(HttpSession session, Model model) {
        UserModel loggedInUser = (UserModel) session.getAttribute("loggedInUser");
        if (loggedInUser == null) {
            return "redirect:/LoginPage";
        }

        ShoppingCartModel cart = shoppingCartModelServiceImpl.getCartByUserId(loggedInUser.getUserId());
        if (cart == null || cart.getProducts().isEmpty()) {
            model.addAttribute("error", "Your cart is empty!");
            return "ShoppingCart";
        }

        boolean success = purchasedService.processCheckout(loggedInUser, cart);
        if (success) {
            model.addAttribute("message", "Checkout successful!");
            List<ProductModel> purchasedProducts = cart.getProducts();
            model.addAttribute("purchasedProducts", purchasedProducts);
            model.addAttribute("user", loggedInUser);

            shoppingCartModelServiceImpl.clearCart(cart.getId());

            return "OrderConfirmation";
        } else {
            model.addAttribute("error", "Checkout failed! Please try again.");
            return "ShoppingCart";
        }
    }

}
