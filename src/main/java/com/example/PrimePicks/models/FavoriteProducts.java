package com.example.PrimePicks.models;

import jakarta.persistence.*;
import org.apache.catalina.User;

import java.io.Serializable;

@Entity
public class FavoriteProducts implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "favorite_user_id")
    private UserModel user;

    @ManyToOne
    @JoinColumn(name = "favorite_product_id")
    private ProductModel product;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserModel getUser() {
        return user;
    }

    public void setUser(UserModel user) {
        this.user = user;
    }

    public ProductModel getProduct() {
        return product;
    }

    public void setProduct(ProductModel product) {
        this.product = product;
    }
}
