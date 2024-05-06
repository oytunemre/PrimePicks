package com.example.PrimePicks.models;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;


import java.io.Serializable;
import java.util.Date;


@Entity
@Table(name = "UserModel")
public class UserModel implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long UserId;
    @Column(name = "First Name")
    public String firstName;
    @Column(name = "Last Name")
    public String lastName;
    @Column(name = "BirthDate")
    private Date birthDate;
    @Column(name = "Email Address", unique = true)
    protected String email;
    @Column(name = "Password")
    private String password;
    @Column(name = "Address")
    protected String address;
    @CreationTimestamp
    @Column(name = "User account created")
    private Date userCreated;
    @Column(name = "User account updated")
    @UpdateTimestamp
    private Date userUpdated;


    public UserModel(Long userId, String firstName, String lastName, Date birthDate, String email, String password, String address, Date userCreated, Date userUpdated) {
        UserId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.email = email;
        this.password = password;

        this.address = address;

        this.userCreated = userCreated;
        this.userUpdated = userUpdated;
    }

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

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
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
        return UserId;
    }

    public void setUserId(Long userId) {
        UserId = userId;
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

}