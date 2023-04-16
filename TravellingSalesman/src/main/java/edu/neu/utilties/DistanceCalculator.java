package edu.neu.utilties;

public class DistanceCalculator {
    public static double calculateDistance(int[] route, double[][] distMatrix) {
        double distance = 0;
        for (int i = 0; i < route.length - 1; i++) {
            distance += distMatrix[route[i]][route[i + 1]];
        }
        distance += distMatrix[route[route.length - 1]][route[0]];  // return to the starting point
        return distance;
    }
}
