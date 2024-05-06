package com.example.PrimePicks.services.implement;

import com.example.PrimePicks.dto.OrderModelDTO;
import com.example.PrimePicks.dto.UserModelDTO;
import com.example.PrimePicks.models.OrderModel;
import com.example.PrimePicks.models.UserModel;
import com.example.PrimePicks.repository.OrderModelRepository;
import com.example.PrimePicks.services.OrderModelService;

import java.util.List;
import java.util.stream.Collectors;

public class OrderModelImplements  implements OrderModelService {


   private OrderModelRepository orderModelRepository;

    public OrderModelImplements(OrderModelRepository orderModelRepository) {
        this.orderModelRepository = orderModelRepository;
    }

    @Override
    public List<OrderModelDTO> listAllOrders() {
        List<OrderModel> orderModels = orderModelRepository.findAll();
        return  orderModels.stream().map(orderModel -> mapToOrderModelDTO(orderModel)).collect(Collectors.toList());
    }

    private OrderModelDTO mapToOrderModelDTO(OrderModel orderModel) {

        OrderModelDTO orderModelDTO = OrderModelDTO.builder()
                .orderID(orderModel.getOrderID())
                .productID(orderModel.getProductID())
                .userID(orderModel.getUserID())
                .sellerAddress(orderModel.getSellerAddress())
                .buyerAddress(orderModel.getBuyerAddress())
                .dateOfOrder(orderModel.getDateOfOrder())
                .build();
        return  orderModelDTO;
    }



}
