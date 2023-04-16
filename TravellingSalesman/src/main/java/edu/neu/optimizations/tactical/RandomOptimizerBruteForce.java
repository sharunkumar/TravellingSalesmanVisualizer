package edu.neu.optimizations.tactical;

import edu.neu.modals.Location;
import edu.neu.modals.TravelPath;

import java.util.Random;

import static edu.neu.optimizations.tactical.OptimizationHelperFunctions.swap;
import static edu.neu.utilties.TSPUtilities.calculateDistance;

public class RandomOptimizerBruteForce extends RandomOptimizer {
    int i = 0;
    int j = 1;

    public RandomOptimizerBruteForce(Location[] locations, double[][] weightMatrix, int[] route, Random random,
                                     int maxIterations) {
        super(locations, weightMatrix, route, random, maxIterations);
    }

    @Override
    public boolean hasNext() {
        return i < route.length - 1 && j < route.length - 1;
    }

    @Override
    public TravelPath next() {
        swap(route, i, j);
        var newDistance = calculateDistance(route, weightMatrix);
        if (newDistance < currentDistance) {
            currentDistance = newDistance;

            // reset when hit
            i = 0;
            j = 1;

            return new TravelPath(locations, route);
        } else {
            swap(route, i, j);
        }
        j++;
        if (j == route.length - 1) {
            i++;
            j = i + 1;
        }
        return new TravelPath(locations, route);
    }
}
