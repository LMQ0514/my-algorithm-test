package com.lmq;

/**
 * 稀疏数组，压缩数组大小
 */
public class SparseArray {
    public int[][] sparse(int[][] array){
        int sum = 0;
        int line = 0;
        int row = 0;

        for (int[] subArray : array) {
            line++;
            row = subArray.length;
            for (int data : subArray) {
                if(data != 0){
                    sum++;
                }
            }
        }
        int[][] sparseArray = new int[sum + 1][3];
        sparseArray[0][0] = row;
        sparseArray[0][1] = line;
        sparseArray[0][2] = sum;

        int count = 0;
        for (int i = 0;i < line;i++){
            for (int j = 0;j < row;j++){
                if (array[i][j] != 0){
                    count++;
                    sparseArray[count][0] = i;
                    sparseArray[count][1] = j;
                    sparseArray[count][2] = array[i][j];
                }
            }
        }
        return sparseArray;
    }

    public int[][] recover(int[][] sparseArray){
        int line = sparseArray[0][0];
        int row = sparseArray[0][1];

        int[][] array =new int[line][row];

        for (int i = 1;i < sparseArray.length;i++){
            for (int j = 0;j < 3;j++){
                int dataLine = sparseArray[i][0];
                int dataRow = sparseArray[i][1];
                array[dataLine][dataRow] = sparseArray[i][2];
            }
        }
        return array;
    }
}
