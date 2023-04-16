package edu.neu.christofides;

import edu.neu.display.TravellingSalesmanWindow;
import edu.neu.modals.Location;
import edu.neu.optimizations.tactical.RandomOptimizerBruteForce;

import java.util.Random;

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

    private void print(String windowTitle, String debugText) {
        window.setTitle(windowTitle);
        System.out.println(debugText);
    }

    public void run() {
        int sleep_duration = 0;
        window.setTitle("Generating Minimum Spanning Tree. Please wait...");
        int[] minimumSpanningTree = PrimsAlgorithm.run(weightMatrix);
        print("Minimum Spanning Tree Generated!",
                "Minimum Spanning Tree: " + minimumSpanningTree);
        window.setMinimumSpanningTree(minimumSpanningTree);
        window.setDrawMode(MST);
        window.repaint();
        window.setTitle("Minimum Spanning Tree");
        trySleep(sleep_duration);
        window.setTitle("Generating Euler Circuit. Please wait...");

        var path = EulerCircuitGenerator.generateEulerCircuit(locations, minimumSpanningTree, weightMatrix);
        print("Euler Circuit Generated!", "Euler Circuit: " + path);
        window.drawPath(path);
        trySleep(sleep_duration);

        window.setTitle("Generating Random Optimization. Please wait...");

        var optimizer = new RandomOptimizerBruteForce(locations, weightMatrix, path.getRoute(), new Random(69420),
                100000);

        while (optimizer.hasNext()) {
            var prev_path = path;
            path = optimizer.next();
            var new_path = path;
            if (prev_path.hashCode() != new_path.hashCode()) {
                window.drawPath(path);
                print("Random Optimization Path: " + path.getRoute(), "Random " +
                        "Optimization Path: " + path);
            }
        }

        print("Final Path: " + path.getRoute(), "Final Path: " + path);

    }
}
