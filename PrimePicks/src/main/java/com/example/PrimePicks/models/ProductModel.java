package com.example.PrimePicks.models;



import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "ProductModel")
public class ProductModel {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ProductID")
    public Long productID;

    @Column(name = "Product Name")
    public String productName;
    @Column(name = "Stock Status")
    public int stockStatus;
    @Column(name = "Brand")
    public String brand;
    @Column(name = "Color")
    public String color;
    @Column(name = "Size")
    protected String size;
    @Column(name = "Weight")
    protected String weight;
    @Column(name = "Date of published")
    public Date dateOfListingProduct;
    @Column(name = "Date of Update of product")
    public Date dateOfUpdatingProduct;


    public ProductModel() {
    }

    public ProductModel(String productName, Long productID, int stockStatus, String brand, String color, String size, String weight, Date dateOfListingProduct, Date dateOfUpdatingProduct) {
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

    public Long getProductID() {
        return productID;
    }

    public void setProductID(Long productID) {
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
