package edu.neu.mainRunner;

import edu.neu.christofides.*;
import edu.neu.graphs.node.GraphNode;
import edu.neu.tactical.optimizations.OptimizationHelperFunctions;
import edu.neu.tactical.optimizations.RandomOptimization;
import edu.neu.tactical.optimizations.TwoOptOptimization;
import edu.neu.utility.ReadDistanceMatrix;

import java.io.IOException;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws IOException {

        double [][] weightMatrix  = ReadDistanceMatrix.readDistanceMatrix(Constants.DATA_SET_LOCATION);

        int[] minimumSpanningTree = PrimsAlgorithm.run(weightMatrix, weightMatrix[0].length);
        int[][] matchGraph = GreedyMatch.greadyMatch(minimumSpanningTree, weightMatrix, weightMatrix[0].length);

        GraphNode nodes[] = MultiGraph.build(matchGraph, minimumSpanningTree);
        int route[] = EulerCircuitGenerator.generateEulerCircuit(nodes);

        double sum=0;

        for(int i=1;i<route.length;i++){
            sum+=weightMatrix[route[i-1]][route[i]];
        }
        sum+=weightMatrix[route[0]][route[route.length-1]];
        System.out.println("Route = " + Arrays.toString(route));
        System.out.println("Total Sum : "+sum);

        int[] randomSwappingRoute = RandomOptimization.randomSwappingRoute(weightMatrix, route);

        System.out.println("Random Swapping Shortest path: "+Arrays.toString(randomSwappingRoute));
        System.out.println("Route Sum " + OptimizationHelperFunctions.calculateRouteSum(weightMatrix, randomSwappingRoute));


       // int[] twoOptOptimizationTechnique = TwoOptOptimization.twoOptOptimization(route, weightMatrix);

       // System.out.println("Two Opt Optimization Path: "+Arrays.toString(twoOptOptimizationTechnique));
        //System.out.println("Route Sum " + OptimizationHelperFunctions.calculateRouteSum(weightMatrix, randomSwappingRoute));

    }
}
