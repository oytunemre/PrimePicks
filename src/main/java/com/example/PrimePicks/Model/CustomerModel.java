package com.example.PrimePicks.Model;

import org.springframework.boot.autoconfigure.domain.EntityScan;

import java.util.Date;



public class CustomerModel extends UserModel {


    String customerID;
    String customerAddress;

    public CustomerModel(String firstName, String lastName, Date birthDate, String email, String password, String userType, String customerID, String customerAddress) {
        super(firstName, lastName, birthDate, email, password, userType);
        this.customerID = customerID;
        this.customerAddress = customerAddress;
    }

    public CustomerModel(String customerID, String customerAddress) {
        this.customerID = customerID;
        this.customerAddress = customerAddress;
    }

    public CustomerModel() {
    }


}
