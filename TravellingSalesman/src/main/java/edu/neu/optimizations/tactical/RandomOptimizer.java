package edu.neu.optimizations.tactical;

import edu.neu.modals.Location;
import edu.neu.modals.TravelPath;

import java.util.Iterator;
import java.util.Random;

import static edu.neu.optimizations.tactical.OptimizationHelperFunctions.swap;
import static edu.neu.utilties.TSPUtilities.calculateDistance;

public class RandomOptimizer implements Iterator<TravelPath> {
    protected final double[][] weightMatrix;
    protected final Location[] locations;
    protected final int maxIterations;
    protected final Random random;
    protected int[] route;
    protected double currentDistance;
    int current = 0;

    public RandomOptimizer(Location[] locations, double[][] weightMatrix, int[] route, Random random,
                           int maxIterations) {
        this.weightMatrix = weightMatrix;
        this.locations = locations;
        this.route = route;
        this.random = random;
        this.maxIterations = maxIterations;
        this.currentDistance = calculateDistance(route, weightMatrix);
        ;
    }

    @Override
    public boolean hasNext() {
        return current < maxIterations;
    }

    @Override
    public TravelPath next() {
        this.route = getNextBestRoute();
        this.currentDistance = calculateDistance(route, weightMatrix);
        current++;
        return new TravelPath(locations, route, weightMatrix);
    }

    private int[] getNextBestRoute() {
        int i = random.nextInt(route.length - 2) + 1;
        int j = random.nextInt(route.length - 2) + 1;

        // ensure i != j
        while (i == j) {
            j = random.nextInt(route.length - 2) + 1;
        }
        int[] newRoute = route.clone();
        swap(newRoute, i, j);
        double newDistance = calculateDistance(newRoute, weightMatrix);

        return newDistance < currentDistance ? newRoute : route;
    }
}
