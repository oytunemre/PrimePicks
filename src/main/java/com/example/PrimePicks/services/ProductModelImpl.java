package com.example.PrimePicks.services;

import com.example.PrimePicks.models.ProductModel;
import com.example.PrimePicks.models.UserModel;
import com.example.PrimePicks.repository.ProductModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class ProductModelImpl implements ProductModelService{

    @Autowired
    private ProductModelRepository productModelRepository;


    @Override
    public ProductModel saveProduct(ProductModel productModel) {
        productModelRepository.save(productModel);
        return productModel;
    }

    @Override
    public List<ProductModel> listAllProducts() {
        return productModelRepository.findAll();
    }



    @Override
    public ProductModel getProductById(Long id) {
        Optional<ProductModel> getId = productModelRepository.findById(id);
        if(getId.isPresent()){
            return  getId.get();
        }
        return null;
    }


    @Override
    public String deleteProductById(Long id) {
        productModelRepository.deleteById(id);
        return "Product deleted successfully";
    }

    @Override
    public List<ProductModel> getSoldProductsByUser(UserModel user) {
        return List.of();
    }

    @Override
    public List<ProductModel> getPurchasedProductsByUser(UserModel user) {
        return List.of();
    }

    @Override
    public List<ProductModel> getLikedProductsByUser(UserModel user) {
        return List.of();
    }

    @Override
    public List<ProductModel> getProductsByCategory(String category) {
        return productModelRepository.findProductsByCategory(category);
    }

    public List<ProductModel> getOtherUsersProductsSorted(Long userId, String sort) {
        switch (sort) {
            case "priceAsc":
                return productModelRepository.findAllByUserIdNotOrderByPriceAsc(userId);
            case "priceDesc":
                return productModelRepository.findAllByUserIdNotOrderByPriceDesc(userId);
            case "nameAsc":
                return productModelRepository.findAllByUserIdNotOrderByProductNameAsc(userId);
            case "nameDesc":
                return productModelRepository.findAllByUserIdNotOrderByProductNameDesc(userId);
            case "dateAsc":
                return productModelRepository.findAllByUserIdNotOrderByDateOfListingProductAsc(userId);
            case "dateDesc":
            default:
                return productModelRepository.findAllByUserIdNotOrderByDateOfListingProductDesc(userId);
        }
    }

    public List<ProductModel> getProductsSorted(String sort) {
        switch (sort) {
            case "priceAsc":
                return productModelRepository.findAllByOrderByPriceAsc();
            case "priceDesc":
                return productModelRepository.findAllByOrderByPriceDesc();
            case "nameAsc":
                return productModelRepository.findAllByOrderByProductNameAsc();
            case "nameDesc":
                return productModelRepository.findAllByOrderByProductNameDesc();
            case "dateAsc":
                return productModelRepository.findAllByOrderByDateOfListingProductAsc();
            case "dateDesc":
            default:
                return productModelRepository.findAllByOrderByDateOfListingProductDesc();
        }
    }

    @Override
    public List<ProductModel> searchProducts(String query) {
        return productModelRepository.findByProductNameContainingIgnoreCase(query);
    }


}
