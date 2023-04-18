package edu.neu.utilties.algorithm;

import edu.neu.display.TravellingSalesmanWindow;
import edu.neu.modals.Location;
import edu.neu.modals.TravelPath;
import edu.neu.optimizations.strategic.AntColonyOptimization;
import edu.neu.utilties.abstractions.IAlgorithmStep;

public class AntColony implements IAlgorithmStep {
    private static final int NUM_ANTS = 80;
    private static final double ALPHA = 4.0;
    private static final double BETA = 5.0;
    private static final double EVAPORATION_RATE = 0.8;
    private static final double INITIAL_PHEROMONE_LEVEL = 1;

    @Override
    public int[] run(Location[] locations, double[][] weightMatrix, int[] route, TravellingSalesmanWindow window) {
        var aco = new AntColonyOptimization(weightMatrix, NUM_ANTS, ALPHA, BETA,
                EVAPORATION_RATE, INITIAL_PHEROMONE_LEVEL);

        var new_route = aco.run();

        window.drawPath(new TravelPath(locations, new_route, weightMatrix));
        return new_route;
    }
}
