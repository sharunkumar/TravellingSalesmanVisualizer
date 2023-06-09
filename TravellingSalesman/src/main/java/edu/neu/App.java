package edu.neu;

import edu.neu.data.DataSet;

import java.io.IOException;

import static edu.neu.utilties.TSPUtilities.getWeightMatrix;

public class App {
    public static void main(String[] args) throws IOException {
        var set = DataSet.DefaultDataSet();

        var locations = set.getLocations();
        var matrix = getWeightMatrix(locations);

        TspSolverUI tspSolverUI = new TspSolverUI(set.getNormalizedLocations(500), matrix);
        tspSolverUI.run();
    }
}
