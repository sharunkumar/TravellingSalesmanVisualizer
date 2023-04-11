package edu.neu.tactical.optimizations;

public class OptimizationHelperFunctions {

    public static double calculateRouteSum(double[][] weightMatrix, int[] route) {
        double sum = 0;
        for (int i = 1; i < route.length; i++) {
            sum += weightMatrix[route[i - 1]][route[i]];
        }
        sum += weightMatrix[route[0]][route[route.length - 1]];
        return sum;
    }

     void swap(int[] route, int i, int j) {
        int temp = route[i];
        route[i] = route[j];
        route[j] = temp;
    }
}
