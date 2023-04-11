package edu.neu.christofides;

import edu.neu.graphs.node.GraphNode;

import java.util.Arrays;

public class ChristofidesAlgorithm {
    public static int[] run(double[][] weightMatrix) {
        int[] minimumSpanningTree = PrimsAlgorithm.run(weightMatrix, weightMatrix[0].length);
        int[][] matchGraph = GreedyMatch.greadyMatch(minimumSpanningTree, weightMatrix, weightMatrix[0].length);

        GraphNode[] nodes = MultiGraph.build(matchGraph, minimumSpanningTree);
        int route[] = EulerCircuitGenerator.generateEulerCircuit(nodes);

        double sum = 0;

        for (int i = 1; i < route.length; i++) {
            sum += weightMatrix[route[i - 1]][route[i]];
        }
        sum += weightMatrix[route[0]][route[route.length - 1]];
        System.out.println("Route = " + Arrays.toString(route));
        System.out.println("Total Sum : " + sum);

        return route;
    }
}
