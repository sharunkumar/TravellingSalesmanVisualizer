package edu.neu.modals;

import java.util.Random;

/**
 * Represents a location in the Traveling Salesman Problem.
 * Immutable.
 */
public class Location {
    private final double latitude;
    private final double longitude;
    private final String crimeID;
    private int hashCode = -1;

    /**
     * Constructs the location.
     *
     * @param latitude  the x coordinate
     * @param longitude the y coordinate
     * @param crimeID
     */
    public Location(double latitude, double longitude, String crimeID) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.crimeID = crimeID;
    }

    /**
     * Create a location with a random name and random location.
     *
     * @param random the Random object to be used for the generation
     * @return a Randomly generated location
     */
    public static Location getRandomLocation(Random random) {
        int x = random.nextInt(500);
        int y = random.nextInt(500);
        return new Location(x, y, "");
    }

    /**
     * Finds the Euclidean distance between two locations.
     *
     * @param location1 the first location
     * @param location2 the second location
     * @return the distance
     */
    public static double distance(Location location1, Location location2) {

        double x1 = location1.getLatitude();
        double y1 = location1.getLongitude();

        double x2 = location2.getLatitude();
        double y2 = location2.getLongitude();

        double xDiff = x2 - x1;
        double yDiff = y2 - y1;

        return Math.sqrt(xDiff * xDiff + yDiff * yDiff);
    }

    public String getCrimeID() {
        return crimeID;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public double distanceTo(Location otherLocation) {
        return distance(this, otherLocation);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        Location location = (Location) o;

        if (latitude != location.latitude)
            return false;
        if (longitude != location.longitude)
            return false;
        return toString().equals(location.toString());

    }

    @Override
    public int hashCode() {
        if (hashCode == -1) {
            hashCode = toString().hashCode();
        }
        return hashCode;
    }

    @Override
    public String toString() {
        return crimeID;
    }
}
