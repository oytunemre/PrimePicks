package com.example.PrimePicks.repository;

import com.example.PrimePicks.models.OrderModel;
import com.example.PrimePicks.models.ProductModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductModelRepository extends JpaRepository<ProductModel,Long> {


    Optional<ProductModel> findProduct(String product);



}
