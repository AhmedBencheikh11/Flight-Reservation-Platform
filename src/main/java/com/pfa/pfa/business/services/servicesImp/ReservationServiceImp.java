package com.pfa.pfa.business.services.servicesImp;

import org.springframework.stereotype.Service;

import com.pfa.pfa.business.services.ReservationService;
import com.pfa.pfa.dao.entities.Reservation;
import com.pfa.pfa.dao.entities.User;
import com.pfa.pfa.dao.repositories.ReservationRepository;

import java.util.List;

@Service
public class ReservationServiceImp implements ReservationService {

    private final ReservationRepository reservationRepository;

    public ReservationServiceImp(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    @Override
     public List<Reservation> getReservationsByUser(User user) {
      return reservationRepository.findByUser(user);
}


    @Override
    public void ajouterReservation(Reservation reservation) {
        reservationRepository.save(reservation);  // Add the reservation to the repository
    }

    @Override
    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();  // Get all reservations
    }

    @Override
    public Reservation getReservationById(Long id) {
        return reservationRepository.findById(id).orElse(null);  // Find a reservation by its ID
    }

    @Override
    public void supprimerReservation(Long id) {
        reservationRepository.deleteById(id);  // Delete a reservation by its ID
    }

    @Override
    public void saveReservation(Reservation reservation) {
        ajouterReservation(reservation);  // Call the existing method to save the reservation
    }
}
