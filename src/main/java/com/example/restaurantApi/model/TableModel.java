package com.example.restaurantApi.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
@Entity
@Data
public class TableModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String tableNumber;

    private Integer seatingCapacity;

    @Enumerated(EnumType.STRING)
    private TableStatus status;

    private String layoutPlan;

    @ManyToOne
    @JoinColumn(name = "restaurant_id")
    private RestaurantModel restaurant;

    @OneToMany(mappedBy = "table")
    private List<ReservationModel> reservations;
}
