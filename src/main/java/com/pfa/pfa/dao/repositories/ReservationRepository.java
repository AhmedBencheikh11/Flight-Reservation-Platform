package com.pfa.pfa.dao.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import com.pfa.pfa.dao.entities.User;

import com.pfa.pfa.dao.entities.Reservation;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    // Add custom methods like finding reservations by user or by flight

    
   List<Reservation> findByUser(User user);

}
