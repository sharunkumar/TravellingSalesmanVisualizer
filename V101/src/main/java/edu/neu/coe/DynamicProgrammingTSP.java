package edu.neu.coe;
import java.io.* ;
import java.math.*;
import java.util.*;

public class DynamicProgrammingTSP {
    public static int[][] adjacencyMatrix;
    public static int numberOfVertices = 0;
    public static long[][] dynamicProgrammingCache;
    public DynamicProgrammingTSP(int[][] routeMap) {

        numberOfVertices = routeMap[0].length;
        System.out.println("Length of routeMap (Dynamic Programming Technique): "+numberOfVertices);
        adjacencyMatrix = new int[numberOfVertices][numberOfVertices];
        adjacencyMatrix = routeMap;

        // Initializing cache with default values of -1
        double twoPowerN = Math.pow(2, numberOfVertices);
        dynamicProgrammingCache = new long[(int)twoPowerN][numberOfVertices]; // Because we are reducing the code complexity to O(n.2^n) complexity
        for(int i=0; i<twoPowerN; i++) {
            for(int j=0; j<numberOfVertices; j++) {
                dynamicProgrammingCache[i][j] = -1;
            }
        }

    }

    public long travelingSalesmanDP(int mask, int vertex) {
        long returnValue = (long) 1e18;
        long answer = 0;
        if(mask==(Math.pow(2, numberOfVertices)-1)) {
            return adjacencyMatrix[vertex][0];
        }
        else {
            if(dynamicProgrammingCache[mask][vertex]!=-1) {
                return dynamicProgrammingCache[mask][vertex];
            }
            for(int i=0; i<numberOfVertices; i++) {
                if ((mask & (1 << i)) == 0) {
                    answer = adjacencyMatrix[vertex][i] + travelingSalesmanDP(mask | (1 << i), i);
                    returnValue = Math.min(answer, returnValue);
                }
            }
        }
        dynamicProgrammingCache[mask][vertex] = returnValue;
        return returnValue;
    }
}
