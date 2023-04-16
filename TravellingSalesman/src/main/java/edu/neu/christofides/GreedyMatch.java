package edu.neu.christofides;

import edu.neu.graphs.edge.Edge;
import edu.neu.graphs.node.Node;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GreedyMatch {
    public static int[][] greedyMatch(int[] minimumSpanningTree, double[][] weightMatrix) {

        int weightMatrixSize = weightMatrix[0].length;
        
        Node[] nodes = new Node[minimumSpanningTree.length];

        nodes[0] = new Node(0, true);
        for (int i = 1; i < minimumSpanningTree.length; i++) {
            nodes[i] = new Node(i, false);
        }

        for (int i = 0; i < minimumSpanningTree.length; i++) {
            if (minimumSpanningTree[i] != i)
                nodes[minimumSpanningTree[i]].addChild(nodes[i]);
        }

        ArrayList<Integer> oddDegreeNodes = findOddDegreeNodes(nodes[0]);
        int nOdd = oddDegreeNodes.size();


        Edge[][] edges = new Edge[nOdd][nOdd];
        for (int i = 0; i < nOdd; i++) {
            for (int j = 0; j < nOdd; j++) {
                if ((oddDegreeNodes.get(i)).intValue() != (oddDegreeNodes.get(j)).intValue())
                    edges[i][j] = new Edge((oddDegreeNodes.get(i)).intValue(),
                            (oddDegreeNodes.get(j)).intValue(),
                            weightMatrix[(oddDegreeNodes.get(i)).intValue()][(oddDegreeNodes.get(j)).intValue()]);
                else
                    edges[i][j] = new Edge((oddDegreeNodes.get(i)).intValue(),
                            (oddDegreeNodes.get(j)).intValue(), Double.MAX_VALUE);
            }
            Arrays.sort(edges[i]);
        }

        boolean[] matched = new boolean[weightMatrixSize];
        int[][] match = new int[(nOdd / 2)][2];


        int k = 0;
        for (int i = 0; i < nOdd; i++) {
            for (int j = 0; j < nOdd; j++) {
                if (matched[edges[i][j].getFrom()] || matched[edges[i][j].getTo()])
                    continue;
                else {
                    matched[edges[i][j].getFrom()] = true;
                    matched[edges[i][j].getTo()] = true;
                    match[k][0] = edges[i][j].getFrom();
                    match[k][1] = edges[i][j].getTo();
                    k++;
                }
            }
        }

        return match;
    }

    private static ArrayList<Integer> findOddDegreeNodes(Node _root) {
        var oddNodes = new ArrayList<Integer>();
        _root.visitFindOddDegreeNodes(oddNodes);
        return oddNodes;
    }

    private static List<Integer>[] buildAdjacencyList(int[] minimumSpanningTree, int length) {
        List<Integer>[] adj = new List[length];
        for (int i = 0; i < length; i++) {
            adj[i] = new ArrayList<>();
        }
        for (int i = 0; i < minimumSpanningTree.length; i += 2) {
            int u = minimumSpanningTree[i];
            int v = minimumSpanningTree[i + 1];
            adj[u].add(v);
            adj[v].add(u);
        }
        return adj;
    }
}
