package edu.neu.optimizations.strategic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static edu.neu.christofides.Constants.RANDOM;

public class AntColonyOptimization {
    private final double[][] weightMatrix;
    private final int numNodes;
    private final int numAnts;
    private final double alpha;
    private final double beta;
    private final double evaporationRate;
    private final double initialPheromoneLevel;
    public double[][] pheromoneMatrix;

    public AntColonyOptimization(double[][] weightMatrix, int numAnts, double alpha, double beta,
                                 double evaporationRate, double initialPheromoneLevel) {
        this.weightMatrix = weightMatrix;
        this.numNodes = weightMatrix[0].length;
        this.numAnts = numAnts;
        this.alpha = alpha;
        this.beta = beta;
        this.evaporationRate = evaporationRate;
        this.initialPheromoneLevel = initialPheromoneLevel;
        this.pheromoneMatrix = new double[numNodes][numNodes];
        initPheromones();
    }

    public void initPheromones() {
        for (int i = 0; i < numNodes; i++) {
            for (int j = 0; j < numNodes; j++) {
                pheromoneMatrix[i][j] = initialPheromoneLevel;
            }
        }
    }

    public int[] run() {
        List<Integer> nodes = new ArrayList<>();
        for (int i = 0; i < numNodes; i++) {
            nodes.add(i);
        }
        int[] bestRoute = null;
        double bestDistance = Double.MAX_VALUE;
        for (int iter = 0; iter < numAnts; iter++) {
            Collections.shuffle(nodes);
            int[] route = new int[numNodes];
            route[0] = nodes.get(0);
            nodes.remove(0);
            for (int i = 1; i < numNodes; i++) {
                int currentNode = route[i - 1];
                int nextNode = selectNextNode(currentNode, nodes);
                route[i] = nextNode;
                nodes.remove(Integer.valueOf(nextNode));
            }
            double distance = calculateDistance(route, weightMatrix);
            if (distance < bestDistance) {
                bestDistance = distance;
                bestRoute = Arrays.copyOf(route, numNodes);
            }
            updatePheromones(route, distance);
            nodes.clear();
            for (int i = 0; i < numNodes; i++) {
                nodes.add(i);
            }
        }
        System.out.println("Best Route = " + Arrays.toString(bestRoute));
        System.out.println("Total Distance = " + bestDistance);
        return bestRoute;
    }

    public double calculateDistance(int[] route, double[][] weightMatrix) {
        double distance = 0.0;
        for (int i = 0; i < route.length - 1; i++) {
            int fromNode = route[i];
            int toNode = route[i + 1];
            distance += weightMatrix[fromNode][toNode];
        }
        // Add distance from last node to first node
        distance += weightMatrix[route[numNodes - 1]][route[0]];
        return distance;
    }


    public int selectNextNode(int currentNode, List<Integer> nodes) {
        double[] probabilities = new double[numNodes];
        double sum = 0.0;
        for (int node : nodes) {
            probabilities[node] = Math.pow(pheromoneMatrix[currentNode][node], alpha)
                    * Math.pow(1.0 / weightMatrix[currentNode][node], beta);
            sum += probabilities[node];
        }
        for (int i = 0; i < numNodes; i++) {
            probabilities[i] /= sum;
        }
        double rand = RANDOM.nextDouble();
        double p = 0.0;
        for (int i = 0; i < numNodes; i++) {
            p += probabilities[i];
            if (rand <= p) {
                if (nodes.contains(i)) {
                    return i;
                } else {
                    break; // Selected node is not in list, fall back to first node
                }
            }
        }
        return nodes.get(0);
    }

    public void updatePheromones(int[] route, double distance) {
        double delta = 1.0 / distance;
        for (int i = 0; i < numNodes - 1; i++) {
            int currentNode = route[i];
            int nextNode = route[i + 1];
            pheromoneMatrix[currentNode][nextNode] = (1 - evaporationRate) * pheromoneMatrix[currentNode][nextNode]
                    + evaporationRate * delta;
            pheromoneMatrix[nextNode][currentNode] = (1 - evaporationRate) * pheromoneMatrix[nextNode][currentNode]
                    + evaporationRate * delta;
        }
        // Update pheromones for last and first node
        int lastNode = route[numNodes - 1];
        int firstNode = route[0];
        pheromoneMatrix[lastNode][firstNode] = (1 - evaporationRate) * pheromoneMatrix[lastNode][firstNode]
                + evaporationRate * delta;
        pheromoneMatrix[firstNode][lastNode] = (1 - evaporationRate) * pheromoneMatrix[firstNode][lastNode]
                + evaporationRate * delta;
    }
}
