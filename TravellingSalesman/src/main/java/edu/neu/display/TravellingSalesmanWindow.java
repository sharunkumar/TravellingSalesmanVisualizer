package edu.neu.display;

import edu.neu.modals.Location;
import edu.neu.modals.TravelPath;

import javax.swing.*;
import java.awt.*;

/**
 * Draws the cities to the screen, as well as a path.
 */
public class TravellingSalesmanWindow extends JFrame {

    static final int OFFSET = 40;
    static final int LOCATION_SIZE = 6;
    private static final int WIDTH = 1600;
    private static final int HEIGHT = 1600 / 16 * 9;
    Location[] locations;
    TravelPath path;
    double scaleX;
    double scaleY;
    private Panel panel;
    private int maxX, maxY;

    /**
     * Construct the WindowTSP and draw the cities to the screen.
     *
     * @param cities the cities to draw to the screen
     */
    public TravellingSalesmanWindow(Location[] cities) {
        this.locations = cities;
        setScale();
        panel = createPanel();
        setWindowProperties();
    }

    /**
     * Draw a path through the city.
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
     * Sets the scale for the drawing so that all the cities
     * are drawn inside the window.
     */
    private void setScale() {
        for (Location c : locations) {
            if (c.getX() > maxX) {
                maxX = c.getX();
            }
            if (c.getY() > maxY) {
                maxY = c.getY();
            }
        }
        scaleX = ((double) maxX) / ((double) WIDTH - OFFSET);
        scaleY = ((double) maxY) / ((double) HEIGHT - OFFSET);
    }

}
