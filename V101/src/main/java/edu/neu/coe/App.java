package edu.neu.coe;

/**
 * Hello world!
 *
 */
public class App 
{
    private static int[][] routeMap = new int[4][4];
    public static void main( String[] args )
    {
        for(int i=0; i<4; i++) {
            for(int j=0; j<4; j++) {
                if(i==j) routeMap[i][j] = 0;
                else routeMap[i][j] = 2;
            }
        }
        routeMap[3][0] = 1;
        routeMap[0][3] = 1;
        RecursionTSP recursionTSP = new RecursionTSP(routeMap);
        long travelingSalesman = recursionTSP.travelingSalesmanRecursion(1, 0);
        System.out.println(travelingSalesman);
    }
}
