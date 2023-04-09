package modals;

import java.util.Random;

/**
 * Represents a location in the Traveling Salesman Problem.
 * Immutable.
 */
public class Location {

    private int hashCode = -1;
    private float latitude, longitude;

    /**
     * Constructs the location.
     * 
     * @param name      the name of the location
     * @param latitude  the x coordinate
     * @param longitude the y coordinate
     */
    public Location(float latitude, float longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public float getLatitude() {
        return latitude;
    }

    public float getLongitude() {
        return longitude;
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
        return new Location(x, y);
    }

    /**
     * Finds the Euclidean distance between two locations.
     * 
     * @param location1 the first location
     * @param location2 the second location
     * @return the distance
     */
    public static double distance(Location location1, Location location2) {

        float x1 = location1.getLatitude();
        float y1 = location1.getLongitude();

        float x2 = location2.getLatitude();
        float y2 = location2.getLongitude();

        float xDiff = x2 - x1;
        float yDiff = y2 - y1;

        return Math.sqrt(xDiff * xDiff + yDiff * yDiff);
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
        return "(" + latitude + ", " + longitude + ")";
    }
}
