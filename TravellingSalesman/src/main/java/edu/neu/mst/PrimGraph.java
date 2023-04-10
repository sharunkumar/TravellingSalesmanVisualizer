/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.neu.mst;

public class PrimGraph extends Graph {

    private int matrix[][];
    private PrimAlgorithm pA = new PrimAlgorithm();

    public PrimGraph(int vCount, int eCount) {
        super(vCount, eCount);
        //populate matrix
        matrix = new int[vCount][vCount];
        for (int i = 0; i < eCount; ++i) {
            int u = edges[i].getU();
            int v = edges[i].getV();
            int weight = edges[i].getWeight();
            matrix[u][v] = matrix[v][u] = weight;
        }
        //find mst ; 
        mst = pA.prim(0, this);
    }

    public int[][] getMatrix() {
        return this.matrix;
    }

}
