package com.example.PrimePicks.models;

import jakarta.annotation.Nonnull;
import jakarta.persistence.*;
import org.antlr.v4.runtime.misc.NotNull;
import org.apache.catalina.User;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "OrderModel")
public class OrderModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "OrderID", unique = true)
    private Long orderID;

    @Id
    @OneToOne
    @JoinColumn(name = "Product ID")
    private ProductModel productID;

    @OneToOne
    @JoinColumn(name = "User ID")
    private UserModel userID;

    @Column(name = "Seller Address ")
    private UserModel sellerAddress;

    @Column(name = "Buyer Address ")
    private UserModel buyerAddress;

    @Column(name = "Date of the order ")
    private Date dateOfOrder;


    public Long getOrderID() {
        return orderID;
    }

    public void setOrderID(Long orderID) {
        this.orderID = orderID;
    }

    public ProductModel getProductID() {
        return productID;
    }

    public void setProductID(ProductModel productID) {
        this.productID = productID;
    }

    public UserModel getUserID() {
        return userID;
    }

    public void setUserID(UserModel userID) {
        this.userID = userID;
    }

    public UserModel getSellerAddress() {
        return sellerAddress;
    }

    public void setSellerAddress(UserModel sellerAddress) {
        this.sellerAddress = sellerAddress;
    }

    public UserModel getBuyerAddress() {
        return buyerAddress;
    }

    public void setBuyerAddress(UserModel buyerAddress) {
        this.buyerAddress = buyerAddress;
    }

    public Date getDateOfOrder() {
        return dateOfOrder;
    }

    public void setDateOfOrder(Date dateOfOrder) {
        this.dateOfOrder = dateOfOrder;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderModel that = (OrderModel) o;
        return Objects.equals(getOrderID(), that.getOrderID()) && Objects.equals(getProductID(), that.getProductID()) && Objects.equals(getUserID(), that.getUserID()) && Objects.equals(getSellerAddress(), that.getSellerAddress()) && Objects.equals(getBuyerAddress(), that.getBuyerAddress()) && Objects.equals(getDateOfOrder(), that.getDateOfOrder());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getOrderID(), getProductID(), getUserID(), getSellerAddress(), getBuyerAddress(), getDateOfOrder());
    }
}