package org.example.backend.config;

import org.example.backend.SirenStatus;
import org.example.backend.model.Fire;
import org.example.backend.model.Siren;
import org.example.backend.repository.FireRepository;
import org.example.backend.repository.SirenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class InitData implements CommandLineRunner {

    @Autowired
    private SirenRepository repo;

    @Autowired
    private FireRepository fireRepo;

    @Override
    public void run(String... args) {

        Siren s1 = new Siren();
        s1.setLatitude(33.0100);
        s1.setLongitude(-117.4900);
        s1.setStatus(SirenStatus.FRED);
        repo.save(s1);

        Siren s2 = new Siren();
        s2.setLatitude(34.8200);
        s2.setLongitude(-118.1000);
        s2.setStatus(SirenStatus.FRED);
        repo.save(s2);

        Siren s3 = new Siren();
        s3.setLatitude(34.3250);
        s3.setLongitude(-118.5050);
        s3.setStatus(SirenStatus.FRED);
        repo.save(s3);

        Siren s4 = new Siren();
        s4.setLatitude(34.0350);
        s4.setLongitude(-118.8100);
        s4.setStatus(SirenStatus.FRED);

        repo.save(s4);

        Siren s5 = new Siren();
        s5.setLatitude(34.0750);
        s5.setLongitude(-118.6150);
        s5.setStatus(SirenStatus.FRED);

        repo.save(s5);

        Siren s6 = new Siren();
        s6.setLatitude(34.0400);
        s6.setLongitude(-118.5200);
        s6.setStatus(SirenStatus.FRED);
        repo.save(s6);

        Siren s7 = new Siren();
        s7.setLatitude(64.0450);
        s7.setLongitude(-18.5250);
        s7.setStatus(SirenStatus.FRED);
        repo.save(s7);

        Siren s8 = new Siren();
        s8.setLatitude(35.0500);
        s8.setLongitude(-118.5350);
        s8.setStatus(SirenStatus.FRED);

        repo.save(s8);

        Siren s9 = new Siren();
        s9.setLatitude(34.0557);
        s9.setLongitude(-118.5250);
        s9.setStatus(SirenStatus.FRED);
        repo.save(s9);

        Fire f1 = new Fire();
        f1.setLatitude(34.0550);
        f1.setLongitude(-118.5350);
        f1.setTimestamp(LocalDateTime.of(2025, 4, 8, 12, 30));
        f1.setClosed(false);
        fireRepo.save(f1);

        Fire f2 = new Fire();
        f2.setLatitude(34.0550);
        f2.setLongitude(-118.5350);
        f2.setTimestamp(LocalDateTime.of(2025, 4, 8, 12, 30));
        f2.setClosed(false);
        fireRepo.save(f2);
    }
}

