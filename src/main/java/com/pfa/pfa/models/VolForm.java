package com.pfa.pfa.models;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class VolForm {

    @NotBlank(message = "La date du vol est requise (format: yyyy-MM-dd)")
    private String dateVol;

    @NotBlank(message = "L'heure de départ est requise (format: HH:mm)")
    private String tempsDepart;

    @NotBlank(message = "La durée du voyage est requise (format: PT2H30M par exemple)")
    private String dureeVoyage;

    @NotBlank(message = "La destination est requise")
    private String destination;

    @Positive(message = "Le prix doit être supérieur à 0")
    private double prix;

    @NotBlank(message = "La description est requise")
    private String description;

    @Min(value = 1, message = "Il doit y avoir au moins une place")
    private int nbPlaces;
}
