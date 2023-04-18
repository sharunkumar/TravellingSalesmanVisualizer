package edu.neu.utilties;

import edu.neu.modals.Location;
import org.apache.lucene.util.SloppyMath;

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

    public static double trueDistance(Location location1, Location location2) {
        double lat1_rad = location1.getLatitude();
        double lon1_rad = location1.getLongitude();
        double lat2_rad = location2.getLatitude();
        double lon2_rad = location2.getLongitude();

        return SloppyMath.haversinMeters(lat1_rad, lon1_rad, lat2_rad, lon2_rad);

    }
}
