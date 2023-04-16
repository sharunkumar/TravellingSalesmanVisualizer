package edu.neu.utilties;

import edu.neu.modals.Location;

public class DistanceCalculator {
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
                weightMatrix[i][j] = locations[i].distanceTo(locations[j]);
            }
        }
        return weightMatrix;
    }
}
