package edu.neu.optimizations.tactical;

import edu.neu.display.TravellingSalesmanWindow;
import edu.neu.modals.Location;
import edu.neu.modals.TravelPath;

import static edu.neu.utilties.TSPUtilities.calculateDistance;

public class TwoOptOptimization {

    public static int[] twoOptOptimization(int[] route, double[][] distMatrix) {
        boolean improved = true;
        int[] bestRoute = route.clone();
        double bestDistance = calculateDistance(bestRoute, distMatrix);
        while (improved) {
            improved = false;
            for (int i = 1; i < route.length - 2; i++) {
                for (int j = i + 1; j < route.length; j++) {
                    if (j - i == 1) {
                        continue;
                    }
                    int[] newRoute = route.clone();
                    reverse(newRoute, i, j);
                    double newDistance = calculateDistance(newRoute, distMatrix);
                    if (newDistance < bestDistance) {
                        bestDistance = newDistance;
                        bestRoute = newRoute;
                        improved = true;
                    }
                }
            }
            route = bestRoute;
        }
        return bestRoute;
    }

    public static int[] twoOptOptimization(int[] route, double[][] weightMatrix, TravellingSalesmanWindow window,
                                           Location[] locations) {
        boolean improved = true;
        int[] bestRoute = route.clone();
        double bestDistance = calculateDistance(bestRoute, weightMatrix);
        while (improved) {
            improved = false;
            for (int i = 1; i < route.length - 2; i++) {
                for (int j = i + 1; j < route.length; j++) {
                    if (j - i == 1) {
                        continue;
                    }
                    int[] newRoute = route.clone();
                    reverse(newRoute, i, j);
                    double newDistance = calculateDistance(newRoute, weightMatrix);
                    if (newDistance < bestDistance) {
                        bestDistance = newDistance;
                        bestRoute = newRoute;
                        window.drawPath(new TravelPath(locations, bestRoute, weightMatrix));
                        improved = true;
                    }
                }
            }
            route = bestRoute;
        }
        window.drawPath(new TravelPath(locations, bestRoute, weightMatrix));
        return bestRoute;
    }

    public static void reverse(int[] arr, int i, int j) {
        while (i < j) {
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
            i++;
            j--;
        }
    }


}
