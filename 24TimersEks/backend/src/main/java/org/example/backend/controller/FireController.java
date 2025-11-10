package org.example.backend.controller;

import org.example.backend.dto.LocationDTO;
import org.example.backend.model.Fire;
import org.example.backend.model.Siren;
import org.example.backend.service.FireService;
import org.example.backend.service.SirenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@CrossOrigin(origins ="*")
@RestController
@RequestMapping("/fire")
public class FireController {

    @Autowired
    private FireService fireService;

    @Autowired
    private SirenService sirenService;

    @GetMapping
    public List<Fire> getAllFires() {
        return fireService.getAllFiresWithSirens();
    }

    @GetMapping("/activeFire")
    public ResponseEntity<List<Fire>> getActiveFires() {
        List<Fire> activeFires = fireService.getFiresWhereClosedIsFalse();
        return ResponseEntity.ok(activeFires);
    }

    @PostMapping("/fireAlarm")
    public ResponseEntity<?> createFireAndActivateSirens(@RequestBody LocationDTO fireLocation ) {
        Fire newFire = new Fire();
        newFire.setLatitude(fireLocation.latitude());
        newFire.setLongitude(fireLocation.longitude());
        newFire.setTimestamp(LocalDateTime.now());
        newFire.setClosed(false);

        List<Siren> activatedSirens = sirenService.activateSirensForFire(fireLocation, 10.0);
        newFire.setSirens(activatedSirens);

        Fire savedFire = fireService.createFire(newFire);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedFire);
    }

    @PutMapping("/closeFireWith/{id}")
    public ResponseEntity<?> closeFireAndResetSirens(@PathVariable int id) {
        try {
            Fire fire = fireService.getFireByIdWithSirens(id)
                    .orElseThrow(() -> new IllegalArgumentException("Fire with id " + id + " not found"));

            fireService.closeFireAndResetSirens(fire);

            return ResponseEntity.ok("Fire closed and sirens reset");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PutMapping("updateFire/{id}")
    public ResponseEntity<Fire> updateFire(@PathVariable int id, @RequestBody Fire fire) {
        Fire createdFire = fireService.updateFire(fire);
        return ResponseEntity.status(HttpStatus.OK).body(createdFire);
    }

    @DeleteMapping("deleteFire/{id}")
    public void deleteFire(@PathVariable int id) {
        fireService.deleteFire(id);
    }
}
