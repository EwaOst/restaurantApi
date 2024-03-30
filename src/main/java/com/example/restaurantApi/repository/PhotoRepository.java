package com.example.restaurantApi.repository;

import com.example.restaurantApi.model.PhotoModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhotoRepository extends JpaRepository<PhotoModel, Long> {
}
