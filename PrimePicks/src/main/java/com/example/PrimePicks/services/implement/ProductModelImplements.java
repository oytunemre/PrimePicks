package com.example.PrimePicks.services.implement;

import com.example.PrimePicks.dto.ProductModelDTO;
import com.example.PrimePicks.dto.UserModelDTO;
import com.example.PrimePicks.models.ProductModel;
import com.example.PrimePicks.models.UserModel;
import com.example.PrimePicks.repository.ProductModelRepository;
import com.example.PrimePicks.services.ProductModelService;

import java.util.List;
import java.util.stream.Collectors;

public class ProductModelImplements implements ProductModelService {


    private ProductModelRepository productModelRepository;

    public ProductModelImplements(ProductModelRepository productModelRepository) {
        this.productModelRepository = productModelRepository;
    }

    @Override
    public List<ProductModelDTO> listAllProducts() {
        List<ProductModel> productModels = productModelRepository.findAll();
        return  productModels.stream().map(productModel -> mapToProductModelDTO(productModel)).collect(Collectors.toList());
    }

    private ProductModelDTO mapToProductModelDTO(ProductModel productModel){

        ProductModelDTO productModelDTO  = ProductModelDTO.builder()
                .productID(productModel.getProductID())
                .productName(productModel.getProductName())
                .stockStatus(productModel.getStockStatus())
                .brand(productModel.getBrand())
                .color(productModel.getColor())
                .size(productModel.getSize())
                .weight(productModel.getWeight())
                .dateOfListingProduct(productModel.getDateOfListingProduct())
                .dateOfUpdatingProduct(productModel.getDateOfUpdatingProduct())
                .build();

return productModelDTO;

    }





}
