package edu.neu.utilties.algorithm;

import edu.neu.display.TravellingSalesmanWindow;
import edu.neu.modals.Location;
import edu.neu.optimizations.strategic.SimulatedAnnealing;
import edu.neu.utilties.abstractions.IAlgorithmStep;

public class SimulatedAnnealingStep implements IAlgorithmStep {
    private final double temperature;
    private final double coolingRate;

    public SimulatedAnnealingStep(double temperature, double coolingRate) {
        this.temperature = temperature;
        this.coolingRate = coolingRate;
    }

    @Override
    public int[] run(Location[] locations, double[][] weightMatrix, int[] route, TravellingSalesmanWindow window) {
        var sa = new SimulatedAnnealing();
        var new_route = sa.run(weightMatrix, temperature, coolingRate);
        return new_route;
    }
}
