package edu.neu.optimizations.tactical;

public class OptimizationHelperFunctions {

    static void swap(int[] route, int i, int j) {
        int temp = route[i];
        route[i] = route[j];
        route[j] = temp;
    }
}
