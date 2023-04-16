package edu.neu.display;

import edu.neu.modals.Location;
import edu.neu.modals.TravelPath;

import javax.swing.*;
import java.awt.*;

/**
 * Draws the locations to the screen, as well as a path.
 */
public class TravellingSalesmanWindow extends JFrame {
    static final int OFFSET = 40;
    static final int LOCATION_SIZE = 6;
    private static final int WIDTH = 1600;
    private static final int HEIGHT = 1600 / 16 * 9;
    private final Panel panel;
    protected DRAW_MODE drawMode = DRAW_MODE.PATHS;
    protected int[] minimumSpanningTree = null;
    Location[] locations;
    TravelPath path;
    double scaleX;
    double scaleY;
    private float maxX, maxY;

    /**
     * Construct the WindowTSP and draw the locations to the screen.
     *
     * @param locations the locations to draw to the screen
     */
    public TravellingSalesmanWindow(Location[] locations) {
        this.locations = locations;
        setScale();
        panel = createPanel();
        setWindowProperties();
    }

    public DRAW_MODE getDrawMode() {
        return drawMode;
    }

    public void setDrawMode(DRAW_MODE drawMode) {
        this.drawMode = drawMode;
    }

    public int[] getMinimumSpanningTree() {
        return minimumSpanningTree;
    }

    public void setMinimumSpanningTree(int[] minimumSpanningTree) {
        this.minimumSpanningTree = minimumSpanningTree;
    }

    /**
     * Draw a path through the location.
     *
     * @param path the Travel Path to draw
     */
    public void draw(TravelPath path) {
        this.path = path;
        panel.repaint();
    }

    private Panel createPanel() {
        Panel panel = new Panel(this);
        Container cp = getContentPane();
        cp.add(panel);
        panel.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        return panel;
    }

    private void setWindowProperties() {
        int sWidth = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth() / 2;
        int sHeight = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight() / 2;
        int x = sWidth - (WIDTH / 2);
        int y = sHeight - (HEIGHT / 2);
        setLocation(x, y);
        setResizable(false);
        pack();
        setTitle("Traveling Salesman Problem");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        toFront();
    }

    /**
     * Sets the scale for the drawing so that all the locations
     * are drawn inside the window.
     */
    private void setScale() {
        for (Location c : locations) {
            if (c.getLatitude() > maxX) {
                maxX = c.getLatitude();
            }
            if (c.getLongitude() > maxY) {
                maxY = c.getLongitude();
            }
        }
        scaleX = ((double) maxX) / ((double) WIDTH - OFFSET);
        scaleY = ((double) maxY) / ((double) HEIGHT - OFFSET);
    }

}

