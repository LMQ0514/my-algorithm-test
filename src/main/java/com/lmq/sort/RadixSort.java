package com.lmq.sort;

import java.util.Arrays;

/**
 * 基数排序(桶排序)
 * 属于稳定性排序，两个相同的数据顺序不会改变
 * O(n*k)
 */
public class RadixSort {
    public static void main(String[] args) {
        int[] array = new int[]{3452,111,23,58,741,9};
        sort(array);
        System.out.println(Arrays.toString(array));
    }

    public static void sort(int[] array){
        int[][] bucket = new int[10][array.length];//10个”桶“，只能排正数，排负数需要额外的负数“桶”
        int[] bucketElementCount = new int[10];//每个“桶”的数据量
        int max = array[0];
        //找到最高位
        for (int i = 0;i < array.length;i++){
            if(max < array[i]){
                max = array[i];
            }
        }
        String place = max + "";
        for (int j = 0,n = 1;j < place.length();j++,n*=10){
            //对array进行循环,把数据放到“桶”里
            for (int i = 0;i < array.length;i++){
                int temp = array[i] / n % 10;
                bucket[temp][bucketElementCount[temp]] = array[i];
                bucketElementCount[temp]++;
            }
            int index = 0;
            //对“桶”进行遍历,把数据放到原数组中
            for (int k = 0;k < bucketElementCount.length;k++){
                if(bucketElementCount[k] != 0){
                    for (int l = 0;l < bucketElementCount[k];l++){
                        array[index++] = bucket[k][l];
                    }
                }
                bucketElementCount[k] = 0;//重置数据量
            }
        }
    }
}
