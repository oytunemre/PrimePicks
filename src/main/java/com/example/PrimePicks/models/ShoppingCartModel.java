package com.example.PrimePicks.models;


import jakarta.persistence.*;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;

@Entity
@Table(name = "Shopping Cart Model")
public class ShoppingCartModel implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id", unique = true)
    private UserModel user;


    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "cart_product_ids", joinColumns = @JoinColumn(name = "cart_id"))
    @Column(name = "product_id")
    private List<Long> productIds;



    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "cart_products",
            joinColumns = @JoinColumn(name = "cart_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private List<ProductModel> products;


    @ElementCollection(fetch = FetchType.EAGER)
    @MapKeyColumn(name = "product_id")
    @Column(name = "quantity")
    private Map<Long, Integer> productQuantities = new HashMap<>();

    @Transient
    private double totalPrice;

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public void calculateTotalPrice() {
        totalPrice = 0.0;
        for (ProductModel product : products) {
            int quantity = productQuantities.getOrDefault(product.getProductID(), 0);
            totalPrice += product.getPrice() * quantity;
        }
    }



    public Map<Long, Integer> getProductQuantities() {
        return productQuantities;
    }

    public void setProductQuantities(Map<Long, Integer> productQuantities) {
        this.productQuantities = productQuantities;
    }

    public ShoppingCartModel(Long id, UserModel userModel, List<Long> productIds, List<ProductModel> products) {
        this.id = id;
        this.user = userModel;
        this.productIds = productIds;
        this.products = products;
    }

    public List<ProductModel> getProducts() {
        return products;
    }

    public void setProducts(List<ProductModel> products) {
        this.products = products;
    }

    public ShoppingCartModel() {
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserModel getUser() {
        return user;
    }

    public void setUser(UserModel userModel) {
        this.user = userModel;
    }

    public List<Long> getProductIds() {
        return productIds;
    }

    public void setProductIds(List<Long> productIds) {
        this.productIds = productIds;
    }
}
