package edu.neu.christofides;

import edu.neu.graphs.node.GraphNode;
import java.util.Arrays;
import java.util.Random;

public class SimulatedAnnealing {
    public static int[] run(double[][] weightMatrix, double temperature, double coolingRate) {
        int[] minimumSpanningTree = PrimsAlgorithm.run(weightMatrix); //, weightMatrix[0].length
        int[][] matchGraph = GreedyMatch.greadyMatch(minimumSpanningTree, weightMatrix, weightMatrix[0].length);

        GraphNode[] nodes = MultiGraph.build(matchGraph, minimumSpanningTree);
        int[] currentRoute = EulerCircuitGenerator.generateEulerCircuit(nodes);
        int[] bestRoute = currentRoute.clone();
        double currentEnergy = calculateEnergy(currentRoute, weightMatrix);
        double bestEnergy = currentEnergy;

        Random random = new Random();

        while (temperature > 1) {
            int[] newRoute = perturbRoute(currentRoute, random);
            double newEnergy = calculateEnergy(newRoute, weightMatrix);
            double deltaEnergy = newEnergy - currentEnergy;

            if (deltaEnergy < 0 || Math.exp(-deltaEnergy / temperature) > random.nextDouble()) {
                currentRoute = newRoute.clone();
                currentEnergy = newEnergy;

                if (currentEnergy < bestEnergy) {
                    bestRoute = currentRoute.clone();
                    bestEnergy = currentEnergy;
                }
            }

            temperature *= coolingRate;
        }

        System.out.println("Route = " + Arrays.toString(bestRoute));
        System.out.println("Total Sum : " + bestEnergy);

        return bestRoute;
    }

    private static double calculateEnergy(int[] route, double[][] weightMatrix) {
        double sum = 0;

        for (int i = 1; i < route.length; i++) {
            sum += weightMatrix[route[i - 1]][route[i]];
        }
        sum += weightMatrix[route[0]][route[route.length - 1]];

        return sum;
    }

    private static int[] perturbRoute(int[] route, Random random) {
        int[] newRoute = route.clone();

        int i = random.nextInt(newRoute.length);
        int j = random.nextInt(newRoute.length);

        int temp = newRoute[i];
        newRoute[i] = newRoute[j];
        newRoute[j] = temp;

        return newRoute;
    }
}
