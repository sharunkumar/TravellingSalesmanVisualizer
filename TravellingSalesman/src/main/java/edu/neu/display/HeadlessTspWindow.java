package edu.neu.display;

import edu.neu.modals.Location;

public class HeadlessTspWindow extends TravellingSalesmanWindow {
    public HeadlessTspWindow() {
        super(new Location[0]);
        panel = new HeadlessPanel(this);
    }

    @Override
    protected void setWindowProperties() {
        // do nothing
    }
}
