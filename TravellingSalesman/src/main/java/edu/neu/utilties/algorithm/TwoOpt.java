package edu.neu.utilties.algorithm;

import edu.neu.display.TravellingSalesmanWindow;
import edu.neu.modals.Location;
import edu.neu.modals.TravelPath;
import edu.neu.optimizations.tactical.TwoOptOptimization;
import edu.neu.utilties.abstractions.IAlgorithmStep;

public class TwoOpt implements IAlgorithmStep {
    @Override
    public int[] run(Location[] locations, double[][] weightMatrix, int[] route, TravellingSalesmanWindow window) {
        window.setTitle("Running Two Opt Optimization");

        var twoOptRoute = TwoOptOptimization.twoOptOptimization(route, weightMatrix, window, locations);

        var path = new TravelPath(locations, twoOptRoute, weightMatrix);
        window.setTitle("Two Opt Optimization Complete!");

        window.setTitle("Final Path: " + path.getRoute());

        return path.getRoute();
    }
}
