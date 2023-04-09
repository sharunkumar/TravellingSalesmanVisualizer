package GeneticObjects;

import java.util.Random;

/**
 * Represents a location in the Traveling Salesman Problem.
 * Immutable.
 */
public class Location {

    private String name;
    private int x, y;

    /**
     * Constructs the location.
     * 
     * @param name the name of the location
     * @param x    the x coordinate
     * @param y    the y coordinate
     */
    public Location(String name, int x, int y) {
        this.name = name;
        this.x = x;
        this.y = y;
    }

    public String getName() {
        return name;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    /**
     * Create a location with a random name and random location.
     * 
     * @param random the Random object to be used for the generation
     * @return a Randomly generated location
     */
    public static Location getRandomLocation(Random random) {
        String name = getRandomName(random);
        int x = random.nextInt(500);
        int y = random.nextInt(500);
        return new Location(name, x, y);
    }

    /**
     * Helper method to generate a random name for the random location generator.
     * 
     * @param random the Random object to be used for the generation
     * @return random letters
     */
    private static String getRandomName(Random random) {

        // Create an array with random integers.
        int[] name = new int[random.nextInt(5) + 3];
        for (int i = 0; i < name.length; i++) {
            name[i] = random.nextInt(26) + 65;
        }

        // Convert the integers in the array to chars.
        // Add each char to StringBuilder.
        StringBuilder sb = new StringBuilder();
        for (int i : name) {
            sb.append((char) i);
        }

        return new String(sb);
    }

    /**
     * Finds the Euclidean distance between two locations.
     * 
     * @param location1 the first location
     * @param location2 the second location
     * @return the distance
     */
    public static double distance(Location location1, Location location2) {

        int x1 = location1.getX();
        int y1 = location1.getY();

        int x2 = location2.getX();
        int y2 = location2.getY();

        int xDiff = x2 - x1;
        int yDiff = y2 - y1;

        return Math.sqrt(xDiff * xDiff + yDiff * yDiff);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        Location location = (Location) o;

        if (x != location.x)
            return false;
        if (y != location.y)
            return false;
        return name.equals(location.name);

    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + x;
        result = 31 * result + y;
        return result;
    }

    @Override
    public String toString() {
        return name + " (" + x + ", " + y + ")";
    }
}
