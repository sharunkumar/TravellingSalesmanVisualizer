package edu.neu.coe;
import java.io.* ;
import java.math.*;
import java.util.*;

public class RecursionTSP {
    public static int[][] adjacencyMatrix;
    public static int numberOfVertices = 0;
    public RecursionTSP(int[][] routeMap) {
        numberOfVertices = routeMap[0].length;
        System.out.println("Length of routeMap: "+numberOfVertices);
        adjacencyMatrix = new int[numberOfVertices][numberOfVertices];
        adjacencyMatrix = routeMap;
    }

    public long travelingSalesmanRecursion(int mask, int vertex) {
        long returnValue = (long) 1e18;
        long answer = 0;
        if(mask==(Math.pow(2, numberOfVertices)-1)) {
            return adjacencyMatrix[vertex][0];
        }
        else {
            for(int i=0; i<numberOfVertices; i++) {
                if ((mask & (1 << i)) == 0) {
                    answer = adjacencyMatrix[vertex][i] + travelingSalesmanRecursion(mask | (1 << i), i);
                    returnValue = Math.min(answer, returnValue);
                }
            }
        }
        return returnValue;
    }
}
