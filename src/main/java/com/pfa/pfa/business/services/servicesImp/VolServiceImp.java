// src/main/java/com/pfa/pfa/business/services/servicesImp/VolServiceImp.java
package com.pfa.pfa.business.services.servicesImp;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pfa.pfa.business.services.VolService;
import com.pfa.pfa.dao.entities.Vol;
import com.pfa.pfa.dao.repositories.VolRepository;

import java.util.List;

@Service
@Transactional
public class VolServiceImp implements VolService {

    private final VolRepository volRepository;

    public VolServiceImp(VolRepository volRepository) {
        this.volRepository = volRepository;
    }

    @Override
    public List<Vol> getAllVols() {
        return volRepository.findAll();
    }

    @Override
    public Vol getVolById(Long id) {
        return volRepository.findById(id).orElse(null);
    }

    @Override
    public Vol saveVol(Vol vol) {
        // JPA save() handles both insert and update (if vol.id_vol is set)
        return volRepository.save(vol);
    }

    @Override
    public void deleteVol(Long id) {
        volRepository.deleteById(id);
    }
}
