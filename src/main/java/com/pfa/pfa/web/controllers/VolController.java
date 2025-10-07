package com.pfa.pfa.web.controllers;

import com.pfa.pfa.dao.entities.Vol;
import com.pfa.pfa.dao.repositories.VolRepository;
import com.pfa.pfa.business.services.VolService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/vol")
public class VolController {

    @Autowired
    private VolService volService;

    @Autowired
    private VolRepository volRepository;

    /**
     * Only this email is considered admin.
     */
    private boolean isAdmin(String email) {
        return "king007jed@gmail.com".equalsIgnoreCase(email);
    }

    /**
     * List all vols.
     */
    @GetMapping
    public String listVols(Model model, HttpSession session) {
        String email = (String) session.getAttribute("email");
        List<Vol> vols = volService.getAllVols();
        model.addAttribute("vols", vols);
        model.addAttribute("isAdmin", isAdmin(email));
        return "vol";
    }

    /**
     * Display the reservation page for a specific vol.
     */
    @GetMapping("/reservation")
    public String showReservationPage(
            @RequestParam("volId") Long volId,
            Model model
    ) {
        Vol vol = volRepository.findById(volId).orElse(null);
        if (vol == null) {
            return "redirect:/vol";
        }
        model.addAttribute("vol", vol);
        return "reservation";
    }

    /**
     * Show form to create a new vol.
     */
    @GetMapping("/add")
    public String showAddVolForm(Model model) {
        model.addAttribute("vol", new Vol());
        return "vol-add";
    }

    /**
     * Handle the submission of the new-vol form.
     */
    @PostMapping("/add")
    public String addVol(
            @Valid @ModelAttribute("vol") Vol vol,
            BindingResult bindingResult,
            RedirectAttributes ra
    ) {
        if (bindingResult.hasErrors()) {
            return "vol-add";
        }
        volService.saveVol(vol);
        ra.addFlashAttribute("successMessage", "Vol ajouté avec succès !");
        return "redirect:/vol";
    }

    /**
     * Show form to edit an existing vol.
     */
    @GetMapping("/edit/{id}")
    public String showEditVolForm(
            @PathVariable Long id,
            Model model
    ) {
        Vol vol = volService.getVolById(id);
        if (vol == null) {
            return "redirect:/vol";
        }
        model.addAttribute("vol", vol);
        return "vol-edit";
    }

    /**
     * Handle the submission of the edit-vol form.
     */
    @PostMapping("/edit/{id}")
    public String editVol(
            @PathVariable Long id,
            @ModelAttribute("vol") Vol vol
    ) {
        vol.setId_vol(id);
        volService.saveVol(vol);
        return "redirect:/vol";
    }

    /**
     * Delete a vol.
     */
    @PostMapping("/delete/{id}")
    public String deleteVol(
            @PathVariable Long id,
            RedirectAttributes ra
    ) {
        volService.deleteVol(id);
        ra.addFlashAttribute("successMessage", "Vol supprimé avec succès !");
        return "redirect:/vol";
    }
}
