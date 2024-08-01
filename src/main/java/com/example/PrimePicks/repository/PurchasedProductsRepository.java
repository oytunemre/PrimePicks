package com.example.PrimePicks.repository;

import com.example.PrimePicks.models.PurchasedProducts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PurchasedProductsRepository extends JpaRepository<PurchasedProducts, Long> {
    List<PurchasedProducts> findByUserUserId(Long userId);
}