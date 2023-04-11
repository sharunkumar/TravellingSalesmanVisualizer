package edu.neu.christofides;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class PrimsAlgorithm {

    public static int[] run(double[][] weightMatrix,int weightMatrixSize){

        List<Integer> primQueue=new ArrayList<>();
        boolean[] checkTree = new boolean[weightMatrixSize];
        double[] allKeys =new double[weightMatrixSize];
        int[] result =new int[weightMatrixSize];


        for(int i=0;i<weightMatrixSize;i++) {
            primQueue.add(i);
        }

        for(int i=0;i<weightMatrixSize;i++){
            allKeys[i]=Constants.INT_MAX_VALUE;
        }

        allKeys[0]=0;
        int zero=0;
        double temp;
        Integer elem;

        do{
            checkTree[zero] = true;
            primQueue.remove(zero);
            for(int v=0;v<weightMatrixSize;v++){
                if( !checkTree[v] && weightMatrix[zero][v]<allKeys[v] ){
                    result[v]=zero;
                    allKeys[v]=weightMatrix[zero][v];
                }
            }


            double mint=Constants.DOUBLE_MAX_VALUE;
            for (Integer integer : primQueue) {
                elem = (Integer) integer;
                temp = allKeys[elem];
                if (temp < mint) {
                    zero = elem;
                    mint = temp;
                }
            }
        } while(!primQueue.isEmpty());

        return result;
    }
}
