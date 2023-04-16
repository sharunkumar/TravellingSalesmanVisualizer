package edu.neu;

import edu.neu.christofides.Constants;
import edu.neu.christofides.GreedyMatch;
import edu.neu.christofides.MultiGraph;
import edu.neu.graphs.node.GraphNode;
import edu.neu.mst.Graph;
import edu.neu.utility.ReadDistanceMatrix;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class GreedyMatchAndMultiGraphTest {

    @Test
    public void testGreedyMatch() throws IOException {

        double[][] weightMatrix = ReadDistanceMatrix.readDistanceMatrix(Constants.DATA_SET_LOCATION_2);
        int[][] checkGreedyMatch = {{0,7}, {4,8}};
        int[] route = {0,0,1,2,3,2,5,6,2};
        int [][] greedyMatch = GreedyMatch.greedyMatch(route, weightMatrix, weightMatrix[0].length);
        assertArrayEquals(checkGreedyMatch,greedyMatch);
    }

    @Test
    public void testMultiGraphGeneration(){
        int[][] checkGreedyMatch = {{0,7}, {4,8}};
        int[] route = {0,0,1,2,3,2,5,6,2};
        GraphNode checkGraph[] =  new GraphNode[9];
        checkGraph = generateGraphNodeForMultiGraph();
        GraphNode[] nodes = MultiGraph.build(checkGreedyMatch, route);
        boolean result = checkBothGraphs(checkGraph, nodes);
       assertTrue(result);
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

            if((one.getName() != two.getName()) || (one.isVisited() != two.isVisited()))
            {
                return false;
            }

            /*if()
            {
                return false;
            }*/
        }
        return false;
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
