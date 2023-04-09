package edu.neu.coe;
import java.util.*;
import java.io.*;

public class RecursionTechnique {
    public static int numberOfNodes = 100;
    public static ArrayList< ArrayList<Integer> > arr = new ArrayList<>();
    public static int visitedAll = 0;
    public static void initializeDistanceMatrix(int nodes) {
        int val = -1;
        numberOfNodes = nodes;
        visitedAll = (1<<nodes)-1;;
        for(int i=0; i<numberOfNodes; i++) {
            ArrayList<Integer> subArr = new ArrayList<>();
            for(int j=0; j<numberOfNodes; j++) {
                subArr.add(val);
            }
            arr.add(subArr);
        }
        for(int i=0; i<arr.size(); i++) {
            ArrayList<Integer> newArr = arr.get(i);
            for(int j=0; j<newArr.size(); j++) {
                System.out.print(newArr.get(j));
            }
            System.out.println();
        }
    }
    public static int travelingSalesman(int mask, int pos) {
        if(mask==visitedAll) {
            ArrayList<Integer> newArr = arr.get(pos);
            return newArr.get(0);
        }
        int ans = Integer.MAX_VALUE;
        for(int city = 0; city<numberOfNodes; i++) {
            if((mask&(1<<city))==0) {
                int newAns = arr.get(pos).get(city) + travelingSalesman(mask|(1<<city), city);
                ans = Math.min(ans, newAns);
            }
        }
        return ans;
    }
    public static void main(String[] args) {
        initializeDistanceMatrix(4);
    }
}
