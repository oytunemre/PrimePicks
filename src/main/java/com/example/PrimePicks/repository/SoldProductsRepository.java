package com.example.PrimePicks.repository;

import com.example.PrimePicks.models.SoldProducts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SoldProductsRepository extends JpaRepository<SoldProducts, Long> {
    List<SoldProducts> findBySellerUserId(Long sellerId);
}
