package edu.neu.mainRunner;

import edu.neu.christofides.*;
import edu.neu.graphs.node.GraphNode;
import edu.neu.optimizations.tactical.OptimizationHelperFunctions;
import edu.neu.optimizations.tactical.RandomOptimization;
import edu.neu.optimizations.tactical.TwoOptOptimization;
import edu.neu.utility.ReadDistanceMatrix;

import java.io.IOException;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws IOException {
        double[][] weightMatrix = ReadDistanceMatrix.readDistanceMatrix(Constants.DATA_SET_LOCATION_2);

        int[] minimumSpanningTree = PrimsAlgorithm.run(weightMatrix, weightMatrix[0].length);

        int[][] matchGraph = GreedyMatch.greadyMatch(minimumSpanningTree, weightMatrix, weightMatrix[0].length);

        GraphNode[] nodes = MultiGraph.build(matchGraph, minimumSpanningTree);
        int[] route = EulerCircuitGenerator.generateEulerCircuit(nodes);
        int[] routeCopy = Arrays.copyOf(route, route.length);

        double sum = 0;

        for (int i = 1; i < route.length; i++) {
            sum += weightMatrix[route[i - 1]][route[i]];
        }
        sum += weightMatrix[route[0]][route[route.length - 1]];

        System.out.println("Shortest Route = " + Arrays.toString(route));
        System.out.println("Shortest Route Total Sum : " + sum);

        int[] randomSwappingRoute = RandomOptimization.randomSwappingRoute(weightMatrix, route);

        System.out.println("Random Swapping Shortest path: " + Arrays.toString(randomSwappingRoute));
        System.out.println("Random Swapping Route Sum " + OptimizationHelperFunctions.calculateRouteSum(weightMatrix,
                randomSwappingRoute));


        int[] twoOptOptimizationTechnique = TwoOptOptimization.twoOptOptimization(routeCopy, weightMatrix);

        System.out.println("Two Opt Optimization Path: " + Arrays.toString(twoOptOptimizationTechnique));
        System.out.println("Two Opt Route Sum " + OptimizationHelperFunctions.calculateRouteSum(weightMatrix,
                twoOptOptimizationTechnique));
    }
}
