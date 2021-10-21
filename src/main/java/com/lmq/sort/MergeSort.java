package com.lmq.sort;

import java.util.Arrays;
import java.util.Date;
import java.util.Random;

/**
 * 归并排序（分治）
 * O(n*log n)
 */
public class MergeSort {
    public static void main(String[] args) {
        int[] array = new int[5];
        int[] temp = new int[array.length];
        Random random = new Random(1000);
        for (int i = 0;i < array.length;i++) {
            array[i] = random.nextInt();
        }
        Date begin = new Date(System.currentTimeMillis());
        merge(array,temp,0, array.length - 1);
        Date end = new Date(System.currentTimeMillis());
//        System.out.println((end.getTime() - begin.getTime()));
        System.out.println(Arrays.toString(array));
    }
    public static void merge(int[] array,int[] temp,int left,int right){
        if(right > left){
            int mid = (left + right) / 2;
            merge(array, temp, left, mid);
            merge(array, temp, mid + 1, right);
            sort(array,temp,left,mid,right);
        }
    }
    public static void sort(int[] array,int[] temp,int left,int mid,int right){
        //合并
        int i = left;
        int j = mid + 1;
        int t = 0;
        while (i <= mid && j <= right){
            if(array[i] <= array[j]){
                temp[t] = array[i];
                t += 1;
                i += 1;
            }else {
                temp[t] = array[j];
                t += 1;
                j += 1;
            }
        }
        while (i <= mid){
            temp[t] = array[i];
            t += 1;
            i += 1;
        }
        while (j <= right){
            temp[t] = array[j];
            t += 1;
            j += 1;
        }
        t = 0;
        int tempLeft = left;
        while (tempLeft <= right){
            array[tempLeft] = temp[t];
            t += 1;
            tempLeft += 1;
        }
    }
}
