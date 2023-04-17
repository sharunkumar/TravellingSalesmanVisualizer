package edu.neu.display;

import edu.neu.modals.Location;
import edu.neu.utilties.abstractions.IAlgorithmStep;
import edu.neu.utilties.algorithm.GenerateEulerCircuit;
import edu.neu.utilties.algorithm.GenerateMst;
import edu.neu.utilties.algorithm.RandomOptimization;
import edu.neu.utilties.algorithm.TwoOpt;
import edu.neu.utilties.algorithm.io.PrintLocations;
import edu.neu.utilties.algorithm.io.PrintPath;
import edu.neu.utilties.algorithm.io.PrintRoute;
import edu.neu.utilties.algorithm.io.WinTitle;

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

        var steps = new IAlgorithmStep[]{
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

        var route = new int[locations.length];

        //noinspection ForLoopReplaceableByForEach
        for (int i = 0; i < steps.length; i++) {
            route = steps[i].run(locations, weightMatrix, route, window);
        }
    }
}
