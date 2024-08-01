package com.example.PrimePicks.models;


import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.io.Serializable;
import java.util.Base64;
import java.util.Date;
import java.util.List;

import static java.util.Base64.getEncoder;

@Entity
@Table(name = "ProductModel")
public class ProductModel implements Serializable {


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
    @CreationTimestamp
    @Column(name = "Date of published")
    public Date dateOfListingProduct;
    @UpdateTimestamp
    @Column(name = "Date of Update of product")
    public Date dateOfUpdatingProduct;
    @Column(name = "Price")
    public Double price;
    @Column(name = "Category")
    private String category;
    @Lob
    @Column(name = "Image", columnDefinition = "LONGBLOB", length = 1000)
    private byte[] image;

    @Column(name = "userId")
    private Long userId;

    @ManyToMany(mappedBy = "products")
    private List<ShoppingCartModel> carts;



    public ProductModel() {
    }

    public ProductModel(String productName, Long productID, int stockStatus, String brand, String color, String size, String weight, Date dateOfListingProduct, Date dateOfUpdatingProduct, byte[] image, String category, Double price, Long userId,UserModel user) {
        this.productName = productName;
        this.productID = productID;
        this.stockStatus = stockStatus;
        this.brand = brand;
        this.color = color;
        this.size = size;
        this.weight = weight;
        this.dateOfListingProduct = dateOfListingProduct;
        this.dateOfUpdatingProduct = dateOfUpdatingProduct;
        this.image = image;
        this.category = category;
        this.price = price;
        this.userId = userId;

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

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }


    public List<ShoppingCartModel> getCarts() {
        return carts;
    }

    public void setCarts(List<ShoppingCartModel> carts) {
        this.carts = carts;
    }

    public String getImageBase64() {
        if (this.image != null) {
            return Base64.getEncoder().encodeToString(this.image);
        }
        return "";
    }


}
