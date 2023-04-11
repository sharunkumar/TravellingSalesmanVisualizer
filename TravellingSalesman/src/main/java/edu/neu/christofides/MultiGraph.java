package edu.neu.christofides;

import edu.neu.graphs.node.GraphNode;

public class MultiGraph {
    public static GraphNode[] build(int[][] match, int[] mst) {
        GraphNode nodes[] = new GraphNode[mst.length];

        for (int i = 0; i < mst.length; i++) {
            nodes[i] = new GraphNode(i);
        }


        for (int i = 1; i < mst.length; i++) {
            nodes[i].addChild(nodes[mst[i]]);
            nodes[mst[i]].addChild(nodes[i]);
        }


        for (int i = 0; i < match.length; i++) {
            nodes[match[i][0]].addChild(nodes[match[i][1]]);
            nodes[match[i][1]].addChild(nodes[match[i][0]]);

        }

        return nodes;
    }
}
