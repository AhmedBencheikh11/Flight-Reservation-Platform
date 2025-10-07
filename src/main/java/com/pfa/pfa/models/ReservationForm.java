package com.pfa.pfa.models;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ReservationForm {

    @NotNull(message = "L'identifiant du vol est requis")
    private Long volId;

    @NotNull(message = "L'identifiant de l'utilisateur est requis")
    private Long userId;

    @NotBlank(message = "Le numéro de passeport est requis")
    private String passportNumber;

    // NEW: provisional visa card textarea
    @NotBlank(message = "Le numéro de carte Visa est requis")
    private String visaCard;
}
