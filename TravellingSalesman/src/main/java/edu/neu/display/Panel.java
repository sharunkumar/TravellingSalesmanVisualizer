package edu.neu.display;

import edu.neu.modals.Location;

import javax.swing.*;
import java.awt.*;

/**
 * All the drawing is done here.
 */
class Panel extends JPanel {

    /**
     *
     */
    private final TravellingSalesmanWindow window;

    /**
     * @param travellingSalesmanWindow parent window of TSP panel
     */
    Panel(TravellingSalesmanWindow travellingSalesmanWindow) {
        this.window = travellingSalesmanWindow;
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        paintTravelingSalesman((Graphics2D) graphics, false);
    }

    private void paintTravelingSalesman(Graphics2D graphics, boolean withNames) {
        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        setBackground(Color.black);
        if (withNames) {
            paintLocationNames(graphics);
        }
        if (this.window.path != null) {
            paintChromosome(graphics);
        }
        paintLocations(graphics);
    }

    private void paintChromosome(Graphics2D graphics) {

        graphics.setColor(Color.white);
        Location[] array = this.window.path.getArray();

        for (int i = 1; i < array.length; i++) {
            int x1 = (int) (array[i - 1].getLatitude() / this.window.scaleX
                    + TravellingSalesmanWindow.OFFSET / 2);
            int y1 = (int) (array[i - 1].getLongitude() / this.window.scaleY
                    + TravellingSalesmanWindow.OFFSET / 2);
            int x2 = (int) (array[i].getLatitude() / this.window.scaleX
                    + TravellingSalesmanWindow.OFFSET / 2);
            int y2 = (int) (array[i].getLongitude() / this.window.scaleY
                    + TravellingSalesmanWindow.OFFSET / 2);
            graphics.drawLine(x1, y1, x2, y2);
        }

        int x1 = (int) (array[0].getLatitude() / this.window.scaleX + TravellingSalesmanWindow.OFFSET / 2);
        int y1 = (int) (array[0].getLongitude() / this.window.scaleY + TravellingSalesmanWindow.OFFSET / 2);
        int x2 = (int) (array[array.length - 1].getLatitude() / this.window.scaleX
                + TravellingSalesmanWindow.OFFSET / 2);
        int y2 = (int) (array[array.length - 1].getLongitude() / this.window.scaleY
                + TravellingSalesmanWindow.OFFSET / 2);
        graphics.drawLine(x1, y1, x2, y2);

    }

    private void paintLocations(Graphics2D graphics) {
        graphics.setColor(Color.white);
        for (Location c : this.window.locations) {
            int x = (int) ((c.getLatitude()) / this.window.scaleX
                    - TravellingSalesmanWindow.LOCATION_SIZE / 2 + TravellingSalesmanWindow.OFFSET / 2);
            int y = (int) ((c.getLongitude()) / this.window.scaleY
                    - TravellingSalesmanWindow.LOCATION_SIZE / 2 + TravellingSalesmanWindow.OFFSET / 2);
            graphics.fillOval(x, y, TravellingSalesmanWindow.LOCATION_SIZE, TravellingSalesmanWindow.LOCATION_SIZE);
        }
    }

    private void paintLocationNames(Graphics2D graphics) {
        graphics.setColor(Color.white);
        for (Location c : this.window.locations) {
            int x = (int) ((c.getLatitude()) / this.window.scaleX
                    - TravellingSalesmanWindow.LOCATION_SIZE / 2 + TravellingSalesmanWindow.OFFSET / 2);
            int y = (int) ((c.getLongitude()) / this.window.scaleY
                    - TravellingSalesmanWindow.LOCATION_SIZE / 2 + TravellingSalesmanWindow.OFFSET / 2);
            graphics.fillOval(x, y, TravellingSalesmanWindow.LOCATION_SIZE, TravellingSalesmanWindow.LOCATION_SIZE);
            int fontOffset = getFontMetrics(graphics.getFont()).stringWidth(c.toString()) / 2 - 2;
            graphics.drawString(c.toString(), x - fontOffset, y - 3);
        }
    }
}