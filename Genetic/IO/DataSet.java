package IO;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import GeneticObjects.Location;

public class DataSet {

    public Set<Location> locations = new HashSet<Location>();

    public DataSet(String fileName) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            br.readLine(); // skip the header
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                try {
                    float latitude = Float.parseFloat(data[5]);
                    float longitude = Float.parseFloat(data[4]);

                    // int normalizedLatitude = (int) ((latitude + 90) * 100);
                    // int normalizedLongitude = (int) ((longitude + 180) * 100);

                    locations.add(new Location(latitude, longitude));
                } catch (Exception e) {
                    // e.printStackTrace();
                }
            }
        }
    }

    public Location[] getLocations() {
        return locations.toArray(new Location[0]);
    }

    public Location[] getNormalizedLocations() {
        Set<Location> result = new HashSet<Location>();
        float minLat, maxLat, minLng, maxLng;
        minLat = Float.MAX_VALUE;
        maxLat = Float.MIN_VALUE;
        minLng = Float.MAX_VALUE;
        maxLng = Float.MIN_VALUE;

        for (Location location : locations) {
            float lat = location.getLatitude();
            float lng = location.getLongitude();
            if (lat < minLat)
                minLat = lat;
            if (lat > maxLat)
                maxLat = lat;
            if (lng < minLng)
                minLng = lng;
            if (lng > maxLng)
                maxLng = lng;
        }

        float latRange = maxLat - minLat;
        float lngRange = maxLng - minLng;

        for (Location location : locations) {
            float lat = location.getLatitude();
            float lng = location.getLongitude();
            int normalizedLat = (int) (((lat - minLat) / latRange) * 50);
            int normalizedLng = (int) (((lng - minLng) / lngRange) * 50);

            result.add(new Location(normalizedLat, normalizedLng));
        }

        return result.toArray(new Location[0]);
    }
}