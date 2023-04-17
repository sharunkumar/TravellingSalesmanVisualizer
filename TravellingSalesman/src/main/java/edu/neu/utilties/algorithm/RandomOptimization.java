package edu.neu.utilties.algorithm;

import edu.neu.display.TravellingSalesmanWindow;
import edu.neu.modals.Location;
import edu.neu.modals.TravelPath;
import edu.neu.optimizations.tactical.RandomOptimizerBruteForce;
import edu.neu.utilties.abstractions.IAlgorithmStep;

import java.util.Random;

public class RandomOptimization implements IAlgorithmStep {
    @Override
    public int[] run(Location[] locations, double[][] weightMatrix, int[] route, TravellingSalesmanWindow window) {
        window.setTitle("Generating Random Optimization. Please wait...");

        var path = new TravelPath(locations, route, weightMatrix);

        var optimizer = new RandomOptimizerBruteForce(locations, weightMatrix, path.getRoute(), new Random(69420),
                100000);

        while (optimizer.hasNext()) {
            var prev_path = path;
            path = optimizer.next();
            var new_path = path;
            if (prev_path.hashCode() != new_path.hashCode()) {
                window.drawPath(path);
                window.setTitle("Current Random Optimization Path: " + path.getRoute());
            }
        }
        window.setTitle("Random Optimization Path Generated!");

        return path.getRoute();
    }
}
