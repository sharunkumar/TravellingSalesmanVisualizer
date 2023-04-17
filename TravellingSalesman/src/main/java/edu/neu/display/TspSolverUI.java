package edu.neu.display;

import edu.neu.modals.Location;
import edu.neu.utilties.abstractions.IAlgorithmStep;
import edu.neu.utilties.algorithm.*;
import edu.neu.utilties.algorithm.io.*;

public class TspSolverUI {
    private final Location[] locations;
    private final double[][] weightMatrix;
    private final TravellingSalesmanWindow window;

    public TspSolverUI(Location[] locations, double[][] weightMatrix) {
        this.locations = locations;
        this.weightMatrix = weightMatrix;
        this.window = new TravellingSalesmanWindow(locations);
    }

    private void print(String windowTitle, String debugText) {
        window.setTitle(windowTitle);
        System.out.println(debugText);
    }

    public void run() {

        var christofides_flow = new IAlgorithmStep[]{
                new GenerateMst(),
                new PrintRoute("Minimum Spanning Tree"),
                new GenerateEulerCircuit(),
                new PrintPath("Euler Circuit"),
                new TwoOpt(),
                new PrintPath("Two Opt"),
                new RandomOptimization(),
                new PrintPath("Random Optimization"),
                new PrintPath("Final Path"),
                new WinTitle("TSP Complete!"),
                new PrintLocations("Final Locations")
        };

        var ant_colony_flow = new IAlgorithmStep[]{
                new AntColony(),
                new PrintPath("Ant Colony"),
        };

        var simulated_annealing_flow = new IAlgorithmStep[]{
                new SimulatedAnnealingStep(10, 0.9),
                new PrintPath("Simulated Annealing"),
                new TwoOpt(),
                new PrintPath("Two Opt"),
        };

        runFlow(simulated_annealing_flow);
    }

    private void runFlow(IAlgorithmStep[] christofides_flow) {
        var route = new int[locations.length];

        //noinspection ForLoopReplaceableByForEach
        for (int i = 0; i < christofides_flow.length; i++) {
            route = christofides_flow[i].run(locations, weightMatrix, route, window);
        }
    }
}
