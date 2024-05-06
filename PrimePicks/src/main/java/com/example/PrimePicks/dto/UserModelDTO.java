package com.example.PrimePicks.dto;

//DTO - Data Transfer Object

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

@Data
@Builder
public class UserModelDTO {


    private Long UserId;

    public String firstName;

    public String lastName;

    private Date birthDate;

    protected String email;

    private String password;

    protected String address;


    private Date userCreated;

    private Date userUpdated;



}
