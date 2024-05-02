package com.example.PrimePicks.Model;

import java.util.Date;

public class ProductModel extends SellerModel {




    public String productName;
    public  String productID;
    public int stockStatus;
    public String brand;
    public String color;
    protected String size;
    protected String weight;
    public Date dateOfListingProduct;
    public Date dateOfUpdatingProduct;


    public ProductModel() {
    }

    public ProductModel(String productName, String productID, int stockStatus, String brand, String color, String size, String weight, Date dateOfListingProduct, Date dateOfUpdatingProduct) {
        this.productName = productName;
        this.productID = productID;
        this.stockStatus = stockStatus;
        this.brand = brand;
        this.color = color;
        this.size = size;
        this.weight = weight;
        this.dateOfListingProduct = dateOfListingProduct;
        this.dateOfUpdatingProduct = dateOfUpdatingProduct;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public int getStockStatus() {
        return stockStatus;
    }

    public void setStockStatus(int stockStatus) {
        this.stockStatus = stockStatus;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public Date getDateOfListingProduct() {
        return dateOfListingProduct;
    }

    public void setDateOfListingProduct(Date dateOfListingProduct) {
        this.dateOfListingProduct = dateOfListingProduct;
    }

    public Date getDateOfUpdatingProduct() {
        return dateOfUpdatingProduct;
    }

    public void setDateOfUpdatingProduct(Date dateOfUpdatingProduct) {
        this.dateOfUpdatingProduct = dateOfUpdatingProduct;
    }
}
