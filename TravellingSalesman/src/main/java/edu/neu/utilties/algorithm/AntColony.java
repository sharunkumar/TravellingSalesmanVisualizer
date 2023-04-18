package edu.neu.utilties.algorithm;

import edu.neu.display.TravellingSalesmanWindow;
import edu.neu.modals.Location;
import edu.neu.modals.TravelPath;
import edu.neu.optimizations.strategic.AntColonyOptimization;
import edu.neu.utilties.abstractions.IAlgorithmStep;

public class AntColony implements IAlgorithmStep {
    private final int NUM_ANTS;
    private final double ALPHA;
    private final double BETA;
    private final double EVAPORATION_RATE;
    private final double INITIAL_PHEROMONE_LEVEL;

    public AntColony(int numAnts, double alpha, double beta, double evaporationRate, double initialPheromoneLevel) {
        NUM_ANTS = numAnts;
        ALPHA = alpha;
        BETA = beta;
        EVAPORATION_RATE = evaporationRate;
        INITIAL_PHEROMONE_LEVEL = initialPheromoneLevel;
    }

    @Override
    public int[] run(Location[] locations, double[][] weightMatrix, int[] route, TravellingSalesmanWindow window) {
        var aco = new AntColonyOptimization(weightMatrix, NUM_ANTS, ALPHA, BETA,
                EVAPORATION_RATE, INITIAL_PHEROMONE_LEVEL);

        var new_route = aco.run();

        window.drawPath(new TravelPath(locations, new_route, weightMatrix));
        return new_route;
    }
}
