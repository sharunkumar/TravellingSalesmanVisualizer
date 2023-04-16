package edu.neu.christofides;

import edu.neu.display.TravellingSalesmanWindow;
import edu.neu.modals.Location;

import static edu.neu.display.DRAW_MODE.MST;

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
        int[] minimumSpanningTree = PrimsAlgorithm.run(weightMatrix);
        window.setMinimumSpanningTree(minimumSpanningTree);
        window.setDrawMode(MST);
        window.repaint();
        window.setTitle("Minimum Spanning Tree");
    }
}
