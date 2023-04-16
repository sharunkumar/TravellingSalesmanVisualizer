package edu.neu.tactical.optimizations;

public class TwoOptOptimization {

    public static int[] twoOptOptimization(int[] route, double[][] distMatrix) {
        boolean improved = true;
        int[] bestRoute = route.clone();
        int bestDistance = calculateDistance(bestRoute, distMatrix);
        while (improved) {
            improved = false;
            for (int i = 1; i < route.length - 2; i++) {
                for (int j = i + 1; j < route.length; j++) {
                    if (j - i == 1) {
                        continue;
                    }
                    int[] newRoute = route.clone();
                    reverse(newRoute, i, j);
                    int newDistance = calculateDistance(newRoute, distMatrix);
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

    public static int calculateDistance(int[] route, double[][] distMatrix) {
        int distance = 0;
        for (int i = 0; i < route.length - 1; i++) {
            distance += distMatrix[route[i]][route[i+1]];
        }
        distance += distMatrix[route[route.length-1]][route[0]];  // return to the starting point
        return distance;
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
