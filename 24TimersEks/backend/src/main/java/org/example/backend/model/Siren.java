package org.example.backend.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.backend.SirenStatus;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Siren {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int sirenId;

    private double latitude;
    private double longitude;
    private SirenStatus status;


    @ManyToMany(mappedBy = "sirens")
    @JsonIgnore
    private List<Fire> fires;

    public Siren(int i, double v, double v1, SirenStatus sirenStatus) {
        this.sirenId = i;
        this.latitude = v;
        this.longitude = v1;
        this.status = sirenStatus;
    }
}
