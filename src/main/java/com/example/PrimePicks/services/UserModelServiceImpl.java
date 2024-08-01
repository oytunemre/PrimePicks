package com.example.PrimePicks.services;


import com.example.PrimePicks.models.*;
import com.example.PrimePicks.repository.ProductModelRepository;
import com.example.PrimePicks.repository.UserModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;

@Service
public class UserModelServiceImpl implements UserModelService {


    @Autowired
    private UserModelRepository userModelRepository;


    @Autowired
    public BCryptPasswordEncoder passwordEncoder;
    @Autowired
    private ProductModelRepository productModelRepository;



    @Override
    public void saveUser(UserModel userModel) {
        String hashedPassword = passwordEncoder.encode(userModel.getPassword());
        userModel.setPassword(hashedPassword);


        ShoppingCartModel cart = new ShoppingCartModel();
        cart.setUser(userModel);
        userModel.setShoppingCart(cart);

        userModelRepository.save(userModel);
    }

    @Override
    public List<UserModel> ListAllUsers() {
        List<UserModel> listUsers = userModelRepository.findAll();
        return listUsers;
    }

    @Override
    public Optional<UserModel> getUsertById(Long id) {
        Optional<UserModel> users = userModelRepository.findById(id);
        if (users.isPresent()) {
            return Optional.of(users.get());
        }
        return null;
    }



    @Override
    public String deleteUserById(Long id) {
        return "";
    }

    @Override
    public UserModel findByUsername(String username) {
        return userModelRepository.findByUsername(username);
    }

    @Override
    public boolean checkCredentials(UserModel userModel) {
        UserModel existingUser = findByUsername(userModel.getUsername());
        return existingUser != null && existingUser.getPassword().equals(userModel.getPassword());
    }

    @Override
    public void addToFavorites(String username, Long productId) {

    }

    @Override
    public void removeFromFavorites(String username, Long productId) {

    }

    @Override
    public Set<ProductModel> getFavoriteProducts(String username) {
        return Set.of();
    }

    @Override
    public Set<ProductModel> getPurchasedProducts(String username) {
        return Set.of();
    }

    @Override
    public Set<ProductModel> getSoldProducts(String username) {
        return Set.of();
    }



}



