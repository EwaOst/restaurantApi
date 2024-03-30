package com.example.restaurantApi.repository;

import com.example.restaurantApi.model.RestaurantModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestaurantRepository extends JpaRepository<RestaurantModel, Long> {
}
