package edu.neu.christofides;

import java.util.Arrays;
import java.util.Random;

public class SimulatedAnnealing {
    public static int[] run(double[][] weightMatrix) {
        int[] route = ChristofidesAlgorithm.run(weightMatrix);
        double temperature = 1000;
        double coolingRate = 0.003;
        Random random = new Random();

        while (temperature > 1) {
            int[] newRoute = Arrays.copyOf(route, route.length);

            int index1 = random.nextInt(newRoute.length);
            int index2 = random.nextInt(newRoute.length);

            int temp = newRoute[index1];
            newRoute[index1] = newRoute[index2];
            newRoute[index2] = temp;

            double currentCost = calculateCost(route, weightMatrix);
            double newCost = calculateCost(newRoute, weightMatrix);

            if (acceptanceProbability(currentCost, newCost, temperature) > random.nextDouble()) {
                route = newRoute;
            }

            temperature *= 1 - coolingRate;
        }

        double totalCost = calculateCost(route, weightMatrix);
        System.out.println("Improved Route = " + Arrays.toString(route));
        System.out.println("Total Cost : " + totalCost);

        return route;
    }

    private static double calculateCost(int[] route, double[][] weightMatrix) {
        double sum = 0;
        for (int i = 1; i < route.length; i++) {
            sum += weightMatrix[route[i - 1]][route[i]];
        }
        sum += weightMatrix[route[0]][route[route.length - 1]];
        return sum;
    }

    private static double acceptanceProbability(double currentCost, double newCost, double temperature) {
        if (newCost < currentCost) {
            return 1.0;
        }
        return Math.exp((currentCost - newCost) / temperature);
    }
}
