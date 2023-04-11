/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.neu.mst;

import edu.neu.modals.Location;

import java.util.ArrayList;
import java.util.List;

public class Graph {
    //    protected final int eCount;
    private final Location[] locations;
    protected List<Edge> edges;
    protected int[][] mst;
//    protected List<Point> points = new ArrayList<>();

//    public Graph(int vCount, int eCount) {
//        this.vCount = vCount;
//        this.eCount = eCount;
//        genGraph();
//    }

    public Graph(Location[] locations) {
        this.locations = locations;
//        this.eCount = locations.length * (locations.length - 1) / 2;
        edges = new ArrayList<>();
        int i = 0;

        for (var u : locations) {
            for (var v : locations) {
                var curr = new Edge(u, v);
                if (u != v && edges.stream().noneMatch(e -> e.compareTo(curr) == 0)) {
                    edges.add(curr);
                }
            }
        }
    }

    public static void main(String[] args) {

    }
//    public boolean isInMst(int u, int v) {
//        return mst[u][v] != 0;
//    }

    public Location[] getLocations() {
        return locations;
    }

    private void findMst() {
        KruskalAlgorithm kA = new KruskalAlgorithm();
        mst = kA.findMST(this);
    }

    public List<Edge> getEdges() {
        return edges;
    }

    public int getvCount() {
        return locations.length;
    }

//    private void genGraph() {
//        edges = new Edge[eCount];
//        for (int i = 0; i < vCount - 1; ++i) {
//            edges[i] = genEdge(i, i + 1);
//        }
//
//        edges[vCount - 1] = genEdge(vCount - 1, 0);
//
//        //augument graph with some more edges
//        int eGen = vCount;
//        boolean isFull = false;
//        for (int i = 0; !isFull && i < vCount; ++i) {
//            for (int j = 0; !isFull && j < vCount; ++j) {
//                if (i != j && !isExisting(i, j, eGen)) {
//                    edges[eGen] = genEdge(i, j);
//                    eGen++;
//                }
//                isFull = (eGen == eCount);
//            }
//        }
//        printGraph();
//    }


//    public void printGraph() {
//        for (int i = 0; i < eCount; ++i) {
//            System.out.println("u: " + edges[i].getU() + " v: " + edges[i].getV() + " weight: " + edges[i]
//            .getWeight());
//        }
//    }

//    private boolean isExisting(int u, int v, int graphSize) {
//        for (int i = 0; i < graphSize; ++i) {
//            if (edges[i].makeOf(u, v)) {
//                return true;
//            }
//        }
//        return false;
//    }
//
//    private Edge genEdge(int u, int v) {
//        Random random = new Random();
//        int weight = Math.abs(random.nextInt() % 100 + 1);
//        Edge e = new Edge(u, v, weight);
//        return e;
//    }

    public int geteCount() {
        return edges.size();
    }
}
