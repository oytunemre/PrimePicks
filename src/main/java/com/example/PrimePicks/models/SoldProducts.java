package com.example.PrimePicks.models;

import jakarta.persistence.*;
import java.util.Date;

@Entity
public class SoldProducts {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "buyer_user_id")
    private UserModel buyer;

    @ManyToOne
    @JoinColumn(name = "seller_user_id")
    private UserModel seller;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private ProductModel product;

    @Temporal(TemporalType.TIMESTAMP)
    private Date saleDate;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserModel getBuyer() {
        return buyer;
    }

    public void setBuyer(UserModel buyer) {
        this.buyer = buyer;
    }

    public UserModel getSeller() {
        return seller;
    }

    public void setSeller(UserModel seller) {
        this.seller = seller;
    }

    public ProductModel getProduct() {
        return product;
    }

    public void setProduct(ProductModel product) {
        this.product = product;
    }

    public Date getSaleDate() {
        return saleDate;
    }

    public void setSaleDate(Date saleDate) {
        this.saleDate = saleDate;
    }


}
