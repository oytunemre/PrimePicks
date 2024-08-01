package com.example.PrimePicks.services;

import com.example.PrimePicks.models.ProductModel;
import com.example.PrimePicks.models.ShoppingCartModel;
import com.example.PrimePicks.models.UserModel;
import com.example.PrimePicks.repository.ProductModelRepository;
import com.example.PrimePicks.repository.ShoppingCartModelRepository;
import com.example.PrimePicks.repository.UserModelRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class ShoppingCartModelServiceImpl implements  ShoppingCartModelService{


    @Autowired
    private ShoppingCartModelRepository cartRepository;

    @Autowired
    private UserModelRepository userRepository;

    @Autowired
    private ProductModelRepository productModelRepository;


    @Override
    public ShoppingCartModel addProductToCart(Long userId, Long productId) {
        ShoppingCartModel cart = getCartByUserId(userId);
        if (cart == null) {
            UserModel user = userRepository.findById(userId).orElse(null);
            if (user == null) {
                throw new IllegalArgumentException("User not found");
            }
            cart = new ShoppingCartModel();
            cart.setUser(user);
            cart.setProductIds(new ArrayList<>());
            cart.setProducts(new ArrayList<>());
            cart.setProductQuantities(new HashMap<>());
            user.setShoppingCart(cart);
        }

        ProductModel product = productModelRepository.findById(productId).orElse(null);
        if (product == null) {
            throw new IllegalArgumentException("Product not found");
        }

        if (!cart.getProductIds().contains(productId)) {
            cart.getProductIds().add(productId);
            cart.getProducts().add(product);
            cart.getProductQuantities().put(productId, 1);
        } else {
            int currentQuantity = cart.getProductQuantities().getOrDefault(productId, 0);
            cart.getProductQuantities().put(productId, currentQuantity + 1);
        }
        return cartRepository.save(cart);
    }


    

    @Override
    public ShoppingCartModel removeProductFromCart(Long userId, Long productId) {
        ShoppingCartModel cart = getCartByUserId(userId);
        if (cart != null) {
            cart.getProductIds().remove(productId);
            cart.getProducts().removeIf(product -> product.getProductID().equals(productId));
            cart.getProductQuantities().remove(productId);
            return cartRepository.save(cart);
        }
        return null;
    }

    @Override
    public void clearCart(Long userId) {
        ShoppingCartModel cart = getCartByUserId(userId);
        if (cart != null) {
            cart.getProductIds().clear();
            cart.getProducts().clear();
            cart.getProductQuantities().clear();
            cartRepository.save(cart);
        }
    }

    @Transactional(readOnly = true)
    @Override
    public ShoppingCartModel findCartById(Long cartId) {
        Optional<ShoppingCartModel> optionalCart = cartRepository.findById(cartId);
        if (optionalCart.isPresent()) {
            ShoppingCartModel cart = optionalCart.get();
            cart.getProductQuantities().size();
            return cart;
        } else {
            return null;
        }
    }


    @Transactional(readOnly = true)
    public ShoppingCartModel getCartByUserId(Long userId) {
        ShoppingCartModel cart = cartRepository.findByUser_UserId(userId);

        if (cart != null) {
            cart.getProductQuantities().size();
        }
        return cart;
    }
    @Transactional
    public void updateQuantity(Long userId, Long productId, int quantity) {

        ShoppingCartModel cart = cartRepository.findByUser_UserId(userId);
        if (cart == null) {
            throw new IllegalArgumentException("Shopping cart not found for user ID: " + userId);
        }


        cart.getProductQuantities().put(productId, quantity);


        cart.calculateTotalPrice();

        cartRepository.save(cart);
    }

    public double calculateTotalPrice(ShoppingCartModel cart) {
        double totalPrice = 0.0;
        for (ProductModel product : cart.getProducts()) {
            int quantity = cart.getProductQuantities().getOrDefault(product.getProductID(), 0);
            Double price = product.getPrice();
            if (price != null) {
                totalPrice += price * quantity;
            } else {
                System.out.println("Product price is null for product ID: " + product.getProductID());
            }
        }
        return totalPrice;
    }


    public void processPayment(ShoppingCartModel cart) {
        cart.getProductIds().clear();
        cart.getProducts().clear();
        cart.getProductQuantities().clear();
        cartRepository.save(cart);
    }

    @Override
    public void saveCart(ShoppingCartModel cart) {
        cartRepository.save(cart);
    }

}