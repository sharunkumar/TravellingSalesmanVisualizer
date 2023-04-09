package edu.neu.modals;

import java.util.Arrays;
import java.util.Random;

/**
 * Contains an array of City objects which represents a path through the cities.
 */
public class TravelPath implements Comparable<TravelPath> {

    private Location[] locations;
    private int distance = -1; // Calculated once then cached.
    private Random random;

    /**
     * Construct the Chromosome from an array. The cities are in the same order
     * as they are in the array. No shuffling is done.
     *
     * @param cities the array of City objects for construction
     */
    public TravelPath(Location[] cities) {
        this.locations = cities.clone();
    }

    /**
     * Construct the Chromosome from an array of City objects and shuffle them.
     *
     * @param cities the array of City objects for construction
     * @param random the Random object for shuffling the Chromosome
     */
    public TravelPath(Location[] cities, Random random) {
        this.locations = cities.clone();
        this.random = random;
        shuffle();
    }

    /**
     * Shuffles the cities in the Chromosome.
     */
    private void shuffle() {
        for (int i = 0; i < locations.length; i++) {
            swap(i, random.nextInt(locations.length));
        }
    }

    /**
     * Helper method for shuffling the cities. Swaps two cities.
     *
     * @param i the index of the first city
     * @param j the index of the second city
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
        return getDistance() - chromosome.getDistance();
    }

    @Override
    public int hashCode() {
        StringBuilder sb = new StringBuilder();
        for (Location city : locations) {
            sb.append(city);
        }
        return (new String(sb)).hashCode();
    }

    public int getDistance() {

        // If this was already calculated, don't calculate it again.
        if (distance != -1) {
            return distance;
        }

        double distanceTravelled = 0;

        for (int i = 1; i < locations.length; i++) {
            distanceTravelled += Location.distance(locations[i - 1], locations[i]);
        }

        distanceTravelled += Location.distance(locations[locations.length - 1], locations[0]);
        this.distance = (int) distanceTravelled;
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
        StringBuilder sb = new StringBuilder("[ ");
        for (Location item : locations) {
            sb.append(item.getName());
            sb.append(" ");
        }
        sb.append("]");
        return new String(sb);
    }
}
