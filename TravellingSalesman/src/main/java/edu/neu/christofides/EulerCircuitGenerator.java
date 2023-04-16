package edu.neu.christofides;

import edu.neu.graphs.node.GraphNode;

import java.util.LinkedList;
import java.util.Vector;

public class EulerCircuitGenerator {
    public static int[] generateEulerCircuit(GraphNode nodes[]) {
        LinkedList path = new LinkedList();
        Vector tmpPath = new Vector();
        int j = 0;


        nodes[0].getNextChild(nodes[0].getName(), tmpPath, true);
        path.addAll(0, tmpPath);


        while (j < path.size()) {
            if (nodes[((Integer) path.get(j)).intValue()].hasMoreChilds()) {
                nodes[((Integer) path.get(j)).intValue()].getNextChild(nodes[((Integer) path.get(j)).intValue()].getName(), tmpPath, true);
                if (tmpPath.size() > 0) {
                    //sätt ihop path och tmpPath
                    for (int i = 0; i < path.size(); i++) {
                        if (((Integer) path.get(i)).intValue() == ((Integer) tmpPath.elementAt(0)).intValue()) {
                            path.addAll(i, tmpPath);
                            break;
                        }
                    }
                    tmpPath.clear();
                }
                j = 0;
            } else j++;
        }


        boolean inPath[] = new boolean[nodes.length];
        int[] route = new int[nodes.length];
        j = 0;
        for (int i = 0; i < path.size(); i++) {
            if (!inPath[((Integer) path.get(i)).intValue()]) {
                route[j] = ((Integer) path.get(i)).intValue();
                j++;
                inPath[((Integer) path.get(i)).intValue()] = true;
            }
        }


        return route;
    }
}
