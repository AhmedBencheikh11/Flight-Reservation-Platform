// src/main/java/com/pfa/pfa/business/services/VolService.java
package com.pfa.pfa.business.services;

import java.util.List;
import com.pfa.pfa.dao.entities.Vol;

public interface VolService {
    List<Vol> getAllVols();          // list all vols
    Vol getVolById(Long id);         // find one vol
    Vol saveVol(Vol vol);            // create or update a vol
    void deleteVol(Long id);         // delete by id
}
