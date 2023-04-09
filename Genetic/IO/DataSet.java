package io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import modals.Location;

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

    public Location[] getNormalizedLocations(int normalizationFactor) {
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
            int normalizedLat = (int) (((lat - minLat) / latRange) * normalizationFactor);
            int normalizedLng = (int) (((lng - minLng) / lngRange) * normalizationFactor);

            result.add(new Location(normalizedLat, normalizedLng));
        }

        return result.toArray(new Location[0]);
    }

    public Location[] getGroupedLocations(int normalizationFactor, double distanceThreshold) {
        List<List<Location>> groups = new ArrayList<>();
        for (Location location : getNormalizedLocations(normalizationFactor)) {
            boolean added = false;
            for (List<Location> group : groups) {
                if (group.stream().anyMatch(l -> l.distanceTo(location) <= distanceThreshold)) {
                    group.add(location);
                    added = true;
                    break;
                }
            }
            if (!added) {
                List<Location> newGroup = new ArrayList<>();
                newGroup.add(location);
                groups.add(newGroup);
            }
        }
        Location[] midpoints = new Location[groups.size()];
        for (int i = 0; i < groups.size(); i++) {
            Location midpoint = groups.get(i).get(0);
            midpoints[i] = midpoint;
        }
        return midpoints;
    }
}