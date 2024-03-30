package com.example.restaurantApi.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class ReviewModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;

    private Integer rating;

    private LocalDateTime date;

    @ManyToOne
    @JsonBackReference
    private UserModel user;

    @ManyToOne
    @JoinColumn(name = "restaurant_id")
    private RestaurantModel restaurant;
}
