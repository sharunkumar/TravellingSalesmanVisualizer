package edu.neu;

import edu.neu.display.HeadlessTspWindow;
import edu.neu.display.TravellingSalesmanWindow;
import edu.neu.io.DataSet;
import edu.neu.modals.Location;
import edu.neu.utilties.algorithm.AntColony;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;

import static edu.neu.utilties.TSPUtilities.getWeightMatrix;

/**
 * Unit test for simple App.
 */
public class AlgorithmStepsTest
        extends TestCase {
    private static final AntColony antColony = new AntColony();
    private final TravellingSalesmanWindow window;
    private final DataSet dataSet;
    private final Location[] locations;
    private double[][] weightMatrix;

    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AlgorithmStepsTest(String testName) throws IOException {
        super(testName);
        dataSet = DataSet.DefaultDataSet();
        locations = dataSet.getLocations();
        var normalizedLocations = dataSet.getNormalizedLocations(500);
        weightMatrix = getWeightMatrix(locations);
        window = new HeadlessTspWindow();
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite() {
        return new TestSuite(AlgorithmStepsTest.class);
    }


    public void testInputData() {
        assertEquals(585, locations.length);
    }

    public void testAntColony() {
        var route = antColony.run(locations, weightMatrix, new int[0], window);
        var set = new HashSet<Integer>();
        for (int i : route) {
            set.add(i);
        }
        assertEquals(locations.length, route.length);
        assertEquals(locations.length, set.size());
        Arrays.sort(route);
        for (int i = 0; i < route.length; i++) {
            assertEquals(i, route[i]);
        }
    }
}
