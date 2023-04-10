/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.neu.mst;

public class PrimAlgorithm {

    public static final int MAX = 100;
    boolean[] free = new boolean[MAX];
    int[] parent = new int[MAX];
    int[] key = new int[MAX];
    private int vertexCount = 4;

    public int[][] prim(int start, PrimGraph graph) {
        vertexCount = graph.getvCount();
        int[][] matrix = graph.getMatrix();
        int[][] mst = new int[vertexCount][vertexCount];

        for (int i = 0; i < vertexCount; ++i) {
            parent[i] = -1;
            free[i] = true;
            key[i] = Integer.MAX_VALUE;
        }

        key[start] = 0;

        for (int i = 0; i < vertexCount; ++i) {
            int u = extractMin();
            free[u] = false;

            for (int v = 0; v < vertexCount; ++v) {
                if (free[v] && matrix[u][v] != 0 && matrix[u][v] < key[v]) {
                    key[v] = matrix[u][v];
                    parent[v] = u;
                }
            }
        }

        for (int i = 0; i < vertexCount; ++i) {
            if (i != start) {
                mst[parent[i]][i] = mst[i][parent[i]] = matrix[parent[i]][i];
                System.out.println(i + ":" + parent[i] + ":" + mst[parent[i]][i]);
            }
        }
        return mst;
    }


    private int extractMin() {
        int min = Integer.MAX_VALUE, u = 0;
        for (int i = 0; i < vertexCount; ++i) {
            if (free[i] && key[i] < min) {
                min = key[i];
                u = i;
            }
        }
        return u;
    }

}
