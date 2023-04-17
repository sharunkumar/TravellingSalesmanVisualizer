package edu.neu.utilties.algorithm.io;

import edu.neu.display.TravellingSalesmanWindow;
import edu.neu.modals.Location;

public class WinTitle extends PrintRoute {
    public WinTitle(String title) {
        super(title);
    }

    @Override
    public int[] run(Location[] locations, double[][] weightMatrix, int[] route, TravellingSalesmanWindow window) {
        window.setTitle(title);
        return route;
    }
}
