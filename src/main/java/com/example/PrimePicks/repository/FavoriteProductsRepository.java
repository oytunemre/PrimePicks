package com.example.PrimePicks.repository;

import com.example.PrimePicks.models.FavoriteProducts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FavoriteProductsRepository extends JpaRepository<FavoriteProducts, Long> {
    List<FavoriteProducts> findByUserUserId(Long userId);

    int countByUserUserIdAndProductProductID(Long userId, Long productId);

    FavoriteProducts findByUserUserIdAndProductProductID(Long userId, Long productId);


}