package org.example.backend.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Fire {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int fireId;

    private double latitude;
    private double longitude;
    private LocalDateTime timestamp;

    private boolean closed = true; // angiver om branden stadig er aktiv

    @ManyToMany
    @JoinTable(
            name = "fire_siren",
            joinColumns = @JoinColumn(name = "fire_id"),
            inverseJoinColumns = @JoinColumn(name = "siren_id"))
    @JsonManagedReference
    private List<Siren> sirens;
}
