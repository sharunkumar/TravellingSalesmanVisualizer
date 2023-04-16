package edu.neu.recursion.solvers;

import edu.neu.interfaces.AbstractTspSolver;
import edu.neu.io.DataSet;
import edu.neu.modals.Location;
import edu.neu.modals.TravelPath;

import java.io.IOException;

public class ExampleTspSolver extends AbstractTspSolver {
    public ExampleTspSolver(Location[] locations) {
        super(locations);
    }

    public static void main(String[] args) {
        try {
            var locations = DataSet.DefaultDataSet().getNormalizedLocations(500);
            var solver = new ExampleTspSolver(locations);
            solver.solve();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public TravelPath getNextPath() {
        return new TravelPath(locations);
    }

    @Override
    public boolean hasNextIteration() {
        return false;
    }

    @Override
    public void afterSolving() {
        System.out.println("Done");
    }
}
