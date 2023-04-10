/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.neu.mst;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Graph {
    protected Edge[] edges;
    protected int vCount;
    protected int eCount;
    protected int[][] mst;
    protected List<Point> points = new ArrayList<>();

    public Graph(int vCount, int eCount) {
        this.vCount = vCount;
        this.eCount = eCount;
        genGraph();
        findMst();
    }

    public boolean isInMst(int u, int v) {
        return mst[u][v] != 0;
    }

    private void findMst() {
        KruskalAlgorithm kA = new KruskalAlgorithm();
        mst = kA.findMST(this);
    }

    public Edge[] getEdges() {
        return edges;
    }

    public int getvCount() {
        return vCount;
    }

    public int geteCount() {
        return eCount;
    }

    public List<Point> getNodesCoordinates(int width, int height) {
        populateNodeCoordinates(width, height);
        return points;
    }

    private void populateNodeCoordinates(int width, int height) {

        Random random = new Random();

        points = new ArrayList<>();
        //populate node's coordinates
        for (int i = 0; i < vCount; ++i) {
            int x = Math.abs(random.nextInt() % width);
            int y = Math.abs(random.nextInt() % height);
            Point p = new Point(x, y);
            points.add(p);
        }

    }

    private void genGraph() {
        edges = new Edge[eCount];
        for (int i = 0; i < vCount - 1; ++i) {
            edges[i] = genEdge(i, i + 1);
        }

        edges[vCount - 1] = genEdge(vCount - 1, 0);

        //augument graph with some more edges
        int eGen = vCount;
        boolean isFull = false;
        for (int i = 0; !isFull && i < vCount; ++i) {
            for (int j = 0; !isFull && j < vCount; ++j) {
                if (i != j && !isExisting(i, j, eGen)) {
                    edges[eGen] = genEdge(i, j);
                    eGen++;
                }
                isFull = (eGen == eCount);
            }
        }
        printGraph();
    }


    public void printGraph() {
        for (int i = 0; i < eCount; ++i) {
            System.out.println("u: " + edges[i].getU() + " v: " + edges[i].getV() + " weight: " + edges[i].getWeight());
        }
    }

    private boolean isExisting(int u, int v, int graphSize) {
        for (int i = 0; i < graphSize; ++i) {
            if (edges[i].makeOf(u, v)) {
                return true;
            }
        }
        return false;
    }

    private Edge genEdge(int u, int v) {
        Random random = new Random();
        int weight = Math.abs(random.nextInt() % 100 + 1);
        Edge e = new Edge(u, v, weight);
        return e;
    }
}
