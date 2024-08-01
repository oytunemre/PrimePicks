package com.example.PrimePicks.models;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "UserModel")
public class UserModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "user_name", unique = true)
    private String username;

    @Column(name = "email_address", unique = true)
    private String email;

    @Column(name = "password")
    private String password;


    @Column(name = "address")
    private String address;

    @CreationTimestamp
    @Column(name = "user_account_created")
    private Date userCreated;

    @UpdateTimestamp
    @Column(name = "user_account_updated")
    private Date userUpdated;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private ShoppingCartModel shoppingCart;

    public UserModel() {
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Long getUserId() {
        return userId ;
    }

    public void setUserId(Long userId2) {
        this.userId  = userId2;
    }

    public Date getUserUpdated() {
        return userUpdated;
    }

    public void setUserUpdated(Date userUpdated) {
        this.userUpdated = userUpdated;
    }

    public Date getUserCreated() {
        return userCreated;
    }

    public void setUserCreated(Date userCreated) {
        this.userCreated = userCreated;
    }

    public String getUsername() {
        return username;
    }


    public void setUsername(String username) {
        this.username = username;
    }

    public ShoppingCartModel getShoppingCart() {
        return shoppingCart;
    }

    public void setShoppingCart(ShoppingCartModel shoppingCart) {
        this.shoppingCart = shoppingCart;
    }


}