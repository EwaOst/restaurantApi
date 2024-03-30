package com.example.restaurantApi.repository;

import com.example.restaurantApi.model.ReviewModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<ReviewModel, Long> {

    // Metoda do pobierania wszystkich recenzji dla danej restauracji
    List<ReviewModel> findByRestaurantId(Long restaurantId);

}
