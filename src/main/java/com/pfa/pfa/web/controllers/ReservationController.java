package com.pfa.pfa.web.controllers;

import com.pfa.pfa.dao.entities.Reservation;
import com.pfa.pfa.dao.entities.User;
import com.pfa.pfa.dao.entities.Vol;
import com.pfa.pfa.dao.repositories.ReservationRepository;
import com.pfa.pfa.dao.repositories.VolRepository;
import com.pfa.pfa.business.services.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
public class ReservationController {

    @Autowired
    private VolRepository volRepository;

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private UserService userService;

    @GetMapping("/reservationsuccess")
     public String showSuccessPage() {
      return "reservationsuccess"; // Looks for templates/reservationsuccess.html
}


    @PostMapping("/vol/reservation")
    public String submitReservation(
            @RequestParam("volId") Long volId,
            @RequestParam("passportNumber") String passportNumber,
            @RequestParam("visaCard") String visaCard,   // provisional, in-memory only
            HttpSession session,
            RedirectAttributes ra
    ) {
        // 1) Look up the Vol
        Optional<Vol> optVol = volRepository.findById(volId);
        if (optVol.isEmpty()) {
            ra.addFlashAttribute("errorMessage", "Flight not found.");
            return "redirect:/vol";
        }
        Vol vol = optVol.get();

        // 2) Look up the logged-in User
        String email = (String) session.getAttribute("email");
        if (email == null) {
            ra.addFlashAttribute("errorMessage", "Please log in to make a reservation.");
            return "redirect:/inscrire";
        }
        Optional<User> optUser = userService.getUserByEmail(email);
        if (optUser.isEmpty()) {
            ra.addFlashAttribute("errorMessage", "User not found.");
            return "redirect:/inscrire";
        }
        User user = optUser.get();

        // 3) Build and save the Reservation, including destination
        Reservation res = Reservation.builder()
                .vol(vol)
                .user(user)
                .passportNumber(passportNumber)
                .destination(vol.getDestination())
                .build();
        reservationRepository.save(res);

        // 4) Decrement available seats on the Vol and save
        int currentSeats = vol.getNbPlaces();
        vol.setNbPlaces(currentSeats - 1);
        volRepository.save(vol);

        // 5) Log provisional Visa info (in-memory only)
        System.out.println("VisaCard (provisoire): " + visaCard);

        

        // 6) Redirect with success message
        ra.addFlashAttribute("successMessage",
                "Reservation saved! Flight to " + vol.getDestination() +
                ", passport " + passportNumber);
        return "redirect:/reservationsuccess";
    }
}
