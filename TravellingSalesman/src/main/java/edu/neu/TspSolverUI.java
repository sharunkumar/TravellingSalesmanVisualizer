package edu.neu;

import edu.neu.display.TravellingSalesmanWindow;
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
                new RandomOptimization(10000),
                new PrintPath("Random Optimization"),
                new PrintPath("Final Path"),
                new WinTitle("TSP Complete!"),
                new PrintLocations("Final Locations")
        };

        var ant_colony_flow = new IAlgorithmStep[]{
                new AntColony(80, 50, 50, 0.1, 1),
                new PrintPath("Ant Colony"),
        };

        var simulated_annealing_flow = new IAlgorithmStep[]{
                new SimulatedAnnealingStep(100, 0.9),
                new PrintPath("Simulated Annealing"),
                new TwoOpt(),
                new PrintPath("Two Opt"),
                new WinTitle("TSP Complete!"),
                new PrintLocations("Final Locations")
        };

        runFlow(christofides_flow);

    }

    private IAlgorithmStep[] getRandomFlow(int maxIterations) {
        return new IAlgorithmStep[]{
                new GenerateMst(),
                new PrintRoute("Minimum Spanning Tree"),
                new GenerateEulerCircuit(),
                new PrintPath("Euler Circuit"),
                new RandomOptimization(maxIterations),
                new PrintPath("Random Optimization"),
        };
    }

    private void runFlow(IAlgorithmStep[] algorithm_steps) {
        var route = new int[locations.length];

        //noinspection ForLoopReplaceableByForEach
        for (int i = 0; i < algorithm_steps.length; i++) {
            route = algorithm_steps[i].run(locations, weightMatrix, route, window);
        }
    }
}
