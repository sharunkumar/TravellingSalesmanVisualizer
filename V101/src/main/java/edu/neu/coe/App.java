package edu.neu.coe;
import java.io.*;
import java.util.*;
import java.math.*;
import java.lang.*;
public class App 
{
    private static int[][] routeMap;
    public static void main( String[] args )
    {
//        routeMap = new int[4][4];
//        for(int i=0; i<4; i++) {
//            for(int j=0; j<4; j++) {
//                if(i==j) routeMap[i][j] = 0;
//                else routeMap[i][j] = 2;
//            }
//        }
//        routeMap[3][0] = 1;
//        routeMap[0][3] = 1;

//        Initializing known routeMap and weight should be 85
        routeMap = new int[][]{
                {0, 20, 42, 25},
                {20, 0, 30, 34},
                {42, 30, 0, 10},
                {25, 34, 10, 0}
        };

        RecursionTSP recursionTSP = new RecursionTSP(routeMap);
        DynamicProgrammingTSP dynamicProgrammingTSP = new DynamicProgrammingTSP(routeMap);

        long travelingSalesmanRecursion = recursionTSP.travelingSalesmanRecursion(1, 0);
        long travelingSalesmanDP = dynamicProgrammingTSP.travelingSalesmanDP(1, 0);

        System.out.println("Traveling Salesman Minimum Weight (Recursion Technique): "+travelingSalesmanRecursion);
        System.out.println("Traveling Salesman Minimum Weight (Dynamic Programming Technique): "+travelingSalesmanDP);

    }
}
