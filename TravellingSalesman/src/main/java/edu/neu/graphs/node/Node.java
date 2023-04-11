package edu.neu.graphs.node;

import edu.neu.graphs.graphInterface.NodeInterface;

import java.util.ArrayList;

public class Node implements NodeInterface {
    boolean isRoot;
    int number;
    ArrayList children;

    public Node(int _n) {
        number = _n;
        children = null;
        isRoot = false;
    }

    public Node(int _n, boolean _isRoot) {
        number = _n;
        children = null;
        this.isRoot = _isRoot;
    }

    public void addChild(Node _node) {
        if(children == null) children = new ArrayList();
        children.add(_node);
    }


    public void visitBuildRoute(ArrayList _route) {
        _route.add(new Integer(number));
        if(children == null) return;
        for(int i = 0; i < children.size(); i++) {
            ((Node)children.get(i)).visitBuildRoute(_route);
        }
    }


    public void visitFindOddDegreeNodes(ArrayList<Integer>_oddNodes) {
        if(children == null ) {
            _oddNodes.add(number);
            return;
        }
        if (isRoot && children.size() % 2 != 0) _oddNodes.add(number);
        if (!isRoot && children.size() % 2 == 0) _oddNodes.add(number);
        for(int i = 0; i < children.size(); i++) {
            ((Node)children.get(i)).visitFindOddDegreeNodes(_oddNodes);
        }
    }

    public int getNumber() {return number;}
}
