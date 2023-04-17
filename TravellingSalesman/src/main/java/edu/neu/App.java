package edu.neu;

import edu.neu.display.TspSolverUI;
import edu.neu.io.DataSet;

import java.io.IOException;

import static edu.neu.utilties.TSPUtilities.getWeightMatrix;

public class App {
    public static void main(String[] args) throws IOException {
        var set = DataSet.DefaultDataSet();

        var locations = set.getNormalizedLocations(1000);
        var matrix = getWeightMatrix(locations);

        TspSolverUI tspSolverUI = new TspSolverUI(locations, matrix);
        tspSolverUI.run();
    }
}
