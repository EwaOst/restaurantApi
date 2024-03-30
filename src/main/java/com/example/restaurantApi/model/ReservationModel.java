package com.example.restaurantApi.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.Duration;
import java.time.LocalDateTime;

@Entity
@Data
public class ReservationModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime reservationDate;

    private Duration duration;

    @Enumerated(EnumType.STRING)
    private ReservationStatus status;

    @ManyToOne
    private UserModel user;

    @ManyToOne
    private TableModel table;
}