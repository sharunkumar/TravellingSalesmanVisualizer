package edu.neu.utilties.abstractions;

import edu.neu.display.TravellingSalesmanWindow;
import edu.neu.modals.Location;

public interface IAlgorithmStep {
    int[] run(Location[] locations, double[][] weightMatrix, int[] route, TravellingSalesmanWindow window);
}
