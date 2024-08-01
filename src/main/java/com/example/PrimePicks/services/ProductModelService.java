package com.example.PrimePicks.services;


import com.example.PrimePicks.models.ProductModel;
import com.example.PrimePicks.models.UserModel;

import java.util.List;
public interface ProductModelService {

//    List<ProductModelDTO> listAllProducts();

    ProductModel saveProduct(ProductModel productModel); // Save Products in the table

    List<ProductModel> listAllProducts(); // List all Products

    ProductModel getProductById(Long id); // get Product ID
    String deleteProductById(Long id); // Delete Products by ID
    public List<ProductModel> getProductsByCategory(String category);
    List<ProductModel> getSoldProductsByUser(UserModel user);
    List<ProductModel> getPurchasedProductsByUser(UserModel user);
    List<ProductModel> getLikedProductsByUser(UserModel user);

    public List<ProductModel> getProductsSorted(String sort);

    public List<ProductModel> getOtherUsersProductsSorted(Long userId, String sort);
    public List<ProductModel> searchProducts(String query);


}
