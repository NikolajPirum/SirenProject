package org.example.backend.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GeoUtilsTest {

    @Test
    void testCalculateDistanceKM() {
        double lat1 = 34.0100;
        double lon1 = -118.4962;

        double lat2 = 34.0356;
        double lon2 = -118.5156;

        double distance = GeoUtils.calculateDistanceKM(lat1, lon1, lat2, lon2);
        assertTrue(distance < 5.0 && distance > 1.0); // Ca. 2.5 km
    }
}
