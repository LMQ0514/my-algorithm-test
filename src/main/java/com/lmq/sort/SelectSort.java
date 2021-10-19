package com.lmq.sort;

/**
 * 选择排序
 * O（n^2）
 */
public class SelectSort {
    public static void main(String[] args) {
            int[] array = new int[]{2,1,4,3};
            sort(array);
            for (int i : array) {
                System.out.print(i + " ");
            }
    }

    public static void sort(int[] array){

        for (int i = 0;i < array.length - 1;i++){
            int min = array[i];
            int minIndex = i;
            for (int j = i + 1;j < array.length;j++){
                if(min > array[j]){
                    min = array[j];
                    minIndex = j;
                }
            }
            if(minIndex != i){
                array[minIndex] = array[i];
                array[i] = min;
            }
        }
    }
}
