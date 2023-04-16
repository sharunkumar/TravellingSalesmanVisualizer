package edu.neu.coe;

public class NearestNeighborsHeuristicAlgorithm {
    public static int[][] adjacencyMatrix;
    public static int numberOfVertices = 0;
    public static long totalDistance = 0;
    public NearestNeighborsHeuristicAlgorithm(int[][] routeMap) {
        numberOfVertices = routeMap[0].length;
        System.out.println("Length of routeMap (Nearest Neighbors Heuristics): "+numberOfVertices);
        adjacencyMatrix = new int[numberOfVertices][numberOfVertices];
        adjacencyMatrix = routeMap;
    }
    public long NearestNeighbors(int mask, int vertex) {
        if(mask==(Math.pow(2, numberOfVertices)-1)) {
            return adjacencyMatrix[vertex][0];
        }
        else {
            long minimumEdge = (long)1e19;
            int vertextToVisit = vertex;
            for(int i=0; i<numberOfVertices; i++) {
                if((mask&(1<<i))==0) {
                    if (adjacencyMatrix[vertex][i] < minimumEdge) {
                        minimumEdge = adjacencyMatrix[vertex][i];
                        vertextToVisit = i;
                    }
                }
            }
            totalDistance = totalDistance + minimumEdge + NearestNeighbors(mask|(1<<vertextToVisit), vertextToVisit);
        }
        return totalDistance;
    }

}
