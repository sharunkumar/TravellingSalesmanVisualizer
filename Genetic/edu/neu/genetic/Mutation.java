package edu.neu.genetic;

import java.util.Random;

import edu.neu.modals.Location;
import edu.neu.modals.TravelPath;

/**
 * Used for mutating the Chromosomes.
 */
class Mutation {

    /**
     * Class cannot be instantiated, as there would be no point, since all
     * the methods are static.
     */
    private Mutation() {
    }

    /**
     * Selects a location and inserts it into a random place.
     * 
     * @param path   The Chromosome who's locations will be swapped.
     * @param random The Random object used for randomly selecting the locations
     * @return the mutated Chromosome
     */
    static TravelPath insertion(TravelPath path, Random random) {
        Location[] locations = path.getArray();
        int randomIndex = random.nextInt(locations.length);
        int randomDestination = random.nextInt(locations.length);

        if (randomIndex < randomDestination) {
            Location temp = locations[randomIndex];
            for (int i = randomIndex; i < randomDestination; i++) {
                locations[i] = locations[i + 1];
            }
            locations[randomDestination] = temp;
        } else {
            Location temp = locations[randomIndex];
            for (int i = randomIndex; i > randomDestination; i--) {
                locations[i] = locations[i - 1];
            }
            locations[randomDestination] = temp;
        }
        return new TravelPath(locations);
    }

    /**
     * Swaps two randomly selected locations.
     * 
     * @param path   The Chromosome who's locations will be swapped.
     * @param random The Random object used for randomly selecting the locations
     * @return the mutated Chromosome
     */
    static TravelPath reciprocalExchange(TravelPath path, Random random) {
        Location[] locations = path.getArray();
        int l = locations.length;
        swap(locations, random.nextInt(l), random.nextInt(l));
        return new TravelPath(locations);
    }

    /**
     * Pick a subset of locations and randomly re-arrange them.
     * 
     * @param chromosome The Chromosome who's locations will be swapped.
     * @param random     The Random object used for randomly selecting the locations
     * @return the mutated Chromosome
     */
    static TravelPath scrambleMutation(TravelPath chromosome, Random random) {

        /**
         * The subset locations include wrapping.
         * Example: if there is a Chromosome with 10 locations and randomIndexStart is 8
         * and randomIndexEnd is 2, that means that the subset will include the
         * locations
         * at indexes 8, 9, 1, and 2.
         */

        Location[] locations = chromosome.getArray();
        int randomIndexStart = random.nextInt(locations.length);
        int randomIndexEnd = random.nextInt(locations.length);

        for (int i = randomIndexStart; i % locations.length != randomIndexEnd; i++) {
            int r = random.nextInt(Math.abs(i % locations.length - randomIndexEnd));
            swap(locations, i % locations.length, (i + r) % locations.length);
        }

        return new TravelPath(locations);
    }

    /**
     * Helper method for swapping two locations in a Chromosome to change the tour.
     * 
     * @param array the array of locations to do the swap in
     * @param i     the index of the first location
     * @param j     the index of the second location
     */
    private static void swap(Location[] array, int i, int j) {
        Location temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

}
