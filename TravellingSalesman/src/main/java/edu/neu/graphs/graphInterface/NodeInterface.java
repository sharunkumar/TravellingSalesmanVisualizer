package edu.neu.graphs.graphInterface;

import edu.neu.graphs.node.Node;

import java.util.ArrayList;

public interface NodeInterface {

    public void addChild(Node _node);
    public void visitBuildRoute(ArrayList _route);
    public void visitFindOddDegreeNodes(ArrayList <Integer>_oddNodes);
    public int getNumber();


}
