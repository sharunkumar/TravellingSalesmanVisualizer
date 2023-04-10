/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.neu.mst;

import edu.neu.modals.Location;

public class Edge {
    private final Location u;
    private final Location v;
    private final double weight;

    public Edge(Location u, Location v) {
        this.u = u;
        this.v = v;
        this.weight = u.distanceTo(v);
    }
}
