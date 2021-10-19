package com.lmq.sort;

/**
 * 插入排序
 */
public class InsertSort {
    public static void main(String[] args) {
        int[] array = new int[]{2,1,4,3};
        sort(array);
        for (int i : array) {
            System.out.print(i + " ");
        }
    }

    private static void sort(int[] array) {

        for (int i = 1;i < array.length;i++){
            int insertVal = array[i];
            int insertIndex = i - 1;

            while (insertIndex  >= 0 && array[insertIndex] > insertVal){
                array[insertIndex + 1] = array[insertIndex];
                insertIndex--;//①
            }
            if(insertIndex != i - 1){
                array[insertIndex + 1] = insertVal;//①处多减了一
            }
        }

    }
}
