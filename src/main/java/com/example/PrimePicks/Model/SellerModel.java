package com.example.PrimePicks.Model;

import java.util.Date;

public class SellerModel extends UserModel {

    int sellerID;
    String sellerAdress;

    public SellerModel(String firstName, String lastName, Date birthDate, String email, String password, String userType, int sellerID, String sellerAdress) {
        super(firstName, lastName, birthDate, email, password, userType);
        this.sellerID = sellerID;
        this.sellerAdress = sellerAdress;
    }

    public SellerModel(int sellerID, String sellerAdress) {
        this.sellerID = sellerID;
        this.sellerAdress = sellerAdress;
    }

    public SellerModel() {
    }


}
