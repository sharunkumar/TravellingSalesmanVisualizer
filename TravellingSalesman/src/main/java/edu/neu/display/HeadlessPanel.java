package edu.neu.display;

import java.awt.*;

public class HeadlessPanel extends Panel {
    /**
     * @param travellingSalesmanWindow parent window of TSP panel
     */
    HeadlessPanel(TravellingSalesmanWindow travellingSalesmanWindow) {
        super(travellingSalesmanWindow);
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        // do nothing
    }
}
