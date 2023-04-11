package edu.neu.tactical.optimizations;

import java.util.Random;

public class RandomOptimization extends OptimizationHelperFunctions{

    public static int[] randomSwappingRoute(double[][] weightMatrix, int[] candidateShortestPath)
    {

        double sum = calculateRouteSum(weightMatrix, candidateShortestPath);
        System.out.println(" Current sum =" + sum);
        Random random = new Random();
        boolean improved = true;
        int count = 0;
        while (improved) {
            improved = false;
            for (int i = 1; i < candidateShortestPath.length - 1; i++) {
                for (int j = i + 1; j < candidateShortestPath.length - 1; j++) {
                    swap(candidateShortestPath, i, j);
                    double newSum = calculateRouteSum(weightMatrix, candidateShortestPath);
                    if (newSum < sum) {
                        count++;
                        System.out.println("NewSum = " + newSum + " Swapped Count = " +count + " Swapped " + candidateShortestPath[j] +" and " + candidateShortestPath[i]);
                        sum = newSum;
                        improved = true;


                    } else {
                        swap(candidateShortestPath, i, j);
                    }
                }
            }
        }


        return candidateShortestPath;

    }
}
