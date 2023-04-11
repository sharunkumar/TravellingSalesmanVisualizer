package edu.neu.mainRunner;

import edu.neu.christofides.EulerCircuitGenerator;
import edu.neu.christofides.GreedyMatch;
import edu.neu.christofides.MultiGraph;
import edu.neu.christofides.PrimsAlgorithm;
import edu.neu.graphs.node.GraphNode;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {

        double [][] weightMatrix  = new double[100][100]; // placeholder for adjacency matrix

        int[] minimumSpanningTree = PrimsAlgorithm.run(weightMatrix, weightMatrix.length);
        int[][] matchGraph = GreedyMatch.greadyMatch(minimumSpanningTree, weightMatrix, weightMatrix.length);

        GraphNode nodes[] = MultiGraph.build(matchGraph, minimumSpanningTree);
        int route[] = EulerCircuitGenerator.generateEulerCircuit(nodes);

        double sum=0;

        for(int i=1;i<route.length;i++){
            sum+=weightMatrix[route[i-1]][route[i]];
        }
        sum+=weightMatrix[route[0]][route[route.length-1]];
        System.out.println("Route = " + Arrays.toString(route));
        System.out.println("Total Sum : "+sum);


    }
}
