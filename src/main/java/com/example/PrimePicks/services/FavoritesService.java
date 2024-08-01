package com.example.PrimePicks.services;

import com.example.PrimePicks.models.FavoriteProducts;
import com.example.PrimePicks.models.ProductModel;
import com.example.PrimePicks.models.UserModel;
import com.example.PrimePicks.repository.FavoriteProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FavoritesService {

    @Autowired
    private FavoriteProductsRepository favoritesRepository;

    public List<ProductModel> getFavoriteProducts(Long userId) {
        return favoritesRepository.findByUserUserId(userId).stream()
                .map(FavoriteProducts::getProduct)
                .collect(Collectors.toList());
    }

    public void addToFavorites(UserModel user, Long productId) {
        FavoriteProducts favorite = new FavoriteProducts();
        favorite.setUser(user);
        favorite.setProduct(new ProductModel());
        favorite.getProduct().setProductID(productId);
        favoritesRepository.save(favorite);
    }

    public int countFavoritesByUserIdAndProductId(Long userId, Long productId) {
        return favoritesRepository.countByUserUserIdAndProductProductID(userId, productId);
    }
    public void removeFromFavorites(Long userId, Long productId) {
        FavoriteProducts favorite = favoritesRepository.findByUserUserIdAndProductProductID(userId, productId);
        if (favorite != null) {
            favoritesRepository.delete(favorite);
        }
    }

}
