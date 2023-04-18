package edu.neu.optimizations.tactical;

import edu.neu.display.TravellingSalesmanWindow;
import edu.neu.modals.Location;
import edu.neu.modals.TravelPath;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;

import static edu.neu.optimizations.tactical.OptimizationHelperFunctions.swap;
import static edu.neu.utilties.TSPUtilities.calculateDistance;

public class RandomOptimizer implements Iterator<TravelPath> {
    protected final double[][] weightMatrix;
    protected final Location[] locations;
    protected final long maxIterations;
    protected final Random random;
    protected final TravellingSalesmanWindow window;
    protected final HashSet<Long> breakPoints = new HashSet<>();
    protected int[] route;
    protected double currentDistance;
    long current = 0;

    public RandomOptimizer(Location[] locations, double[][] weightMatrix, int[] route, Random random,
                           long maxIterations, TravellingSalesmanWindow window) {
        this.weightMatrix = weightMatrix;
        this.locations = locations;
        this.route = route;
        this.random = random;
        this.maxIterations = maxIterations;
        this.window = window;
        this.currentDistance = calculateDistance(route, weightMatrix);

        for (long i = maxIterations; i > 100; i /= 2)
            breakPoints.add(i);
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

        var path = new TravelPath(locations, route, weightMatrix);

        if (breakPoints.contains(current))
            System.out.println("Current: " + current + " Distance: " + currentDistance);

        return path;
    }

    private int[] getNextBestRoute() {
        int i = random.nextInt(route.length - 2) + 1;
        int j = random.nextInt(route.length - 2) + 1;

        // ensure i != j
        while (i == j) {
            j = random.nextInt(route.length - 2) + 1;
        }
        swap(route, i, j);
        double newDistance = calculateDistance(route, weightMatrix);

        if (newDistance >= currentDistance)
            swap(route, i, j); //swap it back

        return route;
    }
}
