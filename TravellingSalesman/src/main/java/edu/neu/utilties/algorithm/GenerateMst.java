package edu.neu.utilties.algorithm;

import edu.neu.christofides.PrimsAlgorithm;
import edu.neu.display.TravellingSalesmanWindow;
import edu.neu.modals.Location;
import edu.neu.utilties.abstractions.IAlgorithmStep;

import static edu.neu.display.enums.DRAW_MODE.MST;

public class GenerateMst implements IAlgorithmStep {
    @Override
    public int[] run(Location[] locations, double[][] weightMatrix, int[] route, TravellingSalesmanWindow window) {
        window.setTitle("Generating Minimum Spanning Tree. Please wait...");
        var minimumSpanningTree = PrimsAlgorithm.run(weightMatrix);
        window.setMinimumSpanningTree(minimumSpanningTree);
        window.setDrawMode(MST);
        window.repaint();
        window.setTitle("Minimum Spanning Tree Generated!");
        return minimumSpanningTree;
    }
}
