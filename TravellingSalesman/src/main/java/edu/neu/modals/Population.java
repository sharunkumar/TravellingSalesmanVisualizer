package edu.neu.modals;

import edu.neu.io.DataSet;

import java.nio.BufferOverflowException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.Random;

/**
 * Represents a Population of chromosomes.
 */
public class Population implements Iterable<TravelPath> {
    private final PriorityQueue<TravelPath> chromosomes;
    private final int maxSize;

    /**
     * Constructs an empty population with a maximum size.
     *
     * @param maxSize the maximum size of the Population
     */
    public Population(int maxSize) {
        this.maxSize = maxSize;
        chromosomes = new PriorityQueue<>();
    }

    public static Population fromDataSet(int popSize, DataSet dataSet, Random r) {
        Location[] locations = dataSet.getNormalizedLocations(500);
        Population population = new Population(popSize);
        population.populate(locations, r);
        return population;
    }

    /**
     * Generate a Population of randomly generate Chromosomes.
     *
     * @param numOfLocations the number of locations
     * @param sizeOfPop      the size of the population
     * @param random         the Random object used for the generation
     * @return a randomly generated Population
     */
    public static Population getRandomPopulation(int numOfLocations, int sizeOfPop, Random random) {
        Location[] locations = new Location[numOfLocations];

        for (int i = 0; i < numOfLocations; i++) {
            locations[i] = Location.getRandomLocation(random);
        }

        Population population = new Population(sizeOfPop);

        for (int i = 0; i < sizeOfPop; i++) {
            population.add(new TravelPath(locations, random));
        }

        return population;
    }

    /**
     * Adds a Chromosome to the Population.
     *
     * @param chromosome the chromosome to add
     */
    public void add(TravelPath chromosome) {
        if (chromosomes.size() == maxSize) {
            throw new BufferOverflowException();
        }
        chromosomes.add(chromosome);
    }

    public void populate(Location[] locations, Random random) {

        if (chromosomes.size() == maxSize) {
            throw new BufferOverflowException();
        }

        // If the popSize is greater than the factorial of locations, uniqueness not
        // possible.
        // Example: if there are 2 locations but the population size is 100, it is
        // impossible
        // to have all unique values since there are at most 2! = 2 unique
        // possibilities.
        if ((locations.length == 1 && maxSize > 1) ||
                (locations.length == 2 && maxSize > 2) ||
                (locations.length == 3 && maxSize > 6) ||
                (locations.length == 4 && maxSize > 24) ||
                (locations.length == 5 && maxSize > 120) ||
                (locations.length == 6 && maxSize > 720) ||
                (locations.length == 7 && maxSize > 5_040) ||
                (locations.length == 8 && maxSize > 40_320) ||
                (locations.length == 9 && maxSize > 362_880)) {
            throw new IllegalStateException("Cannot force uniqueness when" +
                    " the population size is greater than the factorial" +
                    " of the total number of locations.");
        }

        HashSet<TravelPath> hashSet = new HashSet<>();

        while (chromosomes.size() < maxSize) {
            TravelPath chromo = new TravelPath(locations, random);
            if (!hashSet.contains(chromo)) {
                hashSet.add(chromo);
                add(chromo);
            }
        }

    }

    /**
     * Removes all the Chromosomes.
     */
    public void clear() {
        chromosomes.clear();
    }

    /**
     * Get an array of all the locations.
     *
     * @return the array of locations
     */
    public Location[] getLocations() {
        return chromosomes.peek().getArray().clone();
    }

    /**
     * Get an array of all the Chromosomes.
     *
     * @return the array of the Chromosomes
     */
    public TravelPath[] getChromosomes() {
        TravelPath[] array = new TravelPath[chromosomes.size()];

        int i = 0;
        for (TravelPath chromo : chromosomes) {
            array[i++] = chromo;
        }

        return array;
    }

    /**
     * Get the size of the Population.
     *
     * @return the number of all the Chromosomes.
     */
    public int size() {
        return chromosomes.size();
    }

    /**
     * Gets the average distance of all the chromosomes.
     *
     * @return the mean distance travelled
     */
    public double getAverageDistance() {

        double averageDistance = 0;

        for (TravelPath chromosome : chromosomes) {
            averageDistance += chromosome.getDistance();
        }

        return averageDistance / chromosomes.size();
    }

    /**
     * Get the Chromosome that has the path with the least distance.
     *
     * @return the most fit Chromosome
     */
    public TravelPath getMostFit() {
        return chromosomes.peek();
    }

    public Iterator<TravelPath> iterator() {
        return chromosomes.iterator();
    }

    public Population deepCopy() {
        Population population = new Population(maxSize);
        chromosomes.forEach((chromosome) -> population.add(chromosome));
        return population;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Population:");

        for (TravelPath chromosome : chromosomes) {
            sb.append("\n");
            sb.append(chromosome);
            sb.append(" Value: ");
            sb.append(chromosome.getDistance());
        }

        return new String(sb);
    }

}
