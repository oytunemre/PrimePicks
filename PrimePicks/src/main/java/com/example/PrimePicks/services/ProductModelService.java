package com.example.PrimePicks.services;

import com.example.PrimePicks.dto.ProductModelDTO;
import com.example.PrimePicks.dto.UserModelDTO;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import java.util.List;
@ComponentScan
@Service
public interface ProductModelService {

    List<ProductModelDTO> listAllProducts();

}
