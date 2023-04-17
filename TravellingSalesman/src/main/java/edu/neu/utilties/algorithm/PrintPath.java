package edu.neu.utilties.algorithm;

import edu.neu.display.TravellingSalesmanWindow;
import edu.neu.modals.Location;
import edu.neu.modals.TravelPath;

public class PrintPath extends PrintRoute {

    public PrintPath(String title) {
        super(title);
    }

    @Override
    public int[] run(Location[] locations, double[][] weightMatrix, int[] route, TravellingSalesmanWindow window) {
        var path = new TravelPath(locations, route);
        System.out.println(title + ": " + path);
        return route;
    }
}
