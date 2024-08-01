package com.example.PrimePicks.repository;

import com.example.PrimePicks.models.ShoppingCartModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShoppingCartModelRepository extends JpaRepository<ShoppingCartModel, Long> {
    ShoppingCartModel findByUser_UserId(Long userId);

}
