package org.example.backend.util;

public class GeoUtils {
    private static final int EARTH_RADIUS_KM = 6371;

    public static double calculateDistanceKM(double lat1, double lon1, double lat2, double lon2) {
        double latRad1 = Math.toRadians(lat1);
        double lonRad1 = Math.toRadians(lon1);
        double latRad2 = Math.toRadians(lat2);
        double lonRad2 = Math.toRadians(lon2);

        double dLat = latRad2 - latRad1;
        double dLon = lonRad2 - lonRad1;

        double a = Math.pow(Math.sin(dLat / 2), 2)
                + Math.cos(latRad1) * Math.cos(latRad2) * Math.pow(Math.sin(dLon / 2), 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return EARTH_RADIUS_KM * c;
    }
}

