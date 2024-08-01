package com.example.PrimePicks.services;

import com.example.PrimePicks.models.ProductModel;
import com.example.PrimePicks.models.UserModel;

import java.util.List;
import java.util.Optional;
import java.util.Set;


public interface UserModelService {


    void saveUser(UserModel userModel);  // Save Products in the table

    List<UserModel> ListAllUsers(); // List all Products

    Optional<UserModel> getUsertById(Long id); // get Product ID

    String deleteUserById(Long id); // Delete Products by ID

    UserModel findByUsername(String username);

    boolean checkCredentials(UserModel userModel);

    public void addToFavorites(String username, Long productId);

    public void removeFromFavorites(String username, Long productId);

    public Set<ProductModel> getFavoriteProducts(String username);

    public Set<ProductModel> getPurchasedProducts(String username);

    public Set<ProductModel> getSoldProducts(String username);



}
