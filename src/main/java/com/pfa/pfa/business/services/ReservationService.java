package com.pfa.pfa.business.services;

import java.util.List;

import com.pfa.pfa.dao.entities.Reservation;
import com.pfa.pfa.dao.entities.User;

public interface ReservationService {
    void ajouterReservation(Reservation reservation);  // Add a reservation
    List<Reservation> getAllReservations();  // Get all reservations
    List<Reservation> getReservationsByUser(User user);

    Reservation getReservationById(Long id);  // Get a reservation by ID
    void supprimerReservation(Long id);  // Delete a reservation by ID

    void saveReservation(Reservation reservation);  // Save or update a reservation (for consistency with controller)


}
