package org.example.backend.service;

import org.example.backend.SirenStatus;
import org.example.backend.dto.LocationDTO;
import org.example.backend.model.Siren;
import org.example.backend.repository.SirenRepository;
import org.example.backend.util.GeoUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SirenService {

    @Autowired
    private SirenRepository sirenRepository;

    public List<Siren> getAllSiren() {
        return sirenRepository.findAll();
    }

    public Siren createSiren(Siren siren) {
        return sirenRepository.save(siren);
    }
    public Siren updateSiren(Siren siren) {
        if(!sirenRepository.existsById(siren.getSirenId())) {
            throw new IllegalArgumentException("Siren with id: " + siren.getSirenId() + " not found");
        }
        return sirenRepository.save(siren);
    }
    public void deleteSirenById(int id) {
        sirenRepository.deleteById(id);
    }
    public List<Siren> activateSirensForFire(LocationDTO fireLocation, double radiusKm) {
        List<Siren> allSirens = sirenRepository.findAll();
        List<Siren> activatedSirens = new ArrayList<>();

        for (Siren siren : allSirens) {
            if (siren.getStatus() == SirenStatus.DISABLED) {
                continue;
            }

            double distance = GeoUtils.calculateDistanceKM(
                    siren.getLatitude(), siren.getLongitude(),
                    fireLocation.latitude(), fireLocation.longitude()
            );

            if (distance <= radiusKm) {
                siren.setStatus(SirenStatus.FARE);
                activatedSirens.add(siren);
            } else {
                siren.setStatus(SirenStatus.FRED);
            }
            sirenRepository.save(siren);
        }
        return activatedSirens;
    }
}
