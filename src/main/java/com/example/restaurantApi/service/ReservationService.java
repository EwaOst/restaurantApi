package com.example.restaurantApi.service;

import com.example.restaurantApi.dto.ReservationDto;
import com.example.restaurantApi.model.ReservationModel;
import com.example.restaurantApi.model.TableModel;
import com.example.restaurantApi.model.UserModel;
import com.example.restaurantApi.repository.ReservationRepository;
import com.example.restaurantApi.repository.TableRepository;
import com.example.restaurantApi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final UserRepository userRepository;
    private final TableRepository tableRepository;

    /**
     * Creates a new reservation using details provided in a ReservationDto object.
     * It looks up the User and Table by their IDs, and if not found, throws a RuntimeException.
     *
     * @param reservationDTO Data Transfer Object containing reservation details.
     * @return The saved ReservationModel entity.
     */
    public ReservationModel makeReservation(ReservationDto reservationDTO) {
        UserModel user = userRepository.findById(reservationDTO.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));
        TableModel table = tableRepository.findById(reservationDTO.getTableId())
                .orElseThrow(() -> new RuntimeException("Table not found"));

        ReservationModel reservation = new ReservationModel();
        reservation.setUser(user);
        reservation.setTable(table);
        reservation.setReservationDate(reservationDTO.getReservationDate());
        reservation.setDuration(reservationDTO.getDuration());
        reservation.setStatus(reservationDTO.getStatus());

        return reservationRepository.save(reservation);
    }

    /**
     * Retrieves all reservations made by a specific user.
     *
     * @param userId The ID of the user whose reservations are to be retrieved.
     * @return A list of ReservationModel entities.
     */
    public List<ReservationModel> getReservationsByUserId(Long userId) {
        UserModel user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return reservationRepository.findByUser(user);
    }

    /**
     * Updates an existing reservation with new details provided in a ReservationModel object.
     * It does not allow changing the user or the table of the reservation.
     *
     * @param id                 The ID of the reservation to be updated.
     * @param reservationDetails New details for the reservation.
     * @return The updated ReservationModel entity.
     */
    public ReservationModel updateReservation(Long id, ReservationModel reservationDetails) {
        ReservationModel reservation = reservationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reservation not found"));

        reservation.setReservationDate(reservationDetails.getReservationDate());
        reservation.setDuration(reservationDetails.getDuration());
        reservation.setStatus(reservationDetails.getStatus());
        return reservationRepository.save(reservation);
    }

    /**
     * Deletes a reservation from the database.
     *
     * @param id The ID of the reservation to be deleted.
     */
    public void cancelReservation(Long id) {
        ReservationModel reservation = reservationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reservation not found"));
        reservationRepository.delete(reservation);
    }
}