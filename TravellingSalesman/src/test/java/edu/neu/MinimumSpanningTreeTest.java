package edu.neu;

import edu.neu.christofides.ChristofidesAlgorithm;
import edu.neu.christofides.Constants;
import edu.neu.christofides.PrimsAlgorithm;
import edu.neu.utility.ReadDistanceMatrix;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.Arrays;

import static edu.neu.utilties.TSPUtilities.calculateDistance;
import static org.junit.Assert.*;

public class MinimumSpanningTreeTest {


    @Test
    public void testMSTusingPrims() throws IOException {
        double[][] weightMatrix = ReadDistanceMatrix.readDistanceMatrix(Constants.DATA_SET_LOCATION_2);
        int[] route = PrimsAlgorithm.run(weightMatrix);
        assertSame(route.length, 9);
        boolean success = Arrays.equals(route, new int[]{0,0,5,2,3,6,7,0,2})
                || Arrays.equals(route, new int[]{0,0,1,2,3,2,5,6,2});
       assertTrue(success);
    }
}
