package com.example.restaurantApi.dto;

import com.example.restaurantApi.model.ReservationStatus;
import lombok.Data;

import java.time.Duration;
import java.time.LocalDateTime;


@Data
public class ReservationDto {
    private Long userId;
    private LocalDateTime reservationDate;
    private Duration duration;
    private ReservationStatus status;
    private Long tableId;
}