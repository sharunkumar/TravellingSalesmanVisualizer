package edu.neu.modals;

import java.util.Arrays;
import java.util.Random;

import static edu.neu.utilties.TSPUtilities.getWeightMatrix;

/**
 * Contains an array of location objects which represents a path through the
 * locations.
 */
public class TravelPath implements Comparable<TravelPath> {
    private final Location[] locations;
    private final int[] route;
    private final double[][] weightMatrix;
    private int hashcode = -1;
    private double distance = -1; // Calculated once then cached.
    private Random random;

    /**
     * Construct the Chromosome from an array. The locations are in the same order
     * as they are in the array. No shuffling is done.
     *
     * @param locations the array of location objects for construction
     */
    public TravelPath(Location[] locations) {
        this.locations = locations.clone();
        this.route = new int[locations.length];
        for (int i = 0; i < locations.length; i++) {
            route[i] = i;
        }
        this.weightMatrix = getWeightMatrix(locations);
    }

    public TravelPath(Location[] locations, int[] route, double[][] weightMatrix) {
        this.weightMatrix = weightMatrix;
        Location[] travelRoute = new Location[locations.length];
        for (int i = 0; i < route.length; i++) {
            travelRoute[i] = locations[route[i]];
        }
        this.locations = travelRoute;
        this.route = route;
    }

    /**
     * Construct the Chromosome from an array of location objects and shuffle them.
     *
     * @param locations the array of location objects for construction
     * @param random    the Random object for shuffling the Chromosome
     */
    public TravelPath(Location[] locations, Random random) {
        this.locations = locations.clone();
        this.random = random;
        shuffle();
        this.route = new int[locations.length];
        for (int i = 0; i < locations.length; i++) {
            route[i] = i;
        }
        this.weightMatrix = getWeightMatrix(locations);
    }

    public int[] getRoute() {
        return route;
    }

    /**
     * Shuffles the locations in the Chromosome.
     */
    private void shuffle() {
        for (int i = 0; i < locations.length; i++) {
            swap(i, random.nextInt(locations.length));
        }
    }

    /**
     * Helper method for shuffling the locations. Swaps two locations.
     *
     * @param i the index of the first location
     * @param j the index of the second location
     */
    private void swap(int i, int j) {
        Location temp = locations[i];
        locations[i] = locations[j];
        locations[j] = temp;
    }

    public Location[] getArray() {
        return locations.clone();
    }

    @Override
    public int compareTo(TravelPath chromosome) {
        return Double.compare(getDistance(), chromosome.getDistance());
    }

    @Override
    public int hashCode() {
        if (hashcode == -1) {
            StringBuilder sb = new StringBuilder();
            for (Location location : locations) {
                sb.append(location.hashCode());
                sb.append(" ");
            }
            hashcode = sb.toString().hashCode();
        }
        return hashcode;
    }

    public double getDistance() {

        // If this was already calculated, don't calculate it again.
        if (distance != -1) {
            return distance;
        }

        double distanceTravelled = 0;

        for (int i = 1; i < locations.length; i++) {
            distanceTravelled += this.weightMatrix[route[i - 1]][route[i]];
        }

        distanceTravelled += this.weightMatrix[route[locations.length - 1]][route[0]];
        this.distance = distanceTravelled;
        return distance;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof TravelPath)) {
            return false;
        }

        TravelPath c = (TravelPath) o;

        return Arrays.equals(c.locations, locations);
    }

    @Override
    public String toString() {
        var dist = getDistance();
        StringBuilder sb = new StringBuilder("Distance = " + dist + ", [ ");
        for (var item : route) {
            sb.append(item);
            sb.append(" ");
        }
        sb.append("]");
        return new String(sb);
    }
}
