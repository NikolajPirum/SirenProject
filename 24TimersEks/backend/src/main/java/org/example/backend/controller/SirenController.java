package org.example.backend.controller;

import org.example.backend.model.Siren;
import org.example.backend.service.SirenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/sirens")
public class SirenController {

    @Autowired
    private SirenService sirenService;

    @GetMapping
    public ResponseEntity<List<Siren>> getAll() {
        return ResponseEntity.ok(sirenService.getAllSiren());
    }

    @PostMapping("/newSiren")
    public ResponseEntity<?> createSiren(@RequestBody Siren siren) {
        try {
            Siren createdSiren = sirenService.createSiren(siren);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdSiren);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Fejl ved oprettelse af sirene: " + e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Siren> updateSiren(@PathVariable int id, @RequestBody Siren siren) {
        siren.setSirenId(id);
        Siren UpdatedSiren = sirenService.updateSiren(siren);
        return ResponseEntity.status(HttpStatus.OK).body(UpdatedSiren);
    }

    @DeleteMapping("/{id}")
    public void deleteSiren(@PathVariable int id) {
        sirenService.deleteSirenById(id);
    }
}
