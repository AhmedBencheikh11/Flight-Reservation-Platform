package com.pfa.pfa.web.controllers;

import com.pfa.pfa.dao.entities.User;
import com.pfa.pfa.dao.entities.Reservation;
import com.pfa.pfa.models.UserForm;
import com.pfa.pfa.business.services.UserService;
import com.pfa.pfa.business.services.ReservationService;

import jakarta.validation.Valid;
import jakarta.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
public class MainController {

    private static final Logger logger = LoggerFactory.getLogger(MainController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private ReservationService reservationService;

    private final String[] ADMIN_EMAILS = {
        "king007jed@gmail.com"
    };

    private boolean isAdmin(String email) {
        if (email == null) return false;
        for (String adminEmail : ADMIN_EMAILS) {
            if (adminEmail.equalsIgnoreCase(email)) return true;
        }
        return false;
    }

    @GetMapping("/")
    public String inscrire(Model model) {
        if (!model.containsAttribute("userForm")) {
            model.addAttribute("userForm", new UserForm());
        }
        return "inscrire";
    }

    @PostMapping("/register")
    public String register(
        @ModelAttribute("userForm") @Valid UserForm userForm,
        BindingResult result,
        Model model,
        RedirectAttributes redirectAttributes
    ) {
        if (result.hasErrors()) {
            return "inscrire";
        }

        if (userService.emailExists(userForm.getMail())) {
            model.addAttribute("emailError", "Email déjà existant !");
            return "inscrire";
        }

        User user = User.builder()
                        .nom(userForm.getNom())
                        .prenom(userForm.getPrenom())
                        .mail(userForm.getMail())
                        .password(userForm.getPassword())
                        .build();
        userService.saveUser(user);

        logger.info("Nouvel utilisateur inscrit : {}", user.getMail());
        redirectAttributes.addFlashAttribute("successMessage", "Votre compte a été créé avec succès !");
        return "redirect:/acceuil";
    }

    @PostMapping("/login")
    public String login(
        @RequestParam("mail") String mail,
        @RequestParam("password") String password,
        Model model,
        RedirectAttributes redirectAttributes,
        HttpSession session
    ) {
        if (userService.isValidUser(mail, password)) {
            session.setAttribute("email", mail);
            redirectAttributes.addFlashAttribute("welcomeMessage", "Bienvenue !");
            return "redirect:/acceuil";
        } else {
            model.addAttribute("loginError", "Email ou mot de passe incorrect.");
            model.addAttribute("userForm", new UserForm());
            return "inscrire";
        }
    }

    @GetMapping("/acceuil")
    public String acceuil(HttpSession session, Model model) {
        String email = (String) session.getAttribute("email");
        boolean isAdmin = isAdmin(email);
        model.addAttribute("isAdmin", isAdmin);

        return "acceuil";
    }

    @GetMapping("/admin")
    public String adminPage(HttpSession session, Model model) {
        String email = (String) session.getAttribute("email");
        if (!isAdmin(email)) {
            return "redirect:/acceuil";
        }

        Optional<User> optUser = userService.getUserByEmail(email);
        if (optUser.isEmpty()) {
            return "redirect:/inscrire";
        }

        User user = optUser.get();
        model.addAttribute("user", user);
        return "admin";
    }

    @GetMapping("/contact")
    public String contact() {
        return "contact";
    }

    @GetMapping("/userspace")
    public String userspace(Model model, HttpSession session) {
        String email = (String) session.getAttribute("email");
        User user = userService.getUserByEmail(email).orElse(null);
        model.addAttribute("user", user);

        List<Reservation> reservations = reservationService.getReservationsByUser(user);
        model.addAttribute("reservations", reservations);

        return "admin"; // userspace uses admin.html
    }

    @GetMapping("/user/edit")
    public String editProfile(Model model, HttpSession session) {
        String email = (String) session.getAttribute("email");
        Optional<User> userOptional = userService.getUserByEmail(email);

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            UserForm userForm = new UserForm();
            userForm.setNom(user.getNom());
            userForm.setPrenom(user.getPrenom());
            userForm.setPassword(user.getPassword());

            model.addAttribute("userForm", userForm);
        }

        return "edit-user";
    }

    @PostMapping("/user/edit")
    public String updateProfile(
        @ModelAttribute("userForm") @Valid UserForm userForm,
        BindingResult result,
        HttpSession session,
        RedirectAttributes redirectAttributes
    ) {
        if (result.hasErrors()) {
            return "edit-user";
        }

        String email = (String) session.getAttribute("email");
        Optional<User> userOptional = userService.getUserByEmail(email);

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            user.setNom(userForm.getNom());
            user.setPrenom(userForm.getPrenom());
            user.setPassword(userForm.getPassword());

            userService.saveUser(user);

            logger.info("Utilisateur mis à jour : {}", user.getMail());
            redirectAttributes.addFlashAttribute("successMessage", "Votre profil a été mis à jour avec succès !");
            return "redirect:/userspace";
        }

        redirectAttributes.addFlashAttribute("errorMessage", "Erreur de mise à jour du profil.");
        return "redirect:/userspace";
    }

     @GetMapping("/users")
      public String showUsers(Model model) {
      List<User> users = userService.getAllUsers(); // this must be implemented in your UserService
      model.addAttribute("users", users);
    return "user-list";
}

@GetMapping("/reservations")
public String showReservations(Model model) {
    List<Reservation> reservations = reservationService.getAllReservations(); // Fetch all reservations from the service
    model.addAttribute("reservations", reservations);
    return "reservation-list"; // Name of the HTML template for listing reservations
}

}
