package com.example.PrimePicks.services;

import com.example.PrimePicks.models.ProductModel;
import com.example.PrimePicks.models.SoldProducts;
import com.example.PrimePicks.models.UserModel;
import com.example.PrimePicks.repository.ProductModelRepository;
import com.example.PrimePicks.repository.SoldProductsRepository;
import com.example.PrimePicks.repository.UserModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SoldService {

    @Autowired
    private SoldProductsRepository soldRepository;

    @Autowired
    private ProductModelRepository productRepository;

    @Autowired
    private UserModelRepository userRepository;

    public ProductModel addSale(Long sellerId, Long productId) {
        UserModel seller = userRepository.findById(sellerId).orElseThrow(() -> new RuntimeException("Seller not found"));
        ProductModel product = productRepository.findById(productId).orElseThrow(() -> new RuntimeException("Product not found"));

        SoldProducts sale = new SoldProducts();
        sale.setSeller(seller);
        sale.setProduct(product);
        sale.setSaleDate(new Date());
        soldRepository.save(sale);

        return product;
    }
    public List<ProductModel> getSoldProducts(Long userId) {
        return soldRepository.findBySellerUserId(userId).stream()
                .map(SoldProducts::getProduct)
                .collect(Collectors.toList());
    }


    public List<SoldProducts> getSalesBySeller(Long sellerId) {
        return soldRepository.findBySellerUserId(sellerId);
    }
}
