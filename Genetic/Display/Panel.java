package Display;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JPanel;

import GeneticObjects.Location;

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
        paintTravelingSalesman((Graphics2D) graphics);
    }

    private void paintTravelingSalesman(Graphics2D graphics) {
        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        setBackground(Color.black);
        paintLocationNames(graphics);
        if (this.window.path != null) {
            paintChromosome(graphics);
        }
        paintLocations(graphics);
    }

    private void paintChromosome(Graphics2D graphics) {

        graphics.setColor(Color.white);
        Location[] array = this.window.path.getArray();

        for (int i = 1; i < array.length; i++) {
            int x1 = (int) (array[i - 1].getX() / this.window.scaleX
                    + TravellingSalesmanWindow.OFFSET / 2);
            int y1 = (int) (array[i - 1].getY() / this.window.scaleY
                    + TravellingSalesmanWindow.OFFSET / 2);
            int x2 = (int) (array[i].getX() / this.window.scaleX
                    + TravellingSalesmanWindow.OFFSET / 2);
            int y2 = (int) (array[i].getY() / this.window.scaleY
                    + TravellingSalesmanWindow.OFFSET / 2);
            graphics.drawLine(x1, y1, x2, y2);
        }

        int x1 = (int) (array[0].getX() / this.window.scaleX + TravellingSalesmanWindow.OFFSET / 2);
        int y1 = (int) (array[0].getY() / this.window.scaleY + TravellingSalesmanWindow.OFFSET / 2);
        int x2 = (int) (array[array.length - 1].getX() / this.window.scaleX
                + TravellingSalesmanWindow.OFFSET / 2);
        int y2 = (int) (array[array.length - 1].getY() / this.window.scaleY
                + TravellingSalesmanWindow.OFFSET / 2);
        graphics.drawLine(x1, y1, x2, y2);

    }

    private void paintLocations(Graphics2D graphics) {
        graphics.setColor(Color.white);
        for (Location c : this.window.locations) {
            int x = (int) ((c.getX()) / this.window.scaleX
                    - TravellingSalesmanWindow.LOCATION_SIZE / 2 + TravellingSalesmanWindow.OFFSET / 2);
            int y = (int) ((c.getY()) / this.window.scaleY
                    - TravellingSalesmanWindow.LOCATION_SIZE / 2 + TravellingSalesmanWindow.OFFSET / 2);
            graphics.fillOval(x, y, TravellingSalesmanWindow.LOCATION_SIZE, TravellingSalesmanWindow.LOCATION_SIZE);
        }
    }

    private void paintLocationNames(Graphics2D graphics) {
        graphics.setColor(Color.white);
        for (Location c : this.window.locations) {
            int x = (int) ((c.getX()) / this.window.scaleX
                    - TravellingSalesmanWindow.LOCATION_SIZE / 2 + TravellingSalesmanWindow.OFFSET / 2);
            int y = (int) ((c.getY()) / this.window.scaleY
                    - TravellingSalesmanWindow.LOCATION_SIZE / 2 + TravellingSalesmanWindow.OFFSET / 2);
            graphics.fillOval(x, y, TravellingSalesmanWindow.LOCATION_SIZE, TravellingSalesmanWindow.LOCATION_SIZE);
            int fontOffset = getFontMetrics(graphics.getFont()).stringWidth(c.getName()) / 2 - 2;
            graphics.drawString(c.getName(), x - fontOffset, y - 3);
        }
    }
}