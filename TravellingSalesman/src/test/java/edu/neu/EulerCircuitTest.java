package edu.neu;

import edu.neu.christofides.EulerCircuitGenerator;
import edu.neu.graphs.node.GraphNode;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;

public class EulerCircuitTest {

    @Test
    public void testEulerCircuit()
    {
       int eulerCuircuitCheck [] ={0,1,2,3,4,8,5,6,7};
       GraphNode[] nodes = GreedyMatchAndMultiGraphTest.generateGraphNodeForMultiGraph();
       int eulerCircuitRoute[] = EulerCircuitGenerator.generateEulerCircuit(nodes);

        assertArrayEquals(eulerCuircuitCheck, eulerCircuitRoute);

    }
}
