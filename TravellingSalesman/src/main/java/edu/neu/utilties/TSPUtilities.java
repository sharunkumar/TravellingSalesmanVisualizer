package edu.neu.utilties;

import edu.neu.modals.Location;

public class TSPUtilities {
    public static void trySleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static double calculateDistance(int[] route, double[][] distMatrix) {
        double distance = 0;
        for (int i = 0; i < route.length - 1; i++) {
            distance += distMatrix[route[i]][route[i + 1]];
        }
        distance += distMatrix[route[route.length - 1]][route[0]];  // return to the starting point
        return distance;
    }

    public static double[][] getWeightMatrix(Location[] locations) {
        double[][] weightMatrix = new double[locations.length][locations.length];
        for (int i = 0; i < locations.length; i++) {
            for (int j = 0; j < locations.length; j++) {
                weightMatrix[i][j] = trueDistance(locations[i], locations[j]);
            }
        }
        return weightMatrix;
    }

    private static double trueDistance(Location location1, Location location2) {
        double r = 6371; // radius of the Earth in kilometers

        double lat1_rad = Math.toRadians(location1.getLatitude());
        double lon1_rad = Math.toRadians(location1.getLongitude());
        double lat2_rad = Math.toRadians(location2.getLatitude());
        double lon2_rad = Math.toRadians(location2.getLongitude());

        double diff_lat = lat2_rad - lat1_rad;
        double diff_long = lon2_rad - lon1_rad;

        double a =
                Math.sin(diff_lat / 2) * Math.sin(diff_lat / 2) + Math.cos(lat1_rad) * Math.cos(lat2_rad) * Math.sin(diff_long / 2) * Math.sin(diff_long / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return r * c;
    }
}
