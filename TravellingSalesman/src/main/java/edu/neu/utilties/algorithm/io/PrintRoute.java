package edu.neu.utilties.algorithm.io;

import edu.neu.display.TravellingSalesmanWindow;
import edu.neu.modals.Location;
import edu.neu.utilties.abstractions.IAlgorithmStep;

import java.util.Arrays;

public class PrintRoute implements IAlgorithmStep {
    protected final String title;

    public PrintRoute(String title) {
        this.title = title;
    }

    @Override
    public int[] run(Location[] locations, double[][] weightMatrix, int[] route, TravellingSalesmanWindow window) {
        System.out.println(title + ": " + Arrays.toString(route));
        return route;
    }
}
