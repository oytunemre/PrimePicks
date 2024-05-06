package com.example.PrimePicks.repository;

import com.example.PrimePicks.models.OrderModel;
import com.example.PrimePicks.models.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserModelRepository extends JpaRepository<UserModel,Long> {


    Optional<UserModel> findUser(String users);

}
