package edu.neu.optimizations.tactical;

import static edu.neu.utilties.DistanceCalculator.calculateDistance;

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
