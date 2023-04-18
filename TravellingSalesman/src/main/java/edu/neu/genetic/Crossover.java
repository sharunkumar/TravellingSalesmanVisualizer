package edu.neu.genetic;

import edu.neu.modals.Location;
import edu.neu.modals.Population;
import edu.neu.modals.TravelPath;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;

import static edu.neu.christofides.Constants.RANDOM;

/**
 * Used for Chromosome reproduction.
 */
class Crossover {
    /**
     * Class cannot be instantiated, as there would be no point, since all
     * the methods are static.
     */
    private Crossover() {
    }

    /**
     * Uses a bit mask to perform a uniform order crossover.
     *
     * @param p1 the first parent Chromosome
     * @param p2 the second parent Chromosome
     * @param r  the Random object for generating a bit mask
     * @return the children
     */
    static ArrayList<TravelPath> uniformOrder(TravelPath p1, TravelPath p2, Random r) {

        Location[] parent1 = p1.getArray();
        Location[] parent2 = p2.getArray();

        Location[] child1 = new Location[parent1.length];
        Location[] child2 = new Location[parent2.length];

        HashSet<Location> locationsInChild1 = new HashSet<>();
        HashSet<Location> locationsInChild2 = new HashSet<>();

        ArrayList<Location> locationsNotInChild1 = new ArrayList<>();
        ArrayList<Location> locationsNotInChild2 = new ArrayList<>();

        ArrayList<TravelPath> children = new ArrayList<>();

        int[] bitMask = generateBitMask(parent1.length, r);

        // Inherit the locations of the same parent where the bit-mask is 1.
        // Example: child 1 has all the same locations as parent 1 at the indexes where
        // the
        // bit-mask is 1.
        for (int i = 0; i < bitMask.length; i++) {
            if (bitMask[i] == 1) {
                child1[i] = parent1[i];
                child2[i] = parent2[i];
                locationsInChild1.add(parent1[i]);
                locationsInChild2.add(parent2[i]);
            }
        }

        // Get the locations of the opposite parent if the child does not already
        // contain
        // them.
        for (int i = 0; i < child1.length; i++) {
            if (child1[i] == null && !locationsInChild1.contains(parent2[i])) {
                child1[i] = parent2[i];
                locationsInChild1.add(parent2[i]);
            } else if (child1[i] != null && !locationsInChild1.contains(parent2[i])) {
                locationsNotInChild1.add(parent2[i]);
            }
            if (child2[i] == null && !locationsInChild2.contains(parent1[i])) {
                child2[i] = parent1[i];
                locationsInChild2.add(parent1[i]);
            } else if (child2[i] != null && !locationsInChild2.contains(parent1[i])) {
                locationsNotInChild2.add(parent1[i]);
            }
        }

        // Fill in the blanks.
        for (int i = 0; i < child1.length; i++) {
            if (child1[i] == null) {
                child1[i] = locationsNotInChild1.remove(0);
            }
            if (child2[i] == null) {
                child2[i] = locationsNotInChild2.remove(0);
            }
        }

        if (!locationsNotInChild1.isEmpty() || !locationsNotInChild2.isEmpty()) {
            throw new AssertionError("Lists should be empty.");
        }

        TravelPath childOne = new TravelPath(child1);
        TravelPath childTwo = new TravelPath(child2);
        children.add(childOne);
        children.add(childTwo);

        return children;
    }

    /**
     * Generate an array of a specified sizes with randomly placed ones and zeroes.
     *
     * @param size   the size of the array
     * @param random the Random object used for generating the random ones and
     *               zeroes
     * @return an array with randomly places ones and zeroes
     */
    private static int[] generateBitMask(int size, Random random) {

        int[] array = new int[size];

        for (int i = 0; i < array.length; i++) {
            array[i] = (random.nextInt(2) == 0) ? 0 : 1;
        }

        return array;
    }

    /**
     * Performs a crossover on all the locations after a particular point.
     *
     * @param p1 the first parent chromosome
     * @param p2 the second parent chromosome
     * @param r  the Random object for selecting a point
     * @return the children
     */
    static ArrayList<TravelPath> onePointCrossover(TravelPath p1, TravelPath p2, Random r) {
        Location[] parent1 = p1.getArray();
        Location[] parent2 = p2.getArray();

        Location[] child1 = new Location[parent1.length];
        Location[] child2 = new Location[parent2.length];

        HashSet<Location> locationsInChild1 = new HashSet<>();
        HashSet<Location> locationsInChild2 = new HashSet<>();

        ArrayList<Location> locationsNotInChild1 = new ArrayList<>();
        ArrayList<Location> locationsNotInChild2 = new ArrayList<>();

        ArrayList<TravelPath> children = new ArrayList<>();
        int totalLocations = parent1.length;

        int randomPoint = r.nextInt(totalLocations);

        // Inherit the locations up to the point.
        for (int i = 0; i < randomPoint; i++) {
            child1[i] = parent1[i];
            child2[i] = parent2[i];
            locationsInChild1.add(parent1[i]);
            locationsInChild2.add(parent2[i]);
        }

        // Get the locations of the opposite parent if the child does not already
        // contain
        // them.
        for (int i = randomPoint; i < totalLocations; i++) {
            if (!locationsInChild1.contains(parent2[i])) {
                locationsInChild1.add(parent2[i]);
                child1[i] = parent2[i];
            }
            if (!locationsInChild2.contains(parent1[i])) {
                locationsInChild2.add(parent1[i]);
                child2[i] = parent1[i];
            }
        }

        // Find all the locations that are still missing from each child.
        for (int i = 0; i < totalLocations; i++) {
            if (!locationsInChild1.contains(parent2[i])) {
                locationsNotInChild1.add(parent2[i]);
            }
            if (!locationsInChild2.contains(parent1[i])) {
                locationsNotInChild2.add(parent1[i]);
            }
        }

        // Find which spots are still empty in each child.
        ArrayList<Integer> emptySpotsC1 = new ArrayList<>();
        ArrayList<Integer> emptySpotsC2 = new ArrayList<>();
        for (int i = 0; i < totalLocations; i++) {
            if (child1[i] == null) {
                emptySpotsC1.add(i);
            }
            if (child2[i] == null) {
                emptySpotsC2.add(i);
            }
        }

        // Fill in the empty spots.
        for (Location location : locationsNotInChild1) {
            child1[emptySpotsC1.remove(0)] = location;
        }
        for (Location location : locationsNotInChild2) {
            child2[emptySpotsC2.remove(0)] = location;
        }

        TravelPath childOne = new TravelPath(child1);
        TravelPath childTwo = new TravelPath(child2);
        children.add(childOne);
        children.add(childTwo);

        return children;
    }

    /**
     * Performs a crossover on all the locations between two points.
     *
     * @param p1 the first parent chromosome
     * @param p2 the second parent chromosome
     * @param r  the Random object for selecting a point
     * @return the children
     */
    static ArrayList<TravelPath> orderCrossover(TravelPath p1, TravelPath p2, Random r) {
        Location[] parent1 = p1.getArray();
        Location[] parent2 = p2.getArray();

        Location[] child1 = new Location[parent1.length];
        Location[] child2 = new Location[parent2.length];

        HashSet<Location> locationsInChild1 = new HashSet<>();
        HashSet<Location> locationsInChild2 = new HashSet<>();

        ArrayList<Location> locationsNotInChild1 = new ArrayList<>();
        ArrayList<Location> locationsNotInChild2 = new ArrayList<>();

        ArrayList<TravelPath> children = new ArrayList<>();
        int totalLocations = parent1.length;

        int firstPoint = r.nextInt(totalLocations);
        int secondPoint = r.nextInt(totalLocations - firstPoint) + firstPoint;

        // Inherit the locations before and after the points selected.
        for (int i = 0; i < firstPoint; i++) {
            child1[i] = parent1[i];
            child2[i] = parent2[i];
            locationsInChild1.add(parent1[i]);
            locationsInChild2.add(parent2[i]);
        }
        for (int i = secondPoint; i < totalLocations; i++) {
            child1[i] = parent1[i];
            child2[i] = parent2[i];
            locationsInChild1.add(parent1[i]);
            locationsInChild2.add(parent2[i]);
        }

        // Get the locations of the opposite parent if the child does not already
        // contain
        // them.
        for (int i = firstPoint; i < secondPoint; i++) {
            if (!locationsInChild1.contains(parent2[i])) {
                locationsInChild1.add(parent2[i]);
                child1[i] = parent2[i];
            }
            if (!locationsInChild2.contains(parent1[i])) {
                locationsInChild2.add(parent1[i]);
                child2[i] = parent1[i];
            }
        }

        // Find all the locations that are still missing from each child.
        for (int i = 0; i < totalLocations; i++) {
            if (!locationsInChild1.contains(parent2[i])) {
                locationsNotInChild1.add(parent2[i]);
            }
            if (!locationsInChild2.contains(parent1[i])) {
                locationsNotInChild2.add(parent1[i]);
            }
        }

        // Find which spots are still empty in each child.
        ArrayList<Integer> emptySpotsC1 = new ArrayList<>();
        ArrayList<Integer> emptySpotsC2 = new ArrayList<>();
        for (int i = 0; i < totalLocations; i++) {
            if (child1[i] == null) {
                emptySpotsC1.add(i);
            }
            if (child2[i] == null) {
                emptySpotsC2.add(i);
            }
        }

        // Fill in the empty spots.
        for (Location location : locationsNotInChild1) {
            child1[emptySpotsC1.remove(0)] = location;
        }
        for (Location location : locationsNotInChild2) {
            child2[emptySpotsC2.remove(0)] = location;
        }

        TravelPath childOne = new TravelPath(child1);
        TravelPath childTwo = new TravelPath(child2);
        children.add(childOne);
        children.add(childTwo);

        return children;
    }

    public static void main(String[] args) {
        Population pop = Population.getRandomPopulation(10, 10);
        TravelPath c1 = new TravelPath(pop.getLocations(), RANDOM);
        TravelPath c2 = new TravelPath(pop.getLocations(), RANDOM);

        System.out.println("Children:");
        System.out.println(c1);
        System.out.println(c2);
        System.out.println();

        ArrayList<TravelPath> list = orderCrossover(c1, c2, RANDOM);
        System.out.println(list.get(0));
        System.out.println(list.get(1));
    }

}
