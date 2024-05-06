package com.example.PrimePicks.dto;

import com.example.PrimePicks.models.ProductModel;
import com.example.PrimePicks.models.UserModel;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.util.Date;


@Data
@Builder
public class OrderModelDTO {

    private Long orderID;

    private ProductModel productID;

    private UserModel userID;

    private UserModel sellerAddress;

    private UserModel buyerAddress;

    private Date dateOfOrder;



}
