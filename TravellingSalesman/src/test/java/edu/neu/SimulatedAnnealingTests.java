package edu.neu;

import edu.neu.optimizations.strategic.SimulatedAnnealing;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SimulatedAnnealingTests {
    private final double[][] weightMatrix = {
            {0, 2, 9, 10},
            {2, 0, 6, 4},
            {9, 6, 0, 3},
            {10, 4, 3, 0}
    };

    @Test
    public void testRoute1() {
        int[] expectedRoute = {0, 1, 3, 2};
        int[] actualRoute = SimulatedAnnealing.run(weightMatrix, 100, 0.99);
        Assertions.assertArrayEquals(expectedRoute, actualRoute);
    }

    @Test
    public void testRoute2() {
        int[] expectedRoute = {0, 1, 3, 2};
        int[] actualRoute = SimulatedAnnealing.run(weightMatrix, 1000, 0.95);
        Assertions.assertArrayEquals(expectedRoute, actualRoute);
    }

    @Test
    public void testRoute3() {
        int[] expectedRoute = {0, 1, 3, 2};
        int[] actualRoute = SimulatedAnnealing.run(weightMatrix, 5000, 0.9);
        Assertions.assertArrayEquals(expectedRoute, actualRoute);
    }

    @Test
    public void testRoute4() {
        int[] expectedRoute = {0, 1, 3, 2};
        int[] actualRoute = SimulatedAnnealing.run(weightMatrix, 10000, 0.85);
        Assertions.assertArrayEquals(expectedRoute, actualRoute);
    }

    @Test
    public void testRoute5() {
        int[] expectedRoute = {0, 1, 3, 2};
        int[] actualRoute = SimulatedAnnealing.run(weightMatrix, 20000, 0.8);
        Assertions.assertArrayEquals(expectedRoute, actualRoute);
    }

    @Test
    public void testRoute6() {
        int[] expectedRoute = {0, 1, 3, 2};
        int[] actualRoute = SimulatedAnnealing.run(weightMatrix, 30000, 0.75);
        Assertions.assertArrayEquals(expectedRoute, actualRoute);
    }

    @Test
    public void testRoute7() {
        int[] expectedRoute = {0, 1, 3, 2};
        int[] actualRoute = SimulatedAnnealing.run(weightMatrix, 40000, 0.7);
        Assertions.assertArrayEquals(expectedRoute, actualRoute);
    }

    @Test
    public void testRoute8() {
        int[] expectedRoute = {0, 1, 3, 2};
        int[] actualRoute = SimulatedAnnealing.run(weightMatrix, 50000, 0.65);
        Assertions.assertArrayEquals(expectedRoute, actualRoute);
    }

    @Test
    public void testRoute9() {
        int[] expectedRoute = {0, 1, 3, 2};
        int[] actualRoute = SimulatedAnnealing.run(weightMatrix, 60000, 0.6);
        Assertions.assertArrayEquals(expectedRoute, actualRoute);
    }
}
