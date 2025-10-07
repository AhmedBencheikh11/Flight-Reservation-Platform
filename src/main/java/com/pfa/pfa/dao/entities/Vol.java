package com.pfa.pfa.dao.entities;

import jakarta.persistence.*;
import lombok.*;
import java.time.*;

@Entity
@Table(name = "vols")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Vol {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_vol;

    private LocalDate dateVol;
    private LocalTime tempsDepart;
    private String dureeVoyage;
    private String destination;
    private double prix;
    private String description;
    private int nbPlaces;
}
