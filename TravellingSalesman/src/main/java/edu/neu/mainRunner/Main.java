package edu.neu.mainRunner;

import edu.neu.christofides.GreedyMatch;
import edu.neu.christofides.PrimsAlgorithm;

public class Main {

    public static void main(String[] args) {

        double [][] weightMatrix  = new double[100][100]; // placeholder for adjacency matrix

        int[] minimumSpanningTree = PrimsAlgorithm.run(weightMatrix, weightMatrix.length);
        int[][] matchGraph = GreedyMatch.greadyMatch(minimumSpanningTree, weightMatrix, weightMatrix.length);


    }
}
