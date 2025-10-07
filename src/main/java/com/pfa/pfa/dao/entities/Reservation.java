package com.pfa.pfa.dao.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "reservations")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_reservation;

    @ManyToOne
    @JoinColumn(name = "vol_id", referencedColumnName = "id_vol", nullable = false)
    private Vol vol;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id_user", nullable = false)
    private User user;

    // NEW FIELD: passport number
    @Column(name = "passport_number", nullable = false)
    private String passportNumber;

    @Column(nullable = false)
    private String destination;

}
