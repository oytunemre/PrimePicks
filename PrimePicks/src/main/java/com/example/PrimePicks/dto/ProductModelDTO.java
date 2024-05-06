package com.example.PrimePicks.dto;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Data;

import java.util.Date;
@Data
@Builder
public class ProductModelDTO {

    public Long productID;

    public String productName;

    public int stockStatus;

    public String brand;

    public String color;

    protected String size;

    protected String weight;

    public Date dateOfListingProduct;

    public Date dateOfUpdatingProduct;







}
