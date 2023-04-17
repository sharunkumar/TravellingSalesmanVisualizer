package edu.neu.utilties.algorithm.io;

import edu.neu.display.TravellingSalesmanWindow;
import edu.neu.modals.Location;

public class PrintLocations extends PrintRoute {
    public PrintLocations(String title) {
        super(title);
    }

    @Override
    public int[] run(Location[] locations, double[][] weightMatrix, int[] route, TravellingSalesmanWindow window) {
        System.out.println(title);
        //noinspection ForLoopReplaceableByForEach
        for (int i = 0; i < locations.length; i++) {
            System.out.println(locations[i].getCrimeID());
        }
        return route;
    }
}
