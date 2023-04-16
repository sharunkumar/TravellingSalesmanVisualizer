package edu.neu.christofides;

import edu.neu.graphs.node.GraphNode;

import java.util.Arrays;

import static edu.neu.utilties.TSPUtilities.calculateDistance;

public class ChristofidesAlgorithm {
    public static int[] run(double[][] weightMatrix) {
        int[] minimumSpanningTree = PrimsAlgorithm.run(weightMatrix);
        int[][] matchGraph = GreedyMatch.greedyMatch(minimumSpanningTree, weightMatrix);

        GraphNode[] nodes = MultiGraph.build(matchGraph, minimumSpanningTree);
        int[] route = EulerCircuitGenerator.generateEulerCircuit(nodes);

        System.out.println("Route = " + Arrays.toString(route));
        System.out.println("Total Sum : " + calculateDistance(route, weightMatrix));

        return route;
    }
}
