package edu.neu.graphs.graphInterface;

import edu.neu.graphs.node.GraphNode;

import java.util.Vector;

public interface GraphNodeInterface {

    public boolean isVisited();

    public void setVisited();

    public int getNumberOfChilds();

    public void setNotVisited();

    public void addChild(GraphNode node);

    public void removeChild(GraphNode node);


    public boolean hasMoreChilds();

    public int getName();

    public void getNextChild(int goal, Vector path, boolean firstTime);
}
