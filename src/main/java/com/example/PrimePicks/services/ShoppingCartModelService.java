package com.example.PrimePicks.services;

import com.example.PrimePicks.models.ShoppingCartModel;

import java.util.List;

public interface ShoppingCartModelService {


    ShoppingCartModel getCartByUserId(Long userId);
    ShoppingCartModel addProductToCart(Long userId, Long productId);
    ShoppingCartModel removeProductFromCart(Long userId, Long productId);
    void clearCart(Long userId);

    ShoppingCartModel findCartById(Long cartId);
    public void saveCart(ShoppingCartModel cart);



}
