package edu.neu;

import edu.neu.christofides.EulerCircuitGenerator;
import edu.neu.christofides.GreedyMatch;
import edu.neu.christofides.MultiGraph;
import edu.neu.christofides.PrimsAlgorithm;
import edu.neu.data.DataSet;
import edu.neu.graphs.node.GraphNode;
import edu.neu.optimizations.tactical.RandomOptimization;
import edu.neu.optimizations.tactical.TwoOptOptimization;

import java.io.IOException;
import java.util.Arrays;

import static edu.neu.utilties.TSPUtilities.calculateDistance;
import static edu.neu.utilties.TSPUtilities.getWeightMatrix;

public class Main {

    public static void main(String[] args) throws IOException {
        var locations = DataSet.DefaultDataSet().getLocations();
        
        double[][] weightMatrix = getWeightMatrix(locations);

        int[] minimumSpanningTree = PrimsAlgorithm.run(weightMatrix);

        int[][] matchGraph = GreedyMatch.greedyMatch(minimumSpanningTree, weightMatrix);

        GraphNode[] nodes = MultiGraph.build(matchGraph, minimumSpanningTree);
        int[] route = EulerCircuitGenerator.generateEulerCircuit(nodes);
        int[] routeCopy = Arrays.copyOf(route, route.length);

        System.out.println("Shortest Route = " + Arrays.toString(route));
        System.out.println("Shortest Route Total Sum : " + calculateDistance(route, weightMatrix));

        int[] randomSwappingRoute = RandomOptimization.randomSwappingRoute(weightMatrix, route);

        System.out.println("Random Swapping Shortest path: " + Arrays.toString(randomSwappingRoute));
        System.out.println("Random Swapping Route Sum " + calculateDistance(randomSwappingRoute, weightMatrix));


        int[] twoOptOptimizationTechnique = TwoOptOptimization.twoOptOptimization(routeCopy, weightMatrix);

        System.out.println("Two Opt Optimization Path: " + Arrays.toString(twoOptOptimizationTechnique));
        System.out.println("Two Opt Route Sum " + calculateDistance(twoOptOptimizationTechnique, weightMatrix));
    }
}
