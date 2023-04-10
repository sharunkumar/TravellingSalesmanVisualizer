/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.neu.mst;

public class KruskalAlgorithm {
    public static final int MAX = 100;
    private final int[] parent = new int[MAX];
    private final int[][] mst = new int[MAX][MAX];
    private Edge[] edges = new Edge[MAX];
    private int edgeCount = 5;

    public int[][] findMST(Graph graph) {

        this.edgeCount = graph.geteCount();
        this.edges = graph.getEdges();

        for (int i = 0; i < edgeCount; ++i) {
            parent[i] = i;
        }

        sort();

        for (int i = 0; i < edgeCount; ++i) {
            //find root of u
            int u = findRoot(edges[i].getU());
            //find root of v
            int v = findRoot(edges[i].getV());

            //if u== v then the cycle created
            if (u != v) {
                int x = edges[i].getU();
                int y = edges[i].getV();

                //save the mst 
                mst[x][y] = mst[y][x] = edges[i].getWeight();

                union(u, v);

                System.out.println("u: " + u + " v: " + v);

            }
        }
        return mst;
    }

    int findRoot(int x) {
        while (parent[x] != x) {
            x = parent[x];
        }
        return x;
    }

    void union(int u, int v) {
        if (parent[u] == u && parent[v] == v) {
            parent[v] = u;
        } else if (parent[u] == u && parent[v] != v) {
            parent[u] = v;
        } else if (parent[v] == v && parent[u] != u) {
            parent[v] = u;
        } else if (parent[u] != u && parent[v] != v) {
            int root1 = findRoot(u);
            int root2 = findRoot(v);
            parent[root1] = root2;
        }
    }

    private void sort() {
        for (int i = 0; i < edgeCount; ++i) {
            for (int j = i + 1; j < edgeCount; ++j) {
                if (edges[i].getWeight() > edges[j].getWeight()) {
                    Edge tmp = edges[i];
                    edges[i] = edges[j];
                    edges[j] = tmp;
                }
            }
        }
    }

}
