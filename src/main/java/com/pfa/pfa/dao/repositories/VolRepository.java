package com.pfa.pfa.dao.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pfa.pfa.dao.entities.Vol;

public interface VolRepository extends JpaRepository<Vol, Long> {
    // You can add custom methods like searching for flights by destination
}
