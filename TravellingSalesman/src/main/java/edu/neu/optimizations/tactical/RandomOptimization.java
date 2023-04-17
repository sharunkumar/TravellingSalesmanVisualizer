package edu.neu.optimizations.tactical;

import edu.neu.modals.Location;
import edu.neu.modals.TravelPath;

import java.util.Arrays;
import java.util.Random;

import static edu.neu.utilties.TSPUtilities.calculateDistance;

public class RandomOptimization extends OptimizationHelperFunctions {

    public static TravelPath randomSwappingRoute(Location[] locations, double[][] weightMatrix,
                                                 int[] initialRoute) {

        var optimizedRoute = randomSwappingRoute(weightMatrix, initialRoute);

        System.out.println("Random Swapping Shortest path: " + Arrays.toString(optimizedRoute));

        return new TravelPath(locations, optimizedRoute, weightMatrix);
    }

    public static int[] randomSwappingRoute(double[][] weightMatrix, int[] initialRoute) {

        double sum = calculateDistance(initialRoute, weightMatrix);

        Random random = new Random();
        boolean improved = true;
        int count = 0;
        while (improved) {
            improved = false;
            for (int i = 1; i < initialRoute.length - 1; i++) {
                for (int j = i + 1; j < initialRoute.length - 1; j++) {
                    swap(initialRoute, i, j);
                    double newSum = calculateDistance(initialRoute, weightMatrix);
                    if (newSum < sum) {
                        count++;
                        System.out.println("NewSum = " + newSum + " Swapped Count = " + count + " Swapped " + initialRoute[j] + " and " + initialRoute[i]);
                        sum = newSum;
                        improved = true;
                    } else {
                        swap(initialRoute, i, j);
                    }
                }
            }
        }


        return initialRoute;

    }
}
