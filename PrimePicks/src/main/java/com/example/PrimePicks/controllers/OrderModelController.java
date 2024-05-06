
package com.example.PrimePicks.controllers;

import com.example.PrimePicks.dto.OrderModelDTO;
import com.example.PrimePicks.services.OrderModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;

import java.util.List;

public class OrderModelController {


    private OrderModelService orderModelService;


    @Autowired
    public OrderModelController(OrderModelService orderModelService) {
        this.orderModelService = orderModelService;
    }


    public String listOrder(Model model) {

        List<OrderModelDTO> orderModelDTOList = orderModelService.listAllOrders();
        model.addAttribute("Orders", orderModelDTOList);
        return "order-list";


    }


}


