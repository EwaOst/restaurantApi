package com.example.restaurantApi.repository;

import com.example.restaurantApi.model.ReservationModel;
import com.example.restaurantApi.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReservationRepository extends JpaRepository<ReservationModel, Long> {

    List<ReservationModel> findByUser(UserModel user);
}
