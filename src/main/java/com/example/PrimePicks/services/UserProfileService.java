package com.example.PrimePicks.services;

import com.example.PrimePicks.models.FavoriteProducts;
import com.example.PrimePicks.models.ProductModel;
import com.example.PrimePicks.models.PurchasedProducts;
import com.example.PrimePicks.models.SoldProducts;
import com.example.PrimePicks.repository.FavoriteProductsRepository;
import com.example.PrimePicks.repository.PurchasedProductsRepository;
import com.example.PrimePicks.repository.SoldProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserProfileService {

    @Autowired
    private FavoriteProductsRepository favoritesRepository;

    @Autowired
    private PurchasedProductsRepository purchasedRepository;

    @Autowired
    private SoldProductsRepository soldRepository;

    public List<ProductModel> getFavoriteProducts(Long userId) {
        return favoritesRepository.findByUserUserId(userId).stream()
                .map(FavoriteProducts::getProduct)
                .collect(Collectors.toList());
    }

    public List<ProductModel> getPurchasedProducts(Long userId) {
        return purchasedRepository.findByUserUserId(userId).stream()
                .map(PurchasedProducts::getProduct)
                .collect(Collectors.toList());
    }

    public List<ProductModel> getSoldProducts(Long userId) {
        return soldRepository.findBySellerUserId(userId).stream()
                .map(SoldProducts::getProduct)
                .collect(Collectors.toList());
    }
}
