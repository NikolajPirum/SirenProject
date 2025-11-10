package org.example.backend.service;

import org.example.backend.SirenStatus;
import org.example.backend.model.Siren;
import org.example.backend.repository.SirenRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SirenServiceTest {

    @Mock
    private SirenRepository sirenRepository;

    @InjectMocks
    private SirenService sirenService;

    @Test
    void getAllSiren_shouldReturnAllSirens() {
        // Arrange
        Siren s1 = new Siren(1, 55.67, 12.56, SirenStatus.FRED);
        Siren s2 = new Siren(2, 56.70, 10.25, SirenStatus.FARE);

        when(sirenRepository.findAll()).thenReturn(Arrays.asList(s1, s2));

        // Act
        List<Siren> result = sirenService.getAllSiren();

        // Assert
        assertEquals(2, result.size());
        assertEquals(SirenStatus.FRED, result.get(0).getStatus());
        verify(sirenRepository, times(1)).findAll();
    }
}
