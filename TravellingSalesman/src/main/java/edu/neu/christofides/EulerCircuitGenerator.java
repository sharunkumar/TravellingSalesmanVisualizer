package edu.neu.christofides;

import edu.neu.graphs.node.GraphNode;
import edu.neu.modals.Location;
import edu.neu.modals.TravelPath;

import java.util.LinkedList;
import java.util.Objects;
import java.util.Vector;

public class EulerCircuitGenerator {

    public static TravelPath generateEulerCircuit(Location[] locations, int[] minimumSpanningTree,
                                                  double[][] weightMatrix) {
        
        int[][] matchGraph = GreedyMatch.greedyMatch(minimumSpanningTree, weightMatrix);
        GraphNode[] nodes = MultiGraph.build(matchGraph, minimumSpanningTree);
        int[] route = generateEulerCircuit(nodes);

        return new TravelPath(locations, route, weightMatrix);
    }

    public static int[] generateEulerCircuit(GraphNode[] nodes) {
        var path = new LinkedList<Integer>();
        var tmpPath = new Vector<Integer>();
        int j = 0;

        nodes[0].getNextChild(nodes[0].getName(), tmpPath, true);
        path.addAll(0, tmpPath);


        while (j < path.size()) {
            if (nodes[path.get(j)].hasMoreChilds()) {
                nodes[path.get(j)].getNextChild(nodes[path.get(j)].getName(), tmpPath, true);
                if (tmpPath.size() > 0) {
                    for (int i = 0; i < path.size(); i++) {
                        if (Objects.equals(path.get(i), tmpPath.elementAt(0))) {
                            path.addAll(i, tmpPath);
                            break;
                        }
                    }
                    tmpPath.clear();
                }
                j = 0;
            } else j++;
        }


        boolean[] inPath = new boolean[nodes.length];
        int[] route = new int[nodes.length];
        j = 0;
        for (Integer integer : path) {
            if (!inPath[integer]) {
                route[j] = integer;
                j++;
                inPath[integer] = true;
            }
        }

        return route;
    }
}
