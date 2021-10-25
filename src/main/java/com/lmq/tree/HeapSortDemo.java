package com.lmq.tree;

import java.util.Arrays;

/**
 * 大顶堆排序
 */
public class HeapSortDemo {
    public static void main(String[] args) {
        int[] ints = new int[]{92,3,477,5,7,13,34,12};
        sort(ints);
        System.out.println(Arrays.toString(ints));
    }

    public static void sort(int[] array){
        int temp = 0;
        //调整所有非叶子节点
        for (int i = array.length / 2 - 1;i >= 0;i--){
            adjustHeap(array,i, array.length);
        }
        //将最大值摘出
        for (int j = array.length - 1;j > 0;j--){
            temp = array[j];
            array[j] = array[0];
            array[0] = temp;
            adjustHeap(array,0,j);
        }
    }

    /**
     * 将以i为父节点的最大值调整为堆顶
     * @param array  需要调整的数组
     * @param i      非叶子节点的下标
     * @param length 数组长度
     */
    public static void adjustHeap(int[] array,int i,int length){
        int temp = array[i];
        for (int k = 2 * i + 1;k < length;k = k * 2 + 1){
            if((k + 1) < length && array[k] < array[k + 1]){
                k++;
            }
            if(temp < array[k]){
                array[i] = array[k];
                i = k;
            }else {
                break;
            }
        }
        array[i] = temp;
    }
}

