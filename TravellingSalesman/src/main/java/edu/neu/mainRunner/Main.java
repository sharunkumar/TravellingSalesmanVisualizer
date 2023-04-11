package edu.neu.mainRunner;

import edu.neu.christofides.ChristofidesAlgorithm;
import edu.neu.christofides.Constants;
import edu.neu.tactical.optimizations.OptimizationHelperFunctions;
import edu.neu.tactical.optimizations.RandomOptimization;
import edu.neu.utility.ReadDistanceMatrix;

import java.io.IOException;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws IOException {

        var weightMatrix = ReadDistanceMatrix.readDistanceMatrix(Constants.DATA_SET_LOCATION);

        var route = ChristofidesAlgorithm.run(weightMatrix);

        int[] randomSwappingRoute = RandomOptimization.randomSwappingRoute(weightMatrix, route);

        System.out.println("Random Swapping Shortest path: " + Arrays.toString(randomSwappingRoute));
        System.out.println("Route Sum " + OptimizationHelperFunctions.calculateRouteSum(weightMatrix,
                randomSwappingRoute));

    }
}
