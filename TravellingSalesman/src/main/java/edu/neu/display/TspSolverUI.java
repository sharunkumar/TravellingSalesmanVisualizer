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
        int sleep_duration = 0;

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
                new PrintLocations("Final Locations")
        };

        var route = new int[locations.length];

        for (var step : steps) {
            route = step.run(locations, weightMatrix, route, window);
        }
//
//
//
//        window.setTitle("Generating Minimum Spanning Tree. Please wait...");
//        int[] minimumSpanningTree = PrimsAlgorithm.run(weightMatrix);
//        print("Minimum Spanning Tree Generated!",
//                "Minimum Spanning Tree: " + minimumSpanningTree);
//        window.setMinimumSpanningTree(minimumSpanningTree);
//        window.setDrawMode(MST);
//        window.repaint();
//        window.setTitle("Minimum Spanning Tree");
//        trySleep(sleep_duration);
//        window.setTitle("Generating Euler Circuit. Please wait...");
//
//        var path = EulerCircuitGenerator.generateEulerCircuit(locations, minimumSpanningTree, weightMatrix);
//        print("Euler Circuit Generated!", "Euler Circuit: " + path);
//        window.drawPath(path);
//        trySleep(sleep_duration);
//
//        window.setTitle("Generating Random Optimization. Please wait...");
//
//        var optimizer = new RandomOptimizerBruteForce(locations, weightMatrix, path.getRoute(), new Random(69420),
//                100000);
//
//        while (optimizer.hasNext()) {
//            var prev_path = path;
//            path = optimizer.next();
//            var new_path = path;
//            if (prev_path.hashCode() != new_path.hashCode()) {
//                window.drawPath(path);
//                print("Random Optimization Path: " + path.getRoute(), "Random " +
//                        "Optimization Path: " + path);
//            }
//        }
//
//        print("Running Two Opt Optimization", "Running Two Opt Optimization");
//
//        var twoOptRoute = TwoOptOptimization.twoOptOptimization(path.getRoute(), weightMatrix, window, locations);
//
//        path = new TravelPath(locations, twoOptRoute);
//        print("Two Opt Optimization Complete!", "Two Opt Optimization Complete:" + path);
//
//        print("Final Path: " + path.getRoute(), "Final Path: " + path);

    }
}
