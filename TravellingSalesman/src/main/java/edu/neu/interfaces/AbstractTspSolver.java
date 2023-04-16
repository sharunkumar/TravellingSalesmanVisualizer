package edu.neu.interfaces;

import edu.neu.display.TravellingSalesmanWindow;
import edu.neu.modals.Location;
import edu.neu.modals.TravelPath;

public abstract class AbstractTspSolver {
    protected final Location[] locations;
    protected TravelPath path;
    protected TravellingSalesmanWindow window;

    public AbstractTspSolver(Location[] locations) {
        this.locations = locations;
        path = new TravelPath(locations);
        window = new TravellingSalesmanWindow(locations);
    }

    public abstract TravelPath getNextPath();

    public abstract boolean hasNextIteration();

    public abstract void afterSolving();

    public void solve() {
        do {
            var path = getNextPath();
            window.drawPath(path);
        } while (hasNextIteration());

        afterSolving();
    }
}
