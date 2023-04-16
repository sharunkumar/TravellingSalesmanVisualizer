package edu.neu;

import edu.neu.christofides.Constants;
import edu.neu.christofides.GreedyMatch;
import edu.neu.christofides.MultiGraph;
import edu.neu.graphs.node.GraphNode;
import edu.neu.mst.Graph;
import edu.neu.utility.ReadDistanceMatrix;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class GreedyMatchAndMultiGraphTest {

    @Test
    public void testGreedyMatch() throws IOException {

        double[][] weightMatrix = ReadDistanceMatrix.readDistanceMatrix(Constants.DATA_SET_LOCATION_2);
        int[][] checkGreedyMatch = {{0,7}, {4,8}};
        int[] route = {0,0,1,2,3,2,5,6,2};
        int [][] greedyMatch = GreedyMatch.greedyMatch(route, weightMatrix);
        assertArrayEquals(checkGreedyMatch,greedyMatch);
    }

    @Test
    public void testMultiGraphGeneration(){
        int[][] checkGreedyMatch = {{0,7}, {4,8}};
        int[] route = {0,0,1,2,3,2,5,6,2};
        GraphNode[] checkGraph = generateGraphNodeForMultiGraph();
        GraphNode[] nodes = MultiGraph.build(checkGreedyMatch, route);
        boolean result = checkBothGraphs(checkGraph, nodes);
       assertTrue(result);
    }

    public static GraphNode[] build(int[][] match, int[] mst) {
        GraphNode[] nodes = new GraphNode[mst.length];

        for (int i = 0; i < mst.length; i++) {
            nodes[i] = new GraphNode(i);
        }

        for (int i = 1; i < mst.length; i++) {
            nodes[i].addChild(nodes[mst[i]]);
            nodes[mst[i]].addChild(nodes[i]);
        }

        for (int[] ints : match) {
            nodes[ints[0]].addChild(nodes[ints[1]]);
            nodes[ints[1]].addChild(nodes[ints[0]]);

        }

        return nodes;
    }

    public boolean checkBothGraphs(GraphNode expected[], GraphNode current[])
    {
        if(expected.length != current.length)
        {
            return false;
        }

        for(int i = 0;i<expected.length;i++)
        {
            GraphNode one = expected[i];
            GraphNode two = current[i];

            if((one.getName() != two.getName()) || (one.isVisited() != two.isVisited()) || (one.getNumberOfChilds()!= two.getNumberOfChilds()))
            {
                return false;
            }

            ArrayList oneChild = one.getChildList();
            ArrayList twoChild = two.getChildList();
            for(int j =0; j< oneChild.size();j++)
            {
               GraphNode oneChildOne = (GraphNode) oneChild.get(j);
               GraphNode twochildTwo = (GraphNode) twoChild.get(j);

                if((oneChildOne.getName() != twochildTwo.getName()) || (oneChildOne.isVisited() != twochildTwo.isVisited()) || (oneChildOne.getNumberOfChilds()!= twochildTwo.getNumberOfChilds()))
                {
                    return false;
                }
            }
        }
        return true;
    }


    public static GraphNode[] generateGraphNodeForMultiGraph()
    {
        GraphNode zero = new GraphNode(0);
        GraphNode one = new GraphNode(1);
        GraphNode two = new GraphNode(2);
        GraphNode three = new GraphNode(3);
        GraphNode four = new GraphNode(4);
        GraphNode five = new GraphNode(5);
        GraphNode six = new GraphNode(6);
        GraphNode seven = new GraphNode(7);
        GraphNode eight = new GraphNode(8);

        zero.addChild(one);
        zero.addChild(seven);

        one.addChild(zero);
        one.addChild(two);

        two.addChild(one);
        two.addChild(three);
        two.addChild(five);
        two.addChild(eight);

        three.addChild(two);
        three.addChild(four);

        four.addChild(three);
        four.addChild(eight);

        five.addChild(two);
        five.addChild(six);

        six.addChild(five);
        six.addChild(seven);

        seven.addChild(six);
        seven.addChild(zero);

        eight.addChild(two);
        eight.addChild(four);

        GraphNode [] result = new GraphNode[9];
        result[0]=zero;
        result[1]=one;
        result[2]=two;
        result[3]=three;
        result[4]=four;
        result[5]=five;
        result[6]=six;
        result[7]=seven;
        result[8]=eight;

        return result;

    }
}
