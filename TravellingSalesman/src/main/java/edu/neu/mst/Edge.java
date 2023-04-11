/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.neu.mst;

import edu.neu.modals.Location;

import java.util.Set;

public class Edge implements Comparable<Edge> {
    private final Location u;
    private final Location v;
    private final double weight;
    private int hashCode = -1;

    public Edge(Location u, Location v) {
        this.u = u;
        this.v = v;
        this.weight = u.distanceTo(v);
    }

    public static Edge of(Location u, Location v) {
        return new Edge(u, v);
    }

    public Location getU() {
        return u;
    }

    public Location getV() {
        return v;
    }

    public double getWeight() {
        return weight;
    }

    @Override
    public int compareTo(Edge o) {
        return this.getU() == o.getU() && this.getV() == o.getV() || this.getU() == o.getV() && this.getV() == o.getU() ? 0 : this.getWeight() > o.getWeight() ? 1 : -1;
    }

    @Override
    public int hashCode() {
        if (hashCode == -1) {
            hashCode = Set.of(u, v).hashCode();
        }
        return hashCode;
    }
}
