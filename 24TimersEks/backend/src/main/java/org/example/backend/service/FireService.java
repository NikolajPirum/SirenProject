package org.example.backend.service;

import org.example.backend.SirenStatus;
import org.example.backend.model.Fire;
import org.example.backend.model.Siren;
import org.example.backend.repository.FireRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FireService {

    @Autowired
    private FireRepository fireRepository;

    public List<Fire> getAllFire() {
        return fireRepository.findAll();
    }
    public Fire getFireById(int id) {
        return fireRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Fire with id " + id + " not found"));
    }
    public Fire createFire(Fire fire) {
        return fireRepository.save(fire);
    }
    public Fire updateFire(Fire fire) {
        if(!fireRepository.existsById(fire.getFireId())){
            throw new IllegalArgumentException("Fire with id " + fire.getFireId() + " not found");
        }
        return fireRepository.save(fire);
    }
    public void deleteFire(int id) {
        fireRepository.deleteById(id);
    }

    public List<Siren> getSirensByFireId(int fireId) {
        return fireRepository.findByIdWithSirens(fireId)
                .map(Fire::getSirens)
                .orElseThrow(() -> new IllegalArgumentException("Fire with id " + fireId + " not found"));
    }
    public Optional<Fire> getFireByIdWithSirens(int id) {
        return fireRepository.findByIdWithSirens(id);
    }
    public List<Fire> getFiresWhereClosedIsFalse() {
        return fireRepository.findByClosedFalse();
    }
    public List<Fire> getAllFiresWithSirens() {
        return fireRepository.findAllWithSirens();
    }
    public void closeFireAndResetSirens(Fire fire) {
        fire.setClosed(true);

        List<Siren> sirens = fire.getSirens();
        for (Siren siren : sirens) {
            if (siren.getStatus() != SirenStatus.DISABLED) {
                siren.setStatus(SirenStatus.FRED);
            }
        }
        updateFire(fire);
    }

}
