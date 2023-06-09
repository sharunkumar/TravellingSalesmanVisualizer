package edu.neu.graphs.edge;

import edu.neu.graphs.graphInterface.EdgeInterface;

public class Edge implements Comparable, EdgeInterface {
    int from, to;
    double cost;

    public Edge(int _from, int _to, double _cost) {
        from = _from;
        to = _to;
        cost = _cost;
    }

    public int compareTo(Object _o) {
        Edge e = (Edge)_o;
        if(this.cost == e.cost) return 0;
        else if(this.cost > e.cost) return 1;
        else return -1;
    }

    public int getTo() {return to;}


    public int getFrom() {return from;}
}