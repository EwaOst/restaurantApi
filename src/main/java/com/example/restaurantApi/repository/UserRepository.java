package com.example.restaurantApi.repository;

import com.example.restaurantApi.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserModel, Long> {

}
