package edu.neu.christofides;

import edu.neu.display.TravellingSalesmanWindow;
import edu.neu.modals.Location;
import edu.neu.optimizations.tactical.RandomOptimization;

import static edu.neu.display.DRAW_MODE.MST;
import static edu.neu.utilties.TSPUtilities.trySleep;

public class ChristofidesUi {
    private final Location[] locations;
    private final double[][] weightMatrix;
    private final TravellingSalesmanWindow window;

    public ChristofidesUi(Location[] locations, double[][] weightMatrix) {
        this.locations = locations;
        this.weightMatrix = weightMatrix;
        this.window = new TravellingSalesmanWindow(locations);
    }

    public void run() {
        int sleep_duration = 3000;
        window.setTitle("Generating Minimum Spanning Tree. Please wait...");
        int[] minimumSpanningTree = PrimsAlgorithm.run(weightMatrix);
        window.setMinimumSpanningTree(minimumSpanningTree);
        window.setDrawMode(MST);
        window.repaint();
        window.setTitle("Minimum Spanning Tree");
        trySleep(sleep_duration);
        window.setTitle("Generating Euler Circuit. Please wait...");

        var path = EulerCircuitGenerator.generateEulerCircuit(locations, minimumSpanningTree, weightMatrix);
        window.drawPath(path);
        window.setTitle("Euler Circuit Generated!");
        trySleep(sleep_duration);

        window.setTitle("Generating Random Optimization. Please wait...");
        path = RandomOptimization.randomSwappingRoute(locations, weightMatrix, path.getRoute());
        window.drawPath(path);
        window.setTitle("Random Optimization Generated!");

    }
}
