package com.example.PrimePicks.repository;

import com.example.PrimePicks.PrimePicksApplication;
import com.example.PrimePicks.models.OrderModel;
import com.example.PrimePicks.models.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrderModelRepository extends JpaRepository<OrderModel,Long> {


    Optional<OrderModel> findOrder(String order);


}
