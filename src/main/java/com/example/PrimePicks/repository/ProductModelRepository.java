package com.example.PrimePicks.repository;

import com.example.PrimePicks.models.ProductModel;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.beans.JavaBean;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProductModelRepository extends JpaRepository<ProductModel,Long> {


    List<ProductModel> findAllByUserIdNot(Long userId);


//    @Query("SELECT p FROM ProductModel p WHERE p.user.userId = :userId")
//    List<ProductModel> findProductsByUserId(@Param("userId") Long userId);
//
//
//
//    @Query("SELECT p FROM ProductModel p WHERE p.productID = :productId")
//    Optional<ProductModel> findById(@Param("productId") Long productId);
//
//    @Query("SELECT p FROM ProductModel p WHERE p.user.userId = :sellerId")
//    List<ProductModel> findBySellerId(@Param("sellerId") Long sellerId);



    @Query("SELECT p FROM ProductModel p WHERE p.category = :category")
    List<ProductModel> findProductsByCategory(@Param("category") String category);

    List<ProductModel> findAllByOrderByPriceAsc();
    List<ProductModel> findAllByOrderByPriceDesc();
    List<ProductModel> findAllByOrderByProductNameAsc();
    List<ProductModel> findAllByOrderByProductNameDesc();
    List<ProductModel> findAllByOrderByDateOfListingProductAsc();
    List<ProductModel> findAllByOrderByDateOfListingProductDesc();

    List<ProductModel> findAllByUserIdNotOrderByPriceAsc(Long userId);
    List<ProductModel> findAllByUserIdNotOrderByPriceDesc(Long userId);
    List<ProductModel> findAllByUserIdNotOrderByProductNameAsc(Long userId);
    List<ProductModel> findAllByUserIdNotOrderByProductNameDesc(Long userId);
    List<ProductModel> findAllByUserIdNotOrderByDateOfListingProductAsc(Long userId);
    List<ProductModel> findAllByUserIdNotOrderByDateOfListingProductDesc(Long userId);


    List<ProductModel> findByProductNameContainingIgnoreCase(String productName);



}


