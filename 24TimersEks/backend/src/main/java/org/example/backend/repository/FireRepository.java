package org.example.backend.repository;

import org.example.backend.model.Fire;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface FireRepository extends JpaRepository<Fire, Integer> {

    List<Fire> findByClosedFalse();

    @Query("SELECT f FROM Fire f LEFT JOIN FETCH f.sirens WHERE f.id = :id")
    Optional<Fire> findByIdWithSirens(@Param("id") Integer id);

    @Query("SELECT f FROM Fire f LEFT JOIN FETCH f.sirens")
    List<Fire> findAllWithSirens();
}


