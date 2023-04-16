package edu.neu.christofides;

import edu.neu.graphs.node.GraphNode;

public class MultiGraph {
    public static GraphNode[] build(int[][] match, int[] mst) {
        GraphNode[] nodes = new GraphNode[mst.length];

        for (int i = 0; i < mst.length; i++) {
            nodes[i] = new GraphNode(i);
        }


        for (int i = 1; i < mst.length; i++) {
            nodes[i].addChild(nodes[mst[i]]);
            nodes[mst[i]].addChild(nodes[i]);
        }


        for (int[] ints : match) {
            nodes[ints[0]].addChild(nodes[ints[1]]);
            nodes[ints[1]].addChild(nodes[ints[0]]);
        }

        return nodes;
    }
}
