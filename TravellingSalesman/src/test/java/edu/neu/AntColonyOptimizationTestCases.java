package edu.neu;

import edu.neu.optimizations.strategic.AntColonyOptimization;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AntColonyOptimizationTestCases {

    private static final double[][] WEIGHT_MATRIX = {{0, 2, 9, 10},
            {1, 0, 6, 4},
            {15, 7, 0, 8},
            {6, 3, 12, 0}};

    private static final int NUM_ANTS = 5;
    private static final double ALPHA = 1.0;
    private static final double BETA = 2.0;
    private static final double EVAPORATION_RATE = 0.5;
    private static final double INITIAL_PHEROMONE_LEVEL = 0.1;

    @Test
    public void testSelectNextNode() {
        AntColonyOptimization aco = new AntColonyOptimization(WEIGHT_MATRIX, NUM_ANTS, ALPHA, BETA,
                EVAPORATION_RATE, INITIAL_PHEROMONE_LEVEL);
        int nextNode = aco.selectNextNode(0, Arrays.asList(1, 2, 3));
        assertTrue(nextNode == 1 || nextNode == 2 || nextNode == 3);
    }

    @Test
    public void testCalculateDistance() {
        AntColonyOptimization aco = new AntColonyOptimization(WEIGHT_MATRIX, NUM_ANTS, ALPHA, BETA,
                EVAPORATION_RATE, INITIAL_PHEROMONE_LEVEL);
        int[] route = {0, 1, 2, 3, 0};
        double distance = aco.calculateDistance(route, WEIGHT_MATRIX);
        assertEquals(28.0, distance, 0.01);
    }

    @Test
    void testSelectNextNodeWithEmptyNodes() {
        double[][] weightMatrix = {{0, 2, 3}, {2, 0, 5}, {3, 5, 0}};
        AntColonyOptimization aco = new AntColonyOptimization(weightMatrix, 5, 1.0, 2.0, 0.5, 0.5);
        List<Integer> nodes = new ArrayList<>();
        int currentNode = 0;
        int nextNode = aco.selectNextNode(currentNode, nodes);
        assertEquals(-1, nextNode);
    }

    @Test
    public void testCalculateDistance1() {
        double[][] weightMatrix = {{0, 1, 2}, {1, 0, 3}, {2, 3, 0}};
        AntColonyOptimization aco = new AntColonyOptimization(weightMatrix, 3, 1, 2, 0.5, 1);
        int[] route = {0, 1, 2};
        double expectedDistance = 6;
        double actualDistance = aco.calculateDistance(route, weightMatrix);
        assertEquals(expectedDistance, actualDistance, 0.0);
    }

    @Test
    public void testSelectNextNode1() {
        double[][] weightMatrix = {{0, 1, 2}, {1, 0, 3}, {2, 3, 0}};
        AntColonyOptimization aco = new AntColonyOptimization(weightMatrix, 3, 1, 2, 0.5, 1);
        int currentNode = 0;
        List<Integer> nodes = Arrays.asList(1, 2);
        int actualNextNode = aco.selectNextNode(currentNode, nodes);
        assertTrue(nodes.contains(actualNextNode));
    }

    @Test
    public void testUpdatePheromones() {
        double[][] weightMatrix = {{0, 1, 2}, {1, 0, 3}, {2, 3, 0}};
        AntColonyOptimization aco = new AntColonyOptimization(weightMatrix, 3, 1, 2, 0.5, 1);
        int[] route = {0, 1, 2};
        double distance = aco.calculateDistance(route, weightMatrix);
        aco.updatePheromones(route, distance);
        double expectedPheromoneLevel = 0.5833333333333334;
        double actualPheromoneLevel = aco.pheromoneMatrix[0][1];
        assertEquals(expectedPheromoneLevel, actualPheromoneLevel, 0.0);
    }

    @Test
    public void testInitPheromones() {
        double[][] weightMatrix = {{0, 1, 2}, {1, 0, 3}, {2, 3, 0}};
        AntColonyOptimization aco = new AntColonyOptimization(weightMatrix, 3, 1, 2, 0.5, 1);
        aco.initPheromones();
        double expectedPheromoneLevel = 1.0;
        double actualPheromoneLevel = aco.pheromoneMatrix[0][1];
        assertEquals(expectedPheromoneLevel, actualPheromoneLevel, 0.0);
    }

//    @Test
//    public void testUpdatePheromones1() {
//        double[][] weightMatrix = {
//                {0, 3, 1, 5},
//                {3, 0, 4, 2},
//                {1, 4, 0, 6},
//                {5, 2, 6, 0}
//        };
//        double initialPheromoneLevel = 0.5;
//        double evaporationRate = 0.1;
//        AntColonyOptimization aco = new AntColonyOptimization(weightMatrix, 5, 1, 2, evaporationRate,
//        initialPheromoneLevel);
//        int[] route = {0, 1, 2, 3, 0};
//        double expectedPheromoneLevel = (1 - evaporationRate) * initialPheromoneLevel + evaporationRate * (1.0 / 20
//        .0);
//        aco.updatePheromones(route, 16.0);
//        double[][] actualPheromoneLevels = aco.pheromoneMatrix;
//        assertEquals(expectedPheromoneLevel, actualPheromoneLevels[0][1], 0.001);
//        assertEquals(expectedPheromoneLevel, actualPheromoneLevels[1][0], 0.001);
//        assertEquals(expectedPheromoneLevel, actualPheromoneLevels[1][2], 0.001);
//        assertEquals(expectedPheromoneLevel, actualPheromoneLevels[2][1], 0.001);
//        assertEquals(expectedPheromoneLevel, actualPheromoneLevels[2][3], 0.001);
//        assertEquals(expectedPheromoneLevel, actualPheromoneLevels[3][2], 0.001);
//        assertEquals(expectedPheromoneLevel, actualPheromoneLevels[3][0], 0.001);
//        assertEquals(expectedPheromoneLevel, actualPheromoneLevels[0][3], 0.001);
//    }

}
