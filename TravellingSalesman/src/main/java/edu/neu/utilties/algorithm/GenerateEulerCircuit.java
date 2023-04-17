package edu.neu.utilties.algorithm;

import edu.neu.christofides.EulerCircuitGenerator;
import edu.neu.display.TravellingSalesmanWindow;
import edu.neu.modals.Location;
import edu.neu.utilties.abstractions.IAlgorithmStep;

public class GenerateEulerCircuit implements IAlgorithmStep {
    @Override
    public int[] run(Location[] locations, double[][] weightMatrix, int[] route, TravellingSalesmanWindow window) {
        window.setTitle("Generating Euler Circuit. Please wait...");

        var path = EulerCircuitGenerator.generateEulerCircuit(locations, route, weightMatrix);
        window.setTitle("Euler Circuit Generated!");
        window.drawPath(path);
        return path.getRoute();
    }
}
