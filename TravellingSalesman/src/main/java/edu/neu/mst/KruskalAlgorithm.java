/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.neu.mst;

import edu.neu.modals.Location;

import java.util.HashMap;
import java.util.PriorityQueue;

// FIXME see if this works
public class KruskalAlgorithm {
    public static final int MAX = 100;
    private final HashMap<Location, Location> parent = new HashMap<>();
    private final int[][] mst = new int[MAX][MAX];
//    private Edge[] edges = new Edge[MAX];

    public int[][] findMST(Graph graph) {

        int edgeCount = graph.geteCount();
        var edges = new PriorityQueue<>(graph.getEdges());

        for (var loc : graph.getLocations()) {
            parent.put(loc, loc);
        }

        while (!edges.isEmpty()) {
            var edge = edges.poll();
            //find root of u
            var u = findRoot(edge.getU());
            //find root of v
            var v = findRoot(edge.getV());

            //if u== v then the cycle created
            if (u != v) {
//                var x = edge.getU();
//                var y = edge.getV();

                union(u, v);

                System.out.println("u: " + u + " v: " + v);

            }
        }

        return mst;
    }

    Location findRoot(Location x) {
        while (parent.get(x) != x) {
            x = parent.get(x);
        }
        return x;
    }

    void union(Location u, Location v) {
        if (parent.get(u) == u && parent.get(v) == v) {
            parent.put(v, u);
        } else if (parent.get(u) == u && parent.get(v) != v) {
            parent.put(u, v);
        } else if (parent.get(v) == v && parent.get(u) != u) {
            parent.put(v, u);
        } else if (parent.get(u) != u && parent.get(v) != v) {
            var root1 = findRoot(u);
            var root2 = findRoot(v);
            parent.put(root1, root2);
        }
    }
}
