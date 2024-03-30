package com.example.restaurantApi.controller;

import com.example.restaurantApi.dto.ReservationDto;
import com.example.restaurantApi.model.ReservationModel;
import com.example.restaurantApi.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/reservations")
@RequiredArgsConstructor
public class ReservationController {

    private final ReservationService reservationService;

    /**
     * Creates a new reservation based on the provided ReservationDto object.
     *
     * @param reservationDTO The reservation data transfer object containing the reservation details.
     * @return ResponseEntity containing the saved ReservationModel object.
     */
    @PostMapping
    public ResponseEntity<ReservationModel> makeReservation(@RequestBody ReservationDto reservationDTO) {
        ReservationModel savedReservation = reservationService.makeReservation(reservationDTO);
        return ResponseEntity.ok(savedReservation);
    }

    /**
     * Retrieves all reservations made by a specific user.
     *
     * @param userId The ID of the user whose reservations are to be retrieved.
     * @return ResponseEntity containing a list of ReservationModel objects.
     */
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<ReservationModel>> getReservationsByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok(reservationService.getReservationsByUserId(userId));
    }

    /**
     * Updates an existing reservation identified by the provided ID with the details in the provided ReservationModel object.
     *
     * @param id          The ID of the reservation to be updated.
     * @param reservation The new details for the reservation.
     * @return ResponseEntity containing the updated ReservationModel object.
     */
    @PutMapping("/{id}")
    public ResponseEntity<ReservationModel> updateReservation(@PathVariable Long id, @RequestBody ReservationModel reservation) {
        return ResponseEntity.ok(reservationService.updateReservation(id, reservation));
    }

    /**
     * Cancels an existing reservation identified by the provided ID.
     *
     * @param id The ID of the reservation to be canceled.
     * @return ResponseEntity with OK status if the cancellation was successful.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> cancelReservation(@PathVariable Long id) {
        reservationService.cancelReservation(id);
        return ResponseEntity.ok().build();
    }
}
