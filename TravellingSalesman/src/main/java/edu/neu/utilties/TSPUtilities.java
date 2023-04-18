package edu.neu.utilties;

import edu.neu.modals.Location;
import org.apache.lucene.util.SloppyMath;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class TSPUtilities {
    public static void trySleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static double calculateDistance(int[] route, double[][] distMatrix) {
        double distance = 0;
        for (int i = 0; i < route.length - 1; i++) {
            distance += distMatrix[route[i]][route[i + 1]];
        }
        distance += distMatrix[route[route.length - 1]][route[0]];  // return to the starting point
        return distance;
    }

    public static double[][] getWeightMatrix(Location[] locations) {
        double[][] weightMatrix = new double[locations.length][locations.length];
        for (int i = 0; i < locations.length; i++) {
            for (int j = 0; j < locations.length; j++) {
                weightMatrix[i][j] = trueDistance(locations[i], locations[j]);
            }
        }
        return weightMatrix;
    }

    public static double trueDistance(Location location1, Location location2) {
        double lat1_rad = location1.getLatitude();
        double lon1_rad = location1.getLongitude();
        double lat2_rad = location2.getLatitude();
        double lon2_rad = location2.getLongitude();

        return SloppyMath.haversinMeters(lat1_rad, lon1_rad, lat2_rad, lon2_rad);

    }

    public static double[][] readDistanceMatrix(final String INPUT_FILE) throws IOException {
        double[][] matrix;
        BufferedReader br = new BufferedReader(new FileReader(INPUT_FILE));

        StringBuilder build = new StringBuilder();
        // Find out how many cities there are in the file
        int numCities = 0;
        while (!build.append(br.readLine()).toString().equalsIgnoreCase("null")) {
            numCities++;
            build.setLength(0); // Clears the buffer
        }
        matrix = new double[numCities][numCities];
        // Reset reader to the start of the file
        br = new BufferedReader(new FileReader(INPUT_FILE));
        // Populate the distance matrix
        int currentCity = 0;
        build = new StringBuilder();
        while (!build.append(br.readLine()).toString().equalsIgnoreCase("null")) {
            String[] tokens = build.toString().split(" ");
            for (int i = 0; i < numCities; i++) {
                matrix[currentCity][i] = Integer.parseInt(tokens[i]);
            }
            currentCity++;
            build.setLength(0); // Clears the buffer
        }
        return matrix;
    }
}
